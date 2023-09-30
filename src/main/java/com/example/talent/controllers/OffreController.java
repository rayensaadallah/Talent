package com.example.talent.controllers;



import com.example.talent.dtos.CarrierDto;
import com.example.talent.dtos.OffreDto;
import com.example.talent.services.Offre.IoffreService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/offer")
@CrossOrigin("*")
public class OffreController {

    IoffreService ioffreService;



    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN','USER','MANAGER')")
    public List<OffreDto> showAll(){
        List<OffreDto> list=ioffreService.getAlloffres();
        return list;
    }
    @GetMapping("/show")
    @PreAuthorize("hasAnyRole('ADMIN','USER','MANAGER')")
    public OffreDto showone(@RequestBody OffreDto dto){
        OffreDto ca  =ioffreService.getOne(dto);
        return  ca;
    }
    @PutMapping("/OffreToCarrier")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<String> assignCarrierToUser(@RequestBody OffreDto dto,@RequestBody CarrierDto carrierDto) {
        ioffreService.buyoffre(dto,carrierDto);
        return ResponseEntity.ok("Formation assigned to User successfully");
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<String> add(@RequestBody OffreDto dto) {
        ioffreService.add(dto);
        return ResponseEntity.ok("Formation is added ");
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<String> remove(@RequestBody OffreDto dto) {
        ioffreService.delete(dto);
        return ResponseEntity.ok("Formation is Deleted ");
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<String>update(@RequestBody OffreDto dto) {
        ioffreService.update(dto);
        return ResponseEntity.ok("Formation is updated");
    }

}
