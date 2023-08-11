package com.example.talent.services.Offre;

import com.example.talent.dtos.OffreDto;

import java.util.List;

public interface IoffreService {

    List<OffreDto> getAlloffres();

    void add(OffreDto offreDto);

    void delete(Integer id);

    public OffreDto update(Integer id, OffreDto offreDto);


    OffreDto getoneoffre(Integer id);


}
