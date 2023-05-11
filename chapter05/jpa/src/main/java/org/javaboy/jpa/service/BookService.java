package org.javaboy.jpa.service;

import org.javaboy.jpa.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author szh
 */
@Service
public class BookService {

    @Autowired
    BookDao bookDao;

    @Transactional(rollbackFor = Exception.class)
    public void updateBookById(String name, Long id) {
        bookDao.updateBookById(name, id);
    }

}
