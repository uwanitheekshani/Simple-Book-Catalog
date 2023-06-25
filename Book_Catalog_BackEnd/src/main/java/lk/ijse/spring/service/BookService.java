package lk.ijse.spring.service;

import lk.ijse.spring.dto.BookDTO;
import lk.ijse.spring.entity.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * @author acer on 6/24/2023.
 * @project Book_Catalog_BackEnd
 */
public interface BookService {


    public void addBook(BookDTO dto);

    public void deleteBook(String bookId);

    public void updateBook(BookDTO dto);

    public ArrayList<BookDTO> getAllBooks();

    public BookDTO searchBookByTitle(String title);

    public List<Book> searchByTitleOrAuthor(String searchTerm);
}
