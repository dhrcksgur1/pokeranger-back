package io.elice.pokeranger.user.controller;

import io.elice.pokeranger.global.security.jwt.JwtFilter;
import io.elice.pokeranger.global.security.jwt.TokenProvider;
import io.elice.pokeranger.user.entity.LoginDTO;
import io.elice.pokeranger.user.entity.TokenDTO;
import io.elice.pokeranger.user.entity.User;
import io.elice.pokeranger.user.entity.UserDTO;
import io.elice.pokeranger.user.repository.UserRepository;
import io.elice.pokeranger.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private  TokenProvider tokenProvider;
    private  AuthenticationManagerBuilder authenticationManagerBuilder;


    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository, TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.userService =userService;
        this.userRepository  = userRepository;
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    // 프론트엔드의 api.post(/login) 요청
    @Operation(summary = "인증 정보 획득 ", description = "유저 인증 정보 획득  ")
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> authorize(@RequestBody LoginDTO loginDto) {

        System.out.println(loginDto.getEmail());
        System.out.println(loginDto.getPassword());
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

                                        // jwt 토큰  , 권한헤더 , responce OK
        return new ResponseEntity<>(new TokenDTO(jwt), httpHeaders, HttpStatus.OK);
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