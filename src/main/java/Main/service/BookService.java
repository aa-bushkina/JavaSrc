package Main.service;

import Main.entity.Book;

import java.util.List;

public interface BookService
{
  List<Book> getAllBooks();

  List<Book> getAllBooksByBookType(Integer id);

  Book getBookById(Integer id);

  Book addNewBook(String name, Integer cnt, Integer type_id);

  Book updateBookCntById(Integer id, Integer cnt);

  Book deleteBookById(Integer id);
}
