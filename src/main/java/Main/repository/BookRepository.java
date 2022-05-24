package Main.repository;

import Main.entity.Book;
import Main.entity.BookType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Integer>
{
  List<Book> findAllByBookType(BookType bookType);

  Optional<Book> findByName(String name);
}
