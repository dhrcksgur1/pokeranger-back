package io.elice.pokeranger.user.controller;

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



    // Create
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    // Read
    /*
    -> 회원 전체 정보 읽어오는 API   //user 도메인
    요청 타입 : get
    endPoint :
    요청명 : getUserListForAdmin
    반환값 : ResponseEntity<List<UserDTO>>

	*/
    @GetMapping
    public ResponseEntity<List<User>> getUserListForAdmin() {
        List<User> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        UserDTO userDTO = userService.getUserById(userId);
        return ResponseEntity.ok(userDTO);
    }


    // Update
      /*

-> 회원 권한수정 //user 도메인
    요청 타입 : patch
    endPoint : {userId}/roles
            (함수명) :updateUserRole
    반환값 :  ResponseEntity<List<UserDTO>>
            reponseOK // DTO는 적으면 적을수록 좋다. 바뀐 최신화 정보인 role을 내려주도록 하자 -> 확장성이 좋다
            . ReqeustBody로
*/

    // put 맵핑

    @PutMapping("/{userId}/roles")
    public ResponseEntity<UserDTO> updateUserRole(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(userId, userDTO);
        return ResponseEntity.ok(updatedUser);
    }




    // Delete
    /*

-> 회원 삭제 //user 도메인
    요청 타입 : delete
    endPoint  :{userId}
    함수명 : deleteUser
    반환값 :   response OK
*/
    // delete 맵핑
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }



}