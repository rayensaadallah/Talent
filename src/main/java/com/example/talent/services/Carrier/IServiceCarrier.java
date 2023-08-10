package com.example.talent.services.Carrier;

import com.example.talent.dtos.CarrierDto;

import java.util.List;

public interface IServiceCarrier {
    List<CarrierDto> getAllCarriers();

    void add(CarrierDto carrierDto);

    void delete(Integer id);

    public void update(Integer id, CarrierDto carrierDto);

    public void assignCarrierToUser(Integer carrierId, Integer userId) ;

    CarrierDto getCarrier(Integer carrierId);
}
