package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.Greeting;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

  private final Greeting greeting;
  private final UserService userService;

  @GetMapping("/health_check")
  public String status(HttpServletRequest request) {
    return "Working : " + request.getServerPort();
  }

  @GetMapping("/welcome")
  public String welcome() {
    return greeting.getMessage();
  }

  @PostMapping("/users")
  public ResponseEntity createUser(@RequestBody RequestUser user) {

    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    UserDto userDto = modelMapper.map(user, UserDto.class);
    userService.createUser(userDto);

    ResponseUser responseUser = modelMapper.map(userDto, ResponseUser.class);

    return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
  }

  @GetMapping("/users")
  public ResponseEntity getUsers() {
    Iterable<UserEntity> userList = userService.getUserByAll();

    ArrayList<Object> result = new ArrayList<>();
    userList.forEach(v -> result.add(new ModelMapper().map(v, ResponseUser.class)));

    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @GetMapping("/users/{userId}")
  public ResponseEntity getUser(@PathVariable String userId) {

    UserDto userDto = userService.getUserByUserId(userId);
    ResponseUser result = new ModelMapper().map(userDto, ResponseUser.class);

    return ResponseEntity.status(HttpStatus.OK).body(result);
  }


}
