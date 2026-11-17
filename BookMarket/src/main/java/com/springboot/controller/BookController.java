package com.springboot.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.domain.Book;
import com.springboot.service.BookService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Value("${file.uploadDir}")
    String fileDir;

    @GetMapping
    public String requestBookList(Model model) {

        List<Book> list = bookService.getAllBookList();

        model.addAttribute("bookList", list);

        return "books";
    }

    @GetMapping("/all")
    public ModelAndView requestAllBooks() {

        ModelAndView modelAndView = new ModelAndView();

        List<Book> list = bookService.getAllBookList();

        modelAndView.addObject("bookList", list);

        modelAndView.setViewName("books");

        return modelAndView;
    }

    @GetMapping("/book")
    public String requestBookById(
            @RequestParam("id") String bookId, Model model) {

        Book bookById = bookService.getBookById(bookId);

        model.addAttribute("book", bookById);

        return "book";
    }

    @GetMapping("/{category}")
    public String requestBookByCategory(
            @PathVariable("category") String bookCategory,
            Model model) {

        List<Book> booksByCategory =
                bookService.getBookListByCategory(bookCategory);

        model.addAttribute("bookList", booksByCategory);

        return "books";
    }

    @GetMapping("/filter/{bookFilter}")
    public String requestBooksByFilter(
            @MatrixVariable(pathVar = "bookFilter")
            Map<String, List<String>> bookFilter,
            Model model) {

        Set<Book> booksByFilter =
                bookService.getBookListByFilter(bookFilter);

        model.addAttribute("bookList", booksByFilter);

        return "books";
    }

    @GetMapping("/add")
    public String requestAddBookForm(Model model) {

    	model.addAttribute("book",new Book());
        return "addBook";
        
        
    }

    @PostMapping("/add")
    public String submitAddNewBook(
            @Valid @ModelAttribute Book book, BindingResult bindingResult) {
    	if(bindingResult.hasErrors())
    		return "addBook";
        MultipartFile bookImage = book.getBookImage();

        if (bookImage != null && !bookImage.isEmpty()) {

            String saveName = bookImage.getOriginalFilename();

            File saveFile = new File(fileDir, saveName);

            try {
                bookImage.transferTo(saveFile);

            } catch (Exception e) {
                throw new RuntimeException(
                        "도서 이미지 업로드가 실패하였습니다", e);
            }

            book.setFileName(saveName);
        }

        bookService.setNewBook(book);

        return "redirect:/books";
    }

    @ModelAttribute
    public void addAttributes(Model model) {

        model.addAttribute("addTitle", "신규도서 등록");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        binder.setAllowedFields(
                "bookid",
                "name",
                "unitPrice",
                "author",
                "description",
                "publisher",
                "category",
                "unitsInStock",
                "releaseDate",
                "condition",
                "bookImage"
        );
    }
}