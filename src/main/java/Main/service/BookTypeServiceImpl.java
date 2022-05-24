package Main.service;

import Main.entity.BookType;
import Main.exception.*;
import Main.repository.BookTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookTypeServiceImpl implements BookTypeService
{
  @Autowired
  private BookTypeRepository bookTypeRepository;

  @Override
  public List<BookType> getAllBookTypes()
  {
    return (List<BookType>) bookTypeRepository.findAll();
  }

  @Override
  public BookType getBookTypeById(Integer id)
  {
    Optional<BookType> bookType = bookTypeRepository.findById(id);
    if (bookType.isPresent())
    {
      return bookType.get();
    }
    throw new BookTypeNotFoundException("Ошибка! Жанр книги не найден!");
  }

  @Override
  public BookType addBookType(String name, Double fine, Integer day_count)
  {
    Optional<BookType> bookType = bookTypeRepository.findByName(name);
    if (bookType.isPresent())
    {
      log.info("Жанр книги уже существует!");
      return bookType.get();
    }
    BookType newBookType = new BookType(name, 0, fine, day_count);
    return bookTypeRepository.save(newBookType);
  }

  @Override
  public BookType updateBookTypeCnt(Integer id, Integer cnt)
  {
    Optional<BookType> bookType = bookTypeRepository.findById(id);
    if (bookType.isPresent())
    {
      bookType.get().setCnt(cnt);
      return bookTypeRepository.save(bookType.get());
    }
    throw new BookTypeNotFoundException("Ошибка! Жанр книги не найден!");
  }

  @Override
  public BookType updateBookTypeFine(Integer id, Double fine)
  {
    Optional<BookType> bookType = bookTypeRepository.findById(id);
    if (bookType.isPresent())
    {
      bookType.get().setFine(fine);
      return bookTypeRepository.save(bookType.get());
    }
    throw new BookTypeNotFoundException("Ошибка! Жанр книги не найден!");
  }

  @Override
  public BookType updateBookTypeDayCount(Integer id, Integer day_count)
  {
    Optional<BookType> bookType = bookTypeRepository.findById(id);
    if (bookType.isPresent())
    {
      bookType.get().setDayCount(day_count);
      return bookTypeRepository.save(bookType.get());
    }
    throw new BookNotFoundException("Ошибка! Жанр книги не найден!");

  }

  @Override
  public BookType deleteBookTypeById(Integer id)
  {
    Optional<BookType> bookType = bookTypeRepository.findById(id);
    if (bookType.isPresent())
    {
      if (bookType.get().getCnt() != 0)
      {
        throw new BookTypeNotAllowException("Ошибка! Невозможно удалить, так как есть книги этого жанра!");
      }
      bookTypeRepository.deleteById(id);
      return bookType.get();
    }
    throw new BookTypeNotFoundException("Ошибка! Жанр книги не найден!");
  }
}
