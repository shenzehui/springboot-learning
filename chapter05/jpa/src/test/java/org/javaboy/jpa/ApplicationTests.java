package org.javaboy.jpa;

import org.javaboy.jpa.dao.BookDao;
import org.javaboy.jpa.model.Book;
import org.javaboy.jpa.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private BookDao dao;

    @Test
    void contextLoads() {
        Book book = new Book();
        book.setName("三国演义");
        book.setAuthor("罗贯中");
        dao.save(book);
    }

    @Test
    void  test1(){
        List<Book> list = dao.findAll();
        System.out.println("list = " + list);
        Optional<Book> byId = dao.findById(1L);
        System.out.println("byId = " + byId);
    }

    @Test
    void test2(){
        //页面从0开始记录
        PageRequest pageRequest = PageRequest.of(0, 2, Sort.by(Sort.Order.desc("id")));
        Page<Book> page = dao.findAll(pageRequest);
        System.out.println("总记录数 = " + page.getTotalElements());
        System.out.println("总页数 = " + page.getTotalPages());
        System.out.println("查到的数据 = " + page.getContent());
        System.out.println("每页的记录数 = " + page.getSize());
        System.out.println("是否还有下一页 = " + page.hasNext());
        System.out.println("是否还有上一页 = " + page.hasPrevious());
        System.out.println("是否第一页 = " + page.isFirst());
        System.out.println("是否最后一页 = " + page.isLast());
        System.out.println("当前页面 = " + page.getNumber());
        System.out.println("当前页记录数 = " + page.getNumberOfElements());
    }

    @Test
    void test3(){
        List<Book> list = dao.getBookByAuthorEquals("罗贯中");
        System.out.println("list = " + list);
    }

    @Test
    void test4(){
        Book book = dao.maxIdBook();
        System.out.println("book = " + book);
    }

    @Autowired
    BookService bookService;

    @Test
    void test5(){
        bookService.updateBookById("123", 1L);
    }


}
