package com.example.talent.services.Formation;

import com.example.talent.dtos.Formationdto;
import com.example.talent.dtos.OffreDto;

import java.util.List;

public interface IFormationServices {

    List<Formationdto> getAll();

    void add(Formationdto f);

    void delete(Formationdto formationdto);

    public Formationdto update( Formationdto f);


    Formationdto getone(Formationdto formationdto);

    public void BuyFormation(Formationdto formationdto);
}
