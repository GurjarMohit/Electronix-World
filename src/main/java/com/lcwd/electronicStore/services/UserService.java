package com.lcwd.electronicStore.services;

import com.lcwd.electronicStore.dtos.PageableResponse;
import com.lcwd.electronicStore.dtos.UserDto;
import com.lcwd.electronicStore.entities.User;

import java.util.List;

public interface UserService {
  //create
   UserDto createUser(UserDto userDto);
    //update
    UserDto updateUser(UserDto userDto,String userId);

    //delete
    UserDto deleteUser(String userId);

    //get all user
   PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);

    //get a single user by Id
    UserDto getUserById(String userId);

    //get single user by email
   UserDto getUserByEmail(String email);

    //search user
   List<UserDto> searchUser(String keyword);
    //other user specific users

}
