package lk.ijse.spring.service;

import lk.ijse.spring.dto.UserDTO;

/**
 * @author acer on 6/25/2023.
 * @project Book_Catalog_BackEnd
 */
public interface UserService {

    UserDTO searchUserByEmail(String email);
}
