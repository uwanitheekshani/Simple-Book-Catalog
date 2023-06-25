package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author acer on 6/24/2023.
 * @project Book_Catalog_BackEnd
 */
public interface BookRepo extends JpaRepository<Book,String> {

    Book findBookByTitle(String title);


    @Query(value = "SELECT * FROM book WHERE title LIKE %:searchTerm% OR author LIKE %:searchTerm%", nativeQuery = true)
    List<Book> searchByTitleOrAuthor(@Param("searchTerm") String searchTerm);
}
