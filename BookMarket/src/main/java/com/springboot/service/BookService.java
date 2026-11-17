package com.springboot.service;

import java.util.List;
import java.util.Map;
import java.util.Set;


import com.springboot.domain.Book;

public interface BookService {

    public List<Book> getAllBookList();

    public Book getBookById(String bookId);
    void setNewBook(Book book);
    public List<Book> getBookListByCategory(String category);
    Set<Book> getBookListByFilter(Map<String, List<String>> filter);
}
