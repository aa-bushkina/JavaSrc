package Main.controller;

import Main.entity.BookType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Main.service.BookTypeServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/booktypes")
public class BookTypeController
{
  @Autowired
  private BookTypeServiceImpl bookTypeService;

  //GET REQUESTS
  @GetMapping("/all")
  public List<BookType> getAllBookTypes()
  {
    return bookTypeService.getAllBookTypes();
  }

  @GetMapping("/booktype/get")
  public BookType getBookType(@RequestParam Integer id)
  {
    return bookTypeService.getBookTypeById(id);
  }

  //POST REQUESTS
  @PostMapping("/booktype/add")
  public BookType addBookType(@RequestParam String name, @RequestParam Double fine, @RequestParam Integer day_count)
  {
    return bookTypeService.addBookType(name, fine, day_count);
  }

  //UPDATE REQUESTS
  @PutMapping("/booktype/update/cnt")
  BookType updateBookTypeCnt(@RequestParam Integer id, @RequestParam Integer cnt)
  {
    return bookTypeService.updateBookTypeCnt(id, cnt);
  }

  @PutMapping("/booktype/update/fine")
  BookType updateBookTypeFine(@RequestParam Integer id, @RequestParam Double fine)
  {
    return bookTypeService.updateBookTypeFine(id, fine);
  }

  @PutMapping("/booktype/update/daycount")
  BookType updateBookTypeDayCount(@RequestParam Integer id, @RequestParam Integer day_count)
  {
    return bookTypeService.updateBookTypeDayCount(id, day_count);
  }

  //DELETE REQUESTS
  @DeleteMapping("/booktype/delete")
  BookType deleteBookType(@RequestParam Integer id)
  {
    return bookTypeService.deleteBookTypeById(id);
  }
}
