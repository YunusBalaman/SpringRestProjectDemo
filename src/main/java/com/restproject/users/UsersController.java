package com.restproject.users;

import com.restproject.UserService;
import com.restproject.entity.User;
import com.restproject.users.util.UserPost;
import com.restproject.users.util.UserPut;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.Optional;

@RestController
class UsersController {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

   /** UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired(required = true)
    private UserRepository userRepository; */

    @Autowired(required = true)
    private UserService userService;

    @GetMapping(path={"/users","/users/{musteri_no}"}, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> getUsers(@PathVariable(required = false) String musteri_no, @RequestParam(required = false) String faketckimlikno){

        log.info(musteri_no);
        if (musteri_no != null){
            if(!NumberUtils.isDigits(musteri_no)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BadRequest.builder().responseCode("400")
                                .responseMessage("Müsteri No hatalı").build());
            }
            User user1 = userService.findByMusterino(Long.parseLong(musteri_no)).orElseGet(() -> null);
            return user1 != null ? ResponseEntity.status(200).body(user1) : ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(BadRequest.builder().responseCode("400")
                            .responseMessage("Müsteri No bulunamadı")
                            .build());
        }
        if(faketckimlikno != null){
            if(!NumberUtils.isDigits(faketckimlikno)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BadRequest.builder().responseCode("400")
                        .responseMessage("TC No hatalı").build());
            }
            User user1 = userService.findByFaketckimlikno(Long.parseLong(faketckimlikno)).orElseGet(() -> null);
            return user1 != null ? ResponseEntity.status(200).body(user1) : ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(BadRequest.builder().responseCode("400")
                            .responseMessage("TC No bulunamadı")
                            .build());
        }
        return ResponseEntity.status(200).body(userService.findAll());
    }

    /**
    @GetMapping(path="/users/{musteri_no}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> getUsersByCustomerNo(@PathVariable(required = true) String musteri_no){

        if (musteri_no == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(BadRequest.builder().responseCode("400")
                            .responseMessage("Müsteri No bulunamadı")
                            .build());
        }
        if(!NumberUtils.isDigits(musteri_no)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BadRequest.builder().responseCode("400")
                    .responseMessage("Müsteri No hatalı").build());
        }
        User user1 = userService.findByMusterino(Long.parseLong(musteri_no)).orElseGet(() -> null);
        return user1 != null ? ResponseEntity.status(200).body(user1) : ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(BadRequest.builder().responseCode("400")
                        .responseMessage("Müsteri No bulunamadı")
                        .build());
    }
    */

    // create employee rest api
    @PostMapping(path="/users", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createUser(@RequestBody UserPost userPost) {
        if (userPost == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(BadRequest.builder().responseCode("400")
                            .responseMessage("user datası bulunamadı")
                            .build());
        }
        if (userService.existsByFaketckimlikno(userPost.getFaketckimlikno())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(BadRequest.builder().responseCode("400")
                            .responseMessage("Fake Tc Kimlik No zaten mevcut")
                            .build());
        }
        User user = new User(userPost.getFaketckimlikno(), userPost.getName(), userPost.getSurname()
                , userPost.getBirthdate(), userPost.getAddress());
        user.setMusterino(System.currentTimeMillis());
        log.info("MusteriNo: " + user.getMusterino());
        return ResponseEntity.status(200).body(userService.save(user));
    }

    @PutMapping("/users/{musteri_no}")
    public ResponseEntity<Object> putUserByCustomerNo(@PathVariable(required = true) String musteri_no, @RequestBody UserPut userBody){

        /**
        if (musteri_no == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(BadRequest.builder().responseCode("400")
                            .responseMessage("Musteri No boş olamaz")
                            .build());
        } */
        if(!NumberUtils.isDigits(musteri_no)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BadRequest.builder().responseCode("400")
                    .responseMessage("Müsteri No hatalı").build());
        }
       return setMapForPutUser(userService.findByMusterino(Long.parseLong(musteri_no)), userBody);

    }

    @PutMapping("/users")
    public ResponseEntity<Object> putFakeTCNo(@RequestParam(required = true) String faketckimlikno, @RequestBody UserPut userBody){

        /**
        if (faketckimlikno == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(BadRequest.builder().responseCode("400")
                            .responseMessage("Tc No boş olamaz")
                            .build());
        }
         */
        if(!NumberUtils.isDigits(faketckimlikno)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BadRequest.builder().responseCode("400")
                    .responseMessage("TC No hatalı").build());
        }
        return setMapForPutUser(userService.findByFaketckimlikno(Long.parseLong(faketckimlikno)), userBody);

    }

    public ResponseEntity<Object> setMapForPutUser(Optional<User> userOptional, UserPut userBody){
        //
        User newUserBody = userOptional.map(user -> {
            if (userBody.getName() != null && !userBody.getName().trim().equals("")) {
                user.setName(userBody.getName());
            }
            if (userBody.getSurname() != null && !userBody.getName().trim().equals("")) {
                user.setSurname(userBody.getSurname());
            }
            if (userBody.getAddress() != null) {
                user.setAddress(userBody.getAddress());
            }
           if (userBody.getBirthdate() != null) {
             user.setBirthdate(userBody.getBirthdate());
           }
            return userService.save(user);
        }) //.orElseGet(() -> {return null;})
                .orElseGet(() -> null);
        return newUserBody != null ? ResponseEntity.status(200).body(newUserBody) : ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(BadRequest.builder().responseCode("400")
                        .responseMessage("Müsteri No bulunamadı")
                        .build());
    }

}
