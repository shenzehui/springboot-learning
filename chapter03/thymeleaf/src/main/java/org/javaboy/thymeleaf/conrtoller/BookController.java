package org.javaboy.thymeleaf.conrtoller;

import org.javaboy.thymeleaf.bean.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author szh
 */
@Controller
public class BookController {

    @GetMapping("/book")
    public String book(Model model, HttpSession session){
        session.setAttribute("name", "江南的一点");
        List<Book> bookList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setId(i);
            book.setName("三国演义:"+ i);
            book.setAuthor("罗贯中"+ i);
            book.setPrice(30.00);
            bookList.add(book);
        }
        model.addAttribute("books", bookList);
        Book book = new Book();
        book.setId(1);
        book.setName("西游记");
        book.setAuthor("吴承恩");
        book.setPrice(66.66);
        model.addAttribute("book", book);
        return "book";
    }
}
