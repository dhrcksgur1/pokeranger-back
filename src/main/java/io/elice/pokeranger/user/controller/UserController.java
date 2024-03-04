package io.elice.pokeranger.user.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import io.elice.pokeranger.order.entity.OrderResponseDTO;
import io.elice.pokeranger.user.entity.User;
import io.elice.pokeranger.user.entity.UserDTO;
import io.elice.pokeranger.user.repository.UserRepository;
import io.elice.pokeranger.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService,UserRepository userRepository) {
        this.userService =userService;
        this.userRepository  = userRepository;
    }


    @Operation(summary = "유저 생성 ", description = "userDTO정보로 신규 유저 추가 ")
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    // Read
    @Operation(summary = "전체 회원 조회 ", description = "모든 user 정보 조회  ")
    @GetMapping
    public ResponseEntity<List<User>> getUserListForAdmin() {
        List<User> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "유저 정보 요청 ", description = "id 에 해당하는 유저 반환 ")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        UserDTO userDTO = userService.getUserById(userId);
        return ResponseEntity.ok(userDTO);
    }


    @Operation(summary="로그인 임시 요청" , description = "id 에 해당하는 유저 정보 반환 ")
    @PostMapping("/{userId}/{password}")
    public ResponseEntity<UserDTO> loginUser(@PathVariable(name = "userId") long userId, @PathVariable(name = "password" ) String password) {
        UserDTO userDTO = userService.getUserById(userId);
        return ResponseEntity.ok(userDTO);
    }

    // put 맵핑

    @Operation(summary="유저 권한 수정" , description = "id 에 해당하는 유저 권한 수정 ")
    @PutMapping("/{userId}/roles")
    public ResponseEntity<UserDTO> updateUserRole(@PathVariable(name = "userId") Long userId, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(userId, userDTO);
        return ResponseEntity.ok(updatedUser);
    }



    @Operation(summary="유저 삭제" , description = "id 에 해당하는 유저 삭제 ")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable(name = "userId") Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user with ID " + userId);
        }
    }



}