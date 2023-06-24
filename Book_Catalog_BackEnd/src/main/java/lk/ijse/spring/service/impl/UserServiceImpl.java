package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.BookDTO;
import lk.ijse.spring.dto.UserDTO;
import lk.ijse.spring.entity.User;
import lk.ijse.spring.repo.BookRepo;
import lk.ijse.spring.repo.UserRepo;
import lk.ijse.spring.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author acer on 6/25/2023.
 * @project Book_Catalog_BackEnd
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private ModelMapper mapper;
    @Override
    public UserDTO searchUserByEmail(String email) {
        return mapper.map( repo.getUserByEmail(email), UserDTO.class);
    }
}
