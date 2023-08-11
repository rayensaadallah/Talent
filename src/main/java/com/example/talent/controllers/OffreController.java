package com.example.talent.controllers;


import com.example.talent.dtos.OffreDto;
import com.example.talent.dtos.UserDto;
import com.example.talent.services.Offre.IoffreService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/offer")
@CrossOrigin("*")
public class OffreController {
    IoffreService ioffreService;

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN','USER','MANAGER')")
    public List<OffreDto> all(){
        List<OffreDto> l= ioffreService.getAlloffres();
        return l;
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<String> removeOffre(@PathVariable Integer id) {
        ioffreService.delete(id);
        return ResponseEntity.ok("Offer is Deleted ");
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER','MANAGER')")
    public OffreDto get(@PathVariable Integer id ){
        return ioffreService.getoneoffre(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<String> update(@PathVariable("id")Integer id ,@RequestBody OffreDto offreDto)
    {
        ioffreService.update(id,offreDto);
        return  ResponseEntity.ok("Offer have been updated ");
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<String> add(@RequestBody OffreDto offreDto)
    {
        ioffreService.add(offreDto);
        return ResponseEntity.ok("Offer Added Succesfully");
    }

}
