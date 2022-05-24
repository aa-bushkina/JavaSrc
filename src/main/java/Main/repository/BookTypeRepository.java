package Main.repository;

import Main.entity.BookType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookTypeRepository extends CrudRepository<BookType, Integer>
{
  Optional<BookType> findByName(String name);
}
