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
    public ResponseEntity<String> assignCarrierToUser(@RequestBody CarrierDto carrierDto) {
        serviceCarrier.BuyCarrier(carrierDto);
        return ResponseEntity.ok("Carrier assigned to User successfully");
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<String> add(@RequestBody CarrierDto carrierDto) {
        serviceCarrier.add(carrierDto);
        return ResponseEntity.ok("Carrier is added ");
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<String> remove(@RequestBody CarrierDto carrierDto) {
        serviceCarrier.delete(carrierDto);
      return ResponseEntity.ok("Carrier is Deleted ");

    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<String>update(@RequestBody CarrierDto carrierDto) {
        serviceCarrier.update(carrierDto);
        return ResponseEntity.ok("Carrier is updated");
    }

}
