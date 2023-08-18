package com.example.talent.services.CV;

import com.example.talent.models.*;
import com.example.talent.repository.CVRepository;
import com.example.talent.repository.SkillsRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class Cv_ser implements Icv {
    @Autowired
    SkillsRepository Skills_rep;
    @Autowired
    CVRepository cr;

    public String retrieveAndScrapeCV(Cv cv) {
        try {
            // Assuming the CV content is stored in a byte array
            byte[] cvContent = cv.getUploadedFile();

            // Convert the byte array to a ByteArrayInputStream
            ByteArrayInputStream inputStream = new ByteArrayInputStream(cvContent);

            // Parse the PDF and extract text
            String extractedText = extractTextFromPDF(inputStream);

            // Perform scraping on the extracted text (emails in this example)
            String scrapedEmails = scrapeEmailsFromText(extractedText);

            return scrapedEmails;
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
        return null;
    }

    private String extractTextFromPDF(ByteArrayInputStream inputStream) throws IOException {
        PDDocument document = PDDocument.load(inputStream);
        PDFTextStripper textStripper = new PDFTextStripper();
        String extractedText = textStripper.getText(document);
        document.close();
        return extractedText;
    }

    private String scrapeEmailsFromText(String text) {
        // Implement your scraping logic here to extract emails
        Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        Matcher matcher = emailPattern.matcher(text);

        StringBuilder scrapedEmails = new StringBuilder();
        while (matcher.find()) {
            scrapedEmails.append(matcher.group()).append("\n");
        }

        return scrapedEmails.toString();
    }

    public List<String> extractSkillsFromCV(Cv cv) throws IOException {
        String extractedText = extractTextFromPDF(new ByteArrayInputStream(cv.getUploadedFile()));
        return extractSkillsFromText(extractedText);
    }

    private List<String> extractSkillsFromText(String text) {
        // Define a pattern that matches skills like "Java," "Spring Boot," or "Angular"
        Pattern skillsPattern = Pattern.compile("\\b(?:Java|Spring Boot|Angular|angular|spring boot|Symfony|Php)\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = skillsPattern.matcher(text);

        Set<String> uniqueExtractedSkills = new HashSet<>(); // Use a set to ensure uniqueness
        while (matcher.find()) {
            uniqueExtractedSkills.add(matcher.group());
        }

        List<String> extractedSkills = new ArrayList<>(uniqueExtractedSkills); // Convert set to list
        return extractedSkills;
    }

    public void saveSkillsToDatabase(List<String> skills, Cv cv) {
       // Assuming you have a Skills entity and repository
        List<Skills> skillEntities = new ArrayList<>();
        for (String skillName : skills) {
            Skills skill = new Skills();
            skill.setName(skillName);
            // Set other attributes of the Skills entity if needed
            skillEntities.add(skill);
        }
        cv.setSkills(skillEntities);

        Skills_rep.saveAll(skillEntities);



    }
}
