package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.BookDTO;
import lk.ijse.spring.service.BookService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * @author acer on 6/24/2023.
 * @project Book_Catalog_BackEnd
 */

@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Override
    public void addBook(BookDTO dto) {

    }

    @Override
    public void deleteBook(String bookId) {

    }

    @Override
    public void updateBook(BookDTO dto) {

    }

    @Override
    public ArrayList<BookDTO> getAllBooks() {
        return null;
    }

    @Override
    public BookDTO searchBookByTitle(String title) {
        return null;
    }
}
