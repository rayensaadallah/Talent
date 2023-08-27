package com.example.talent.controllers;


import com.example.talent.models.Cv;
import com.example.talent.models.Users;
import com.example.talent.repository.CVRepository;
import com.example.talent.repository.UserRepository;
import com.example.talent.services.CV.Cv_ser;
import com.example.talent.services.CV.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cv")
@CrossOrigin("*")

public class CVController {
@Autowired
FileUploadService fileUploadService;

@Autowired
CVRepository cr;
@Autowired
UserRepository ur;
@Autowired
Cv_ser cs;

    @GetMapping("/get")
    public String showUploadForm(Model model) {
        model.addAttribute("cv", new Cv());
        return "upload-form"; // Create an HTML template for the form
    }



  //  @PostMapping("/upload/" )
   @RequestMapping(value = "/upload/{userId}", headers = ("content-type=multipart/form-data"), method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file  ,@PathVariable Integer userId) {
       Users user = ur.findById(userId).orElse(null);
       if (user == null) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user ID.");
       }

       Cv cv = new Cv();
        try {
            cv.setUploadedFile(file.getBytes());
            user.setCv(cv);

         cr.save(cv);

            return ResponseEntity.ok("File uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
        }
    }
    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateCVByUserId(@RequestParam("file") MultipartFile file, @PathVariable Integer userId) {
        Users user = ur.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user ID.");
        }

        Cv cv = new Cv();
        try {
            cv.setUploadedFile(file.getBytes());
            user.setCv(cv);

            // Save the changes to update the user entity with the new CV
            ur.save(user);

            return ResponseEntity.ok("CV updated successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update CV.");
        }
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteCVByUserId(@PathVariable Integer userId) {
        Users user = ur.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user ID.");
        }
        user.setCv(null);
        ur.save(user);
        return ResponseEntity.ok("CV deleted successfully.");
    }

    @RequestMapping(value = "/uploadd", headers = ("content-type=multipart/form-data"), method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> handleFileUploadwioutuser(@RequestParam("file") MultipartFile file) {
        Cv cv = new Cv();
        try {
            cv.setUploadedFile(file.getBytes());
            cr.save(cv);

            return ResponseEntity.ok("File uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
        }
    }

    @GetMapping("/scrape/{cvId}")
    public ResponseEntity<String> scrapeCV(@PathVariable Integer cvId) {
        Cv cv = cr.findById(cvId).orElse(null);
        if (cv != null) {
            String scrapedData = cs.retrieveAndScrapeCV(cv);
            // Log the extracted email addresses
            System.out.println("Extracted email addresses: \n" + scrapedData);
            return ResponseEntity.ok(scrapedData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/scrapeSkills/{cvId}")
    public ResponseEntity<List<String>> Skills(@PathVariable Integer cvId) throws IOException {
        Cv cv = cr.findById(cvId).orElse(null);
        if (cv != null) {
            List<String> extractedSkills = cs.extractSkillsFromCV(cv);
            cs.saveSkillsToDatabase(extractedSkills,cv) ;
            // Log the extracted skills
            System.out.println("Extracted skills: \n" + extractedSkills);

            return ResponseEntity.ok(extractedSkills);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


