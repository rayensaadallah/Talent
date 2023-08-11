package com.example.talent.controllers;

import com.example.talent.dtos.CarrierDto;
import com.example.talent.services.Carrier.IServiceCarrier;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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
    @GetMapping("/show/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER','MANAGER')")
    public CarrierDto showone(@PathVariable("id") Integer carrierId){
     CarrierDto carrierDto  =serviceCarrier.getCarrier(carrierId);

        return  carrierDto;
    }
    @PutMapping("/assignCarrierToUser")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<String> assignCarrierToUser(@RequestParam Integer carrierId, @RequestParam Integer userId) {
        serviceCarrier.assignCarrierToUser(carrierId, userId);
        return ResponseEntity.ok("Carrier assigned to User successfully");
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<String> add(@RequestBody CarrierDto carrierDto) {
        serviceCarrier.add(carrierDto);
        return ResponseEntity.ok("Carrier is added ");
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<String> remove(@PathVariable("id") Integer id) {
        serviceCarrier.delete(id);
      return ResponseEntity.ok("Carrier is Deleted ");

    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<String>update(@PathVariable("id") Integer id,@RequestBody CarrierDto carrierDto) {
        serviceCarrier.update(id,carrierDto);
        return ResponseEntity.ok("Carrier is updated");
    }

}
