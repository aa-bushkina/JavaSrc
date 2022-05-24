package Main.service;

import Main.entity.BookType;

import java.util.List;

public interface BookTypeService
{
  List<BookType> getAllBookTypes();

  BookType getBookTypeById(Integer id);

  BookType addBookType(String name, Double fine, Integer day_count);

  BookType updateBookTypeCnt(Integer id, Integer cnt);

  BookType updateBookTypeFine(Integer id, Double fine);

  BookType updateBookTypeDayCount(Integer id, Integer day_count);

  BookType deleteBookTypeById(Integer id);
}
