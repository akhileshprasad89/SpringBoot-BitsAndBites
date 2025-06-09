package com.lcwd.electronic.store.ElectronicStore.services.impl;

import com.lcwd.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.lcwd.electronic.store.ElectronicStore.dtos.UserDto;
import com.lcwd.electronic.store.ElectronicStore.entities.User;
import com.lcwd.electronic.store.ElectronicStore.exception.ResourceNotFoundException;
import com.lcwd.electronic.store.ElectronicStore.helper.Helper;
import com.lcwd.electronic.store.ElectronicStore.repositories.UserRepository;
import com.lcwd.electronic.store.ElectronicStore.services.FileService;
import com.lcwd.electronic.store.ElectronicStore.services.UserService;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {
    private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
    @Value("${user.profile.image.path}")
    private String imagePath;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDto CreateUser(UserDto userDto) {
        String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = dtoToEntity(userDto);
        User saveUser = userRepository.save(user);
        UserDto newDto = entityToDto(saveUser);
        return newDto;
    }



    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found with id"));
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());
        //save
        User updatedUser = userRepository.save(user);
        UserDto updatedDto = entityToDto(updatedUser);
        return updatedDto;
    }

    @Override
    public void delteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found with ID"));
        String fullPath = imagePath+user.getImageName();
        Path path= Paths.get(fullPath);
       try{
           Files.delete(path);
           userRepository.delete(user);
       }catch (NoSuchFileException ex){
           logger.info("user image not found ");
           ex.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) :(Sort.by(sortBy).ascending()) ;
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<User> page = userRepository.findAll(pageable);
        PageableResponse<UserDto> response = Helper.getPageableResponse(page, UserDto.class);
        return response;
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found with the Id"));
        return entityToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User Not Found With Email"));
        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> allUser = userRepository.findByNameContaining(keyword);
        List<UserDto> dtoUser = allUser.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        return dtoUser;
    }

    private UserDto entityToDto(User saveUser) {
//        UserDto userDto = UserDto.builder()
//                .userId(saveUser.getUserId())
//                .name(saveUser.getName())
//                .email(saveUser.getEmail())
//                .password(saveUser.getPassword())
//                .about(saveUser.getAbout())
//                .gender(saveUser.getGender())
//                .imageName(saveUser.getImageName()).build();
        return mapper.map(saveUser, UserDto.class);
    }

    private User dtoToEntity(UserDto userDto) {
//        User user = User.builder()
//                .userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .about(userDto.getAbout())
//                .gender(userDto.getGender())
//                .imageName(userDto.getImageName()).build();
        return mapper.map(userDto,User.class);
    }
}
