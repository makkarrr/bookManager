package com.akanksha_acciojob.bookManager;

import java.util.*;

public class BookRepository {
    private Map<Integer,Book> bookData = new HashMap<>();

    public void add(Book book) {
        bookData.put(book.getId(), book);
    }

    public Optional<Book> getById(Integer id) {
        if(bookData.containsKey(id)) {
            Book bookToReturn = bookData.get(id);
            return Optional.of(bookToReturn); //List.of(1,2,3)
        }
        return Optional.empty();
    }

    public List<Book> getAll() {
        return new ArrayList<>(bookData.values());
    }

    public void update(Book book) {
        bookData.put(book.getId(),book);
    }

    public void delete(Integer id) {
        bookData.remove(id);
    }
}
