package com.example.talent.Mappers;

import com.example.talent.dtos.UserDto;
import com.example.talent.models.Users;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserMappers {

    public UserDto fromBasic(Users u){
        UserDto dto=new UserDto();
        BeanUtils.copyProperties(u,dto);
        return  dto;
    }
    public Users fromDTO(UserDto dto){
        Users u=new Users();
        BeanUtils.copyProperties(dto,u);
        return  u;
    }
}
