package edu.idat.pe.goldenBlog.repository;

import edu.idat.pe.goldenBlog.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByCategory_IdCategory(Long idCategory);
    Book findByNameBook(String nameBook);
    Book findByNameBookAndIdBookNot(String nameBook, Long idBook);
}


