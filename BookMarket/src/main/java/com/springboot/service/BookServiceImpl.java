package com.springboot.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.domain.Book;
import com.springboot.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBookList() {
        List<Book> books = bookRepository.getAllBookList();
        return books;
    }

    public Book getBookById(String bookId) {
        Book bookById = bookRepository.getBookById(bookId);
        return bookById;
    }

    @Override
    public List<Book> getBookListByCategory(String category) {
        List<Book> booksByCategory =
                bookRepository.getBookListByCategory(category);

        return booksByCategory;
    }
    public Set<Book> getBookListByFilter(Map<String, List<String>> filter){
    	Set<Book> booksByFilter = bookRepository.getBookListByFilter(filter);
    	return booksByFilter;
    }
    public void setNewBook(Book book) {
    	bookRepository.setNewBook(book);
    }
}