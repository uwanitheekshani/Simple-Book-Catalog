package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.BookDTO;
import lk.ijse.spring.entity.Book;
import lk.ijse.spring.repo.BookRepo;
import lk.ijse.spring.service.BookService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author acer on 6/24/2023.
 * @project Book_Catalog_BackEnd
 */

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void addBook(BookDTO dto) {
        if (repo.existsById(dto.getBookId())) {
            throw new RuntimeException("Book "+dto.getBookId()+" Already Exist..!");
        }
        repo.save(mapper.map(dto, Book.class));
    }

    @Override
    public void deleteBook(String bookId) {
        if (!repo.existsById(bookId)){
            throw new RuntimeException("Book "+bookId+" Not Available to Delete..!");
        }
        repo.deleteById(bookId);
    }

    @Override
    public void updateBook(BookDTO dto) {
        if (!repo.existsById(dto.getBookId())){
            throw new RuntimeException("Book "+dto.getBookId()+" Not Available to Update..!");
        }
        repo.save( mapper.map(dto, Book.class));
    }

    @Override
    public ArrayList<BookDTO> getAllBooks() {
        return mapper.map(repo.findAll(), new TypeToken<ArrayList<BookDTO>>() {
        }.getType());
    }

    @Override
    public BookDTO searchBookByTitle(String title) {
        return mapper.map( repo.findBookByTitle(title),BookDTO.class);
    }

    @Override
    public ArrayList<BookDTO> searchByTitleOrAuthor(String searchTerm) {
        return mapper.map(repo.searchByTitleOrAuthor(searchTerm), new TypeToken<List<BookDTO>>() {
        }.getType());
    }
}
