package lk.ijse.spring.controller;

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
    CustomerService service;

    @GetMapping(params = "email")
    public ResponseUtil checkCustomer(String email) {
        System.out.println(email);
        CustomerDTO customerDTO = service.searchCustomerByEmail(email);
        System.out.println(customerDTO);
        return new ResponseUtil("200", "Login Success", customerDTO);
    }
}
