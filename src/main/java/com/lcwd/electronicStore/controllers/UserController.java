package com.lcwd.electronicStore.controllers;

import com.lcwd.electronicStore.dtos.ApiResponseMessage;
import com.lcwd.electronicStore.dtos.ImageResponse;
import com.lcwd.electronicStore.dtos.PageableResponse;
import com.lcwd.electronicStore.dtos.UserDto;
import com.lcwd.electronicStore.services.FileService;
import com.lcwd.electronicStore.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;
     @Value("${user.profile.image.path}")
    private  String imageUploadPath;

     private Logger logger= LoggerFactory.getLogger(UserController.class);
  //create
    @PostMapping
    public ResponseEntity<UserDto>CreateUser(@Valid@RequestBody UserDto userDto){
        UserDto user = userService.createUser( userDto);
        return  new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    //delete
    @DeleteMapping("/{userId}")
    //without ApiResponseMessage
//     public ResponseEntity<String>deleteUser(@PathVariable String userId){
//           userService.deleteUser(userId);
//           return  new ResponseEntity<>("user is deleted successfully",HttpStatus.OK);
//
//     }
         public ResponseEntity<ApiResponseMessage>deleteUser(@PathVariable String userId){
           userService.deleteUser(userId);
        ApiResponseMessage message = ApiResponseMessage
                .builder()
                .message("user is deleted successfully!!")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return  new ResponseEntity<>(message,HttpStatus.OK);

     }


    //update
      @PutMapping("/{userId}")
    public  ResponseEntity<UserDto> updateUser(
           @Valid @PathVariable("userId") String userId,
            @RequestBody UserDto userDto
      ){
        UserDto updatedUserDto=userService.updateUser(userDto,userId);
        return  new ResponseEntity<>(updatedUserDto,HttpStatus.OK);

    }

    //get all
     @GetMapping
    public  ResponseEntity<PageableResponse<UserDto>> getAllUser(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int  pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int  pageSize,
            @RequestParam(value = "sortBy",defaultValue = "name",required = false) String  sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String  sortDir

     ){
        return  new ResponseEntity<>(userService.getAllUser(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
     }
    //get single
    @GetMapping("/{userId}")
    public  ResponseEntity<UserDto> getSingleUser(@PathVariable String userId){
        return  new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }

    //get by email
   @GetMapping("/email/{email}")
    public  ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){
        return  new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.OK);
   }
    //search user
    @GetMapping("/search/{keyword}")
    public ResponseEntity<UserDto> searchByKeyword(@PathVariable("keyword") String keyword) {
        UserDto userDto = userService.getUserByEmail(keyword);
        return ResponseEntity.ok(userDto);
    }


    //upload user Image
    @PostMapping("images/users/")
    public  ResponseEntity<ImageResponse> uploadUserImage(@RequestParam("userImage")MultipartFile image,@PathVariable String userId) throws IOException {
        String imageName = fileService.uploadFile(image, imageUploadPath);

        UserDto user = userService.getUserById(userId);
        user.setImageName(imageName);
        UserDto userDto = userService.updateUser(user, userId);
        ImageResponse imageResponse = ImageResponse.builder().imageName(imageName).success(true).status(HttpStatus.CREATED).build();
        return  new ResponseEntity<>(imageResponse,HttpStatus.CREATED);
    }

    //serve user image
  @GetMapping("images/users/")
    public void serveUserImage(@PathVariable String userId, HttpServletResponse response) throws IOException {
        UserDto user=userService.getUserById(userId);
      InputStream resource=fileService.getResource(imageUploadPath,user.getImageName());
      response.setContentType(MediaType.IMAGE_JPEG_VALUE);
      StreamUtils.copy(resource,response.getOutputStream());

  }
}
