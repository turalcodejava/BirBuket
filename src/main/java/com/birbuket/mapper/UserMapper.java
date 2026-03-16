package com.birbuket.mapper;


import com.birbuket.dto.UserRegisterRequest;
import com.birbuket.dto.UserRegisterResponse;
import com.birbuket.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toUserEntity(UserRegisterRequest userRegisterRequest);
    UserRegisterResponse toUserRegisterResponse(UserEntity userEntity);
}
