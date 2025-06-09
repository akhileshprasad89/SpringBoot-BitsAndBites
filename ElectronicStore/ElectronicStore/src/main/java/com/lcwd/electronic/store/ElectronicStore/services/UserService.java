package com.lcwd.electronic.store.ElectronicStore.services;

import com.lcwd.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.lcwd.electronic.store.ElectronicStore.dtos.UserDto;
import com.lcwd.electronic.store.ElectronicStore.entities.User;

import java.util.List;

public interface UserService {

    //create
    UserDto CreateUser(UserDto userDto);

    //update
    UserDto updateUser(UserDto userDto,String userId);

    //Delete
    void delteUser(String userId);

    //getAll User
    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);

    //Single User
    UserDto getUserById(String userId);

    //Get with emnail

    UserDto getUserByEmail(String email);

    //Search Use
    List<UserDto> searchUser(String keyword);

}
