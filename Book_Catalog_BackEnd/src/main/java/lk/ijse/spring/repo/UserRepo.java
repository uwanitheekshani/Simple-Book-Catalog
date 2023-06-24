package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Book;
import lk.ijse.spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author acer on 6/25/2023.
 * @project Book_Catalog_BackEnd
 */
public interface UserRepo extends JpaRepository<User,String> {
    User getUserByEmail(String email);
}
