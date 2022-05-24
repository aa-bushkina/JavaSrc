package Main.service;

import Main.entity.Book;
import Main.entity.BookType;
import Main.exception.BookTypeNotFoundException;
import Main.repository.BookRepository;
import Main.exception.BookNotFoundException;
import Main.repository.BookTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookServiceImpl implements BookService
{
  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private BookTypeRepository bookTypeRepository;

  @Override
  public List<Book> getAllBooks()
  {
    return (List<Book>) bookRepository.findAll();
  }

  @Override
  public List<Book> getAllBooksByBookType(Integer id)
  {
    Optional<BookType> bookType = bookTypeRepository.findById(id);
    if (bookType.isPresent())
    {
      return bookRepository.findAllByBookType(bookType.get());
    }
    throw new BookTypeNotFoundException("Ошибка! Жанр книги не найден!");
  }

  @Override
  public Book getBookById(Integer id)
  {
    Optional<Book> book = bookRepository.findById(id);
    if (book.isPresent())
    {
      return book.get();
    }
    throw new BookNotFoundException("Ошибка! Книга не найдена!");
  }

  @Override
  public Book addNewBook(String name, Integer cnt, Integer type_id)
  {
    Optional<Book> book = bookRepository.findByName(name);
    if (book.isPresent())
    {
      log.info("Книга уже существует!");
      return book.get();
    }
    Optional<BookType> bookType = bookTypeRepository.findById(type_id);
    if (bookType.isPresent())
    {
      Book newBook = new Book(name, cnt, bookType.get());
      bookType.get().setCnt(bookType.get().getCnt() + 1);
      return bookRepository.save(newBook);
    }
    throw new BookTypeNotFoundException("Ошибка! Жанр книги не найден!");
  }

  @Override
  public Book updateBookCntById(Integer id, Integer cnt)
  {
    Optional<Book> book = bookRepository.findById(id);
    if (book.isPresent())
    {
      book.get().setCnt(cnt);
      return bookRepository.save(book.get());
    }
    throw new BookNotFoundException("Ошибка! Книга не найдена!");
  }

  @Override
  public Book deleteBookById(Integer id)
  {
    Optional<Book> book = bookRepository.findById(id);
    if (book.isPresent())
    {
      BookType bookType = book.get().getBookType();
      if (book.get().getCnt() == 1)
      {
        bookRepository.deleteById(id);
        bookType.setCnt(bookType.getCnt() - 1);
        return book.get();
      }
      book.get().setCnt(book.get().getCnt() - 1);
      bookRepository.save(book.get());
      return book.get();
    }
    throw new BookNotFoundException("Ошибка! Книга не найдена!");
  }

}
