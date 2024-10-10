package com.lcwd.electronicStore.services.impl;

import com.lcwd.electronicStore.dtos.PageableResponse;
import com.lcwd.electronicStore.dtos.UserDto;
import com.lcwd.electronicStore.entities.User;
import com.lcwd.electronicStore.exception.ResourceNotFoundException;
import com.lcwd.electronicStore.helper.Helper;
import com.lcwd.electronicStore.repositories.UserRepository;
import com.lcwd.electronicStore.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class  UserServiceImpl implements UserService {
    private Logger logger= LoggerFactory.getLogger(UserService.class);
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserRepository userRepository;
    @Value("${user.profile.image.path}")
    private String imagePath;
    @Override
    public UserDto createUser(UserDto userDto) {
        //generating unique user id
        String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);

        // dto to entity
        User user=dtoToEntity(userDto);
        User savedUser=userRepository.save(user);
        //entity to dto
        UserDto newDto=entityToDto(savedUser);
        return  newDto;

    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User NotFound Exception !!.."));
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setImageName(userDto.getImageName());

        //save data
        User updatedUser = userRepository.save(user);
        UserDto userDto1 = entityToDto(updatedUser);

        return userDto1;
    }

    @Override
    public UserDto deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with given Id"));


        //delete user profile image
        // images/user/abc.png
        String fullPath=imagePath+user.getImageName();
       try {
           Path path = Paths.get(fullPath);
           Files.delete(path);
       }catch (NoSuchFileException ex) {
           logger.info("User image not found in folder");
           ex.printStackTrace();
       }catch
       (IOException e) {
           e.printStackTrace();
       }


        userRepository.delete(user);
        return null;
    }

    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {
        //pagenumber default start from 0
        Sort sort = (sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);

        Page<User> page= userRepository.findAll(pageable);
        PageableResponse<UserDto> pageableResponse = Helper.getPageableResponse(page, UserDto.class);

        return pageableResponse;

    }

     @Override
    public UserDto getUserById (String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with your given userId"));

        return entityToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("user not found with given email !!"));
        return  entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> users = userRepository.findByNameContaining(keyword);
        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        return dtoList;
    }
     // we convert mapping to dto mapping manually
    private  UserDto entityToDto(User savedUser) {
//        UserDto userDto = UserDto.builder()
//                .userId(savedUser.getUserId())
//                .name(savedUser.getName())
//                .email(savedUser.getEmail())
//                .password(savedUser.getPassword())
//                .about(savedUser.getAbout())
//                .gender(savedUser.getGender())
//                .imageName(savedUser.getImageName()).build();
        return mapper.map(savedUser,UserDto.class);
    }
    private User dtoToEntity(UserDto userDto) {
//        User user = User.builder().userId(userDto.getUserId())
//                .name((userDto.getName()))
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .about(userDto.getAbout())
//                .gender(userDto.getGender())
//                .imageName(userDto.getImageName()).build();
        return mapper.map(userDto,User.class);
    }
}
