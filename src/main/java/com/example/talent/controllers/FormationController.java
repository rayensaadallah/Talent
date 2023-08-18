package com.example.talent.controllers;

import com.example.talent.dtos.Formationdto;

import com.example.talent.services.Formation.IFormationServices;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/formation")
@CrossOrigin("*")
public class FormationController {


    IFormationServices iFormationServices;

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN','USER','MANAGER')")
    public List<Formationdto> all(){
        List<Formationdto> l= iFormationServices.getAll();
        return l;
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<String> removeOffre(@PathVariable Integer id) {
        iFormationServices.delete(id);
        return ResponseEntity.ok("Offer is Deleted ");
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER','MANAGER')")
    public Formationdto get(@PathVariable Integer id ){
        return iFormationServices.getone(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<String> update(@PathVariable("id")Integer id ,@RequestBody Formationdto dto)
    {
        iFormationServices.update(id,dto);
        return  ResponseEntity.ok("Offer have been updated ");
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<String> add(@RequestBody Formationdto dto)
    {
        iFormationServices.add(dto);
        return ResponseEntity.ok("Offer Added Succesfully");
    }
    @PutMapping("/buy/{id}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<String> buy(@PathVariable("id")Integer id )
    {
        iFormationServices.BuyFormation(id);
        return  ResponseEntity.ok("Formation have been bought ");
    }

}
