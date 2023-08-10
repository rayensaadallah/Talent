package com.example.talent.Mappers;

import com.example.talent.dtos.CarrierDto;
import com.example.talent.dtos.UserDto;
import com.example.talent.models.Carrier;
import com.example.talent.models.Users;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CarrierMappers {

    public CarrierDto fromBasic(Carrier u){
        CarrierDto dto=new CarrierDto();
        BeanUtils.copyProperties(u,dto);
        return  dto;
    }
    public Carrier fromDTO(CarrierDto dto){
        Carrier u=new Carrier();
        BeanUtils.copyProperties(dto,u);
        return  u;
    }
}
