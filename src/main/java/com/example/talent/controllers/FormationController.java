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
    public void assignCarrierToUser(@RequestBody Formationdto dto) {
        iFormationServices.BuyFormation(dto);

    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public void add(@RequestBody Formationdto dto) {
        iFormationServices.add(dto);

    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public void remove(@PathVariable Integer id) {
        iFormationServices.delete(id);
    }


    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public void update(@RequestBody Formationdto dto) {
        iFormationServices.update(dto);

    }


}
