package com.lcwd.electronic.store.ElectronicStore.controllers;

import com.lcwd.electronic.store.ElectronicStore.dtos.ApiResponseMessage;
import com.lcwd.electronic.store.ElectronicStore.dtos.ImageResponse;
import com.lcwd.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.lcwd.electronic.store.ElectronicStore.dtos.UserDto;
import com.lcwd.electronic.store.ElectronicStore.services.FileService;
import com.lcwd.electronic.store.ElectronicStore.services.UserService;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
   private Logger logger= LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;
    @Value("${user.profile.image.path}")
    private  String imageUploadPath;
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto userDto1 = userService.CreateUser(userDto);
        return new  ResponseEntity<>(userDto1,HttpStatus.CREATED);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") String userId){
        UserDto userDto1 = userService.updateUser(userDto, userId);
        return new ResponseEntity<>(userDto1,HttpStatus.OK);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable("userId") String userId){
        userService.delteUser(userId);
        ApiResponseMessage userDeletedSuccess = ApiResponseMessage.builder().message("User Deleted Success").success(true).status(HttpStatus.OK).build();
        return new ResponseEntity<>(userDeletedSuccess,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<PageableResponse<UserDto>> getAllUser(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "name",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
            ){
        PageableResponse<UserDto> allUser = userService.getAllUser(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(allUser,HttpStatus.OK);
    }
    @GetMapping("{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") String userId){
        UserDto userById = userService.getUserById(userId);
        return new ResponseEntity<>(userById,HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable("email") String email){
        UserDto userByEmail = userService.getUserByEmail(email);
        return new ResponseEntity<>(userByEmail,HttpStatus.OK);
    }
    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable("keywords") String keywords){
        List<UserDto> userDtos = userService.searchUser(keywords);
        return new ResponseEntity<>(userDtos,HttpStatus.OK);
    }
    @PostMapping("/image/{userId}")
    public ResponseEntity<ImageResponse> uploadUserImage(@RequestParam("imageName") MultipartFile image,@PathVariable String userId) throws IOException {
        String imageName = fileService.uploadFile(image, imageUploadPath);
        UserDto user = userService.getUserById(userId);
        user.setImageName(imageName);
        UserDto userDto = userService.updateUser(user, userId);
        ImageResponse imageResponse=ImageResponse.builder().imageName(imageName).success(true).status(HttpStatus.CREATED).build();
        return new ResponseEntity<>(imageResponse,HttpStatus.CREATED);
    }
    @GetMapping("/image/{userId}")
    public void serveImage(@PathVariable("userId") String userId, HttpServletResponse response) throws IOException {
        UserDto user = userService.getUserById(userId);
        logger.info("Usr image Name {}",user.getImageName());
        InputStream resource = fileService.getResource(imageUploadPath, user.getImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}
