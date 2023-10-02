package com.example.talent.services.Carrier;

import com.example.talent.dtos.CarrierDto;
import com.example.talent.dtos.UserDto;

import java.util.List;

public interface IServiceCarrier {
    List<CarrierDto> getAllCarriers();

    void add(CarrierDto carrierDto);

    void delete(Integer id);

    public void update( CarrierDto carrierDto);

    public void BuyCarrier(CarrierDto carrierDto) ;

    CarrierDto getCarrier(CarrierDto carrierDto);
}
