package lk.ijse.spring.controller;

import lk.ijse.spring.dto.BookDTO;
import lk.ijse.spring.service.BookService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @author acer on 6/24/2023.
 * @project Book_Catalog_BackEnd
 */
@RestController
@RequestMapping("/book")
@CrossOrigin
public class BookController {
    @Autowired
    private BookService service;


    @PostMapping
    public ResponseUtil saveBook(@ModelAttribute BookDTO dto){
        service.addBook(dto);
        return new ResponseUtil("200",dto.getBookId()+ " Added.!",null);
    }

    @PutMapping
    public ResponseUtil updateBook(@RequestBody BookDTO dto){
        service.updateBook(dto);
        return new ResponseUtil("200",dto.getBookId()+": Updated.!",null);
    }

    @DeleteMapping(params = "id")
    public ResponseUtil deleteBook(String id){
        service.deleteBook(id);
        return new ResponseUtil("200",id+" : Deleted.!",null);
    }

    @GetMapping
    public ResponseUtil getAllBook(){
        ArrayList<BookDTO> allBooks = service.getAllBooks();
        return new ResponseUtil("200"," Success.!",getAllBook());
    }

    @GetMapping(params = "title")
    public ResponseUtil searchBookByTitle(String title){
        BookDTO book = service.searchBookByTitle(title);
        return new ResponseUtil("200"," Success.!",book);
    }
}
