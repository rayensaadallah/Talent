package com.example.talent.controllers;

import com.example.talent.dtos.CarrierDto;
import com.example.talent.dtos.UserDto;
import com.example.talent.services.Carrier.IServiceCarrier;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/carriers")
@CrossOrigin("*")
public class CarrierController {

    IServiceCarrier serviceCarrier;
    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN','USER','MANAGER')")
    public List<CarrierDto> showAll(){
         List<CarrierDto> list=serviceCarrier.getAllCarriers();
        return list;
    }
    @GetMapping("/show")
    @PreAuthorize("hasAnyRole('ADMIN','USER','MANAGER')")
    public CarrierDto showone(@RequestBody CarrierDto carrierDto){
     CarrierDto ca  =serviceCarrier.getCarrier(carrierDto);
        return  ca;
    }
    @PutMapping("/assignCarrierToUser")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public void assignCarrierToUser(@RequestBody CarrierDto carrierDto) {
        serviceCarrier.BuyCarrier(carrierDto);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public void add(@RequestBody CarrierDto carrierDto) {
        serviceCarrier.add(carrierDto);
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public void remove(@PathVariable Integer id) {
        serviceCarrier.delete(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public void update(@RequestBody CarrierDto carrierDto) {
        serviceCarrier.update(carrierDto);
    }

}
