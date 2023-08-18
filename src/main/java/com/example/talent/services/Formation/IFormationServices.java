package com.example.talent.services.Formation;

import com.example.talent.dtos.Formationdto;
import com.example.talent.dtos.OffreDto;

import java.util.List;

public interface IFormationServices {

    List<Formationdto> getAll();

    void add(Formationdto f);

    void delete(Integer id);

    public Formationdto update(Integer id, Formationdto f);


    Formationdto getone(Integer id);

    public void BuyFormation(Integer id);
}
