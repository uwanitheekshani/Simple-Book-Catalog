package lk.ijse.spring.controller;

import lk.ijse.spring.dto.UserDTO;
import lk.ijse.spring.service.UserService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author acer on 6/25/2023.
 * @project Book_Catalog_BackEnd
 */
@RestController
@RequestMapping("/userLogin")
@CrossOrigin
public class UserLoginController {
    @Autowired
    UserService service;

    @GetMapping(params = "email")
    public ResponseUtil checkUser(String email) {
        System.out.println(email);
        UserDTO userDTO = service.searchUserByEmail(email);
        System.out.println(userDTO);
        return new ResponseUtil("200", "Login Success", userDTO);
    }
}
