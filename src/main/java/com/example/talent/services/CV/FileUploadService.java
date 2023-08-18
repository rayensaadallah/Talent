package com.example.talent.services.CV;

import com.example.talent.models.Cv;
import com.example.talent.repository.CVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileUploadService {
    @Autowired
    CVRepository cvr;


   public void saveCv(Cv cv) {
       cvr.save(cv);
   }

}
