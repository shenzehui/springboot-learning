package org.javaboy.jpa.dao;

import org.javaboy.jpa.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author szh
 */
public interface BookDao extends JpaRepository<Book, Long> {

    /**
     * get find read 命名规范参考文档
     *
     * @param author
     * @return
     */
    List<Book> getBookByAuthorEquals(String author);

    /**
     * 自定义
     * 是否用原生的sql
     *
     * @return
     */
    @Query(nativeQuery = true, value = "select * from t_book where id = (select max(id) from t_book)")
    Book maxIdBook();

    /**
     * 自定义更新操作
     * * 1、添加 @Modifying
     * * 2、在 service 实现类添加事务注解 @Transactional (Jpa默认对自定义的操作只有只读权限)
     *
     * @param name
     * @param id
     */
    @Query(nativeQuery = true, value = "update t_book set b_name = :name where id = :id")
    @Modifying
    void updateBookById(String name, Long id);

}
