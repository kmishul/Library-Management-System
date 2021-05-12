package com.project.library_management.repository;

import com.project.library_management.model.Book;
import com.project.library_management.model.IssuedBook;
import com.project.library_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IssuedBookRepository extends JpaRepository<IssuedBook,Integer> {

    @Query(value = "SELECT * FROM user WHERE user.id IN (SELECT issued_book.user_id FROM issued_book WHERE issued_book.book_id= :bookid)", nativeQuery = true)
    List<Integer> findUsersByBookId(@Param("bookid") int bookid);

    @Query(value = "SELECT * FROM book WHERE book.id IN (SELECT issued_book.book_id FROM issued_book WHERE issued_book.user_id= :userid)", nativeQuery = true)
    List<Integer> findBooksByUserId(@Param("userid") int userid);

    int countIssuedBooksByBookId(int bookid);
    int countIssuedBooksByUserId(int userid);
    void deleteIssuedBookByBookIdAndUserId(int bookid,int userid);



}
