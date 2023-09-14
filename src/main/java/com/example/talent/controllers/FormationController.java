package com.example.talent.controllers;



import com.example.talent.dtos.Formationdto;
import com.example.talent.dtos.UserDto;
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
    public List<Formationdto> showAll(){
        List<Formationdto> list=iFormationServices.getAll();
        return list;
    }
    @GetMapping("/show")
    @PreAuthorize("hasAnyRole('ADMIN','USER','MANAGER')")
    public Formationdto showone(@RequestBody Formationdto dto){
        Formationdto ca  =iFormationServices.getone(dto);
        return  ca;
    }
    @PutMapping("/BuyFormation")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<String> assignCarrierToUser(@RequestBody Formationdto dto) {
        iFormationServices.BuyFormation(dto);
        return ResponseEntity.ok("Formation assigned to User successfully");
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<String> add(@RequestBody Formationdto dto) {
        iFormationServices.add(dto);
        return ResponseEntity.ok("Formation is added ");
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<String> remove(@RequestBody Formationdto dto) {
        iFormationServices.delete(dto);
        return ResponseEntity.ok("Formation is Deleted ");

    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<String>update(@RequestBody Formationdto dto) {
        iFormationServices.update(dto);
        return ResponseEntity.ok("Formation is updated");
    }


}
