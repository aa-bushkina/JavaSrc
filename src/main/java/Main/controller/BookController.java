package Main.controller;
// test space to check commit

import Main.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Main.service.BookServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController
{
  @Autowired
  private BookServiceImpl bookService;

  //GET REQUESTS
  @GetMapping("/all")
  public List<Book> getAllBooks()
  {
    return bookService.getAllBooks();
  }

  @GetMapping("/all/by-booktype")
  public List<Book> getAllBooksByBookTypeId(@RequestParam Integer id)
  {
    return bookService.getAllBooksByBookType(id);
  }

  @GetMapping("/book/get")
  public Book getBook(@RequestParam Integer id)
  {
    return bookService.getBookById(id);
  }

  //POST REQUESTS
  @PostMapping("/book/add")
  public Book addNewBook(@RequestParam String name, @RequestParam Integer cnt, @RequestParam Integer type_id)
  {
    return bookService.addNewBook(name, cnt, type_id);
  }

  //PUT REQUESTS
  @PutMapping("/book/update/cnt")
  Book updateBookCnt(@RequestParam Integer id, @RequestParam Integer cnt)
  {
    return bookService.updateBookCntById(id, cnt);
  }

  //DELETE REQUESTS
  @DeleteMapping("/book/delete")
  Book deleteBook(@RequestParam Integer id)
  {
    return bookService.deleteBookById(id);
  }

}
