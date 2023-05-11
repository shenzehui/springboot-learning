package org.javaboy.sbdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class SbdemoApplicationTests {

    @Autowired
    Author author;

    @Autowired
    MyConfig config;

    @Autowired
    Book book;

    @Test
    public void contextLoads() {
        System.out.println(author);
        System.out.println("config = "+ config);
        System.out.println("author==book.getAuthor()=" + (book.getAuthor() == author));
    }

}
