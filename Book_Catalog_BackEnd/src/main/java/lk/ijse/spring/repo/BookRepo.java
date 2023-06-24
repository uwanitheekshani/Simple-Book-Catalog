package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author acer on 6/24/2023.
 * @project Book_Catalog_BackEnd
 */
public interface BookRepo extends JpaRepository<Book,String> {

    Book findBookByTitle(String title);
}
