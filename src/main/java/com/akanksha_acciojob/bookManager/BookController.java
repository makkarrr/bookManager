package com.akanksha_acciojob.bookManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    private BookService bookService = new BookService();

    @PostMapping("/add-new-book")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        //bookData.put(book.getId(), book);
        String str = bookService.addBook(book);
        return new ResponseEntity<>(str,HttpStatus.CREATED);
    }

    @GetMapping("/get-book") // google.com/search?q=yes_bank // localhost:8080/get-book?id=1
    public ResponseEntity<?> getBook(@RequestParam Integer id) { // @RequestParam String q
        try {
            Book book = bookService.getBook(id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch(RuntimeException ex) {
            System.out.println("did not find book");
            return new ResponseEntity<>("Book with id not found : " + id, HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/get-book-by-name/{name}") // /get-book-by-name/harry-potter
    public ResponseEntity<Book> getBookByName(@PathVariable String name) {
        try {
            return new ResponseEntity<>(bookService.getBook(name), HttpStatus.OK);
        } catch(BookNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-book-page/{id}/{pages}") //update-book-pages/1/500
    public ResponseEntity<Book> updateBookPages(@PathVariable Integer id, @PathVariable Integer pages) {
        try {
            Book book = bookService.updateBookPages(id, pages);
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        } catch(BookNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
//
//    @PutMapping("/update-book-page") //update-book-pages/1/500
//    public Book updateBookAuthor(@RequestParam Integer id, @RequestParam String author) {
//        Book book = bookData.get(id);
//        book.setAuthor(author);
//        bookData.put(id, book);
//        return book;
//    }
//
    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
        bookService.removeBookById(id);
        return new ResponseEntity<>("book deleted with id: " + id, HttpStatus.OK);
    }

}
