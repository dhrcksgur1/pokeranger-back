package io.elice.pokeranger.user.controller;

import io.elice.pokeranger.global.security.jwt.JwtFilter;
import io.elice.pokeranger.global.security.jwt.TokenProvider;
import io.elice.pokeranger.user.entity.*;
import io.elice.pokeranger.user.repository.UserRepository;
import io.elice.pokeranger.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private  TokenProvider tokenProvider;
    private  AuthenticationManagerBuilder authenticationManagerBuilder;


    private UserService userService;
    private UserRepository userRepository;
    private  PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository, TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, PasswordEncoder passwordEncoder) {
        this.userService =userService;
        this.userRepository  = userRepository;
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.passwordEncoder =  passwordEncoder;
    }

    // 프론트엔드의 api.post(/login) 요청
    @Operation(summary = "인증 정보 획득 ", description = "유저 인증 정보 획득  ")
    @PostMapping("/login")
    public ResponseEntity<LoginResponceDTO> authorize(@RequestBody LoginDTO loginDto) {

        System.out.println(loginDto.getEmail());
        System.out.println(loginDto.getPassword());



        User user =  userService.getUserPasswordHash(loginDto);



        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());


        System.out.println(authenticationToken);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        System.out.println(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

                                        // jwt 토큰  , 권한헤더 , responce OK

        return new ResponseEntity<>(new LoginResponceDTO(jwt, user.getType(), user.getId()), httpHeaders, HttpStatus.OK);
    }

    @Operation(summary = "유저 생성 ", description = "userDTO정보로 신규 유저 추가 ")
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody RegisterDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    // Read
    @Operation(summary = "전체 회원 조회 ", description = "모든 user 정보 조회  ")
    @GetMapping
    public ResponseEntity<Page<UserDTO>> getUserListForAdmin(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "20") int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<UserDTO> users = userService.getAll(pageRequest);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @Operation(summary = "유저 정보 요청 ", description = "id 에 해당하는 유저 반환 ")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        UserDTO userDTO = userService.getUserById(userId);
        return ResponseEntity.ok(userDTO);
    }


//    @Operation(summary="로그인 임시 요청" , description = "id 에 해당하는 유저 정보 반환 ")
//    @PostMapping("/{userId}/{password}")
//    public ResponseEntity<UserDTO> loginUser(@PathVariable long userId, @PathVariable String password) {
//        UserDTO userDTO = userService.getUserById(userId);
//        return ResponseEntity.ok(userDTO);
//    }


    @Operation(summary="패스워드 체크 " , description = "유저의 패스워드 체크  ")
    @PostMapping("/password-check")
    public ResponseEntity<Long> checkPassword(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.checkPasswordHash(userDTO));
    }
    // put 맵핑

    @Operation(summary="유저 권한 수정" , description = "id 에 해당하는 유저 권한 수정 ")
    @PatchMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUserRole(@PathVariable Long userId, @RequestBody UserTypeDTO userTypeDTO) {
        UserDTO updatedUser = userService.userRoleChange(userId, userTypeDTO);
        return ResponseEntity.ok(updatedUser);
    }


    @Operation(summary="유저 정보 수정" , description = "id 에 해당하는 유저 정보 수정 ")
    @PatchMapping("/modify/{userId}")
    public ResponseEntity<UserDTO> updateUserData(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(userId, userDTO);
        return ResponseEntity.ok(updatedUser);
    }



    @Operation(summary="유저 삭제" , description = "id 에 해당하는 유저 삭제 ")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long userId) {
        try {
            // 캐스케이드 ?

            userService.deleteUser(userId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user with ID " + userId);
        }
    }




}