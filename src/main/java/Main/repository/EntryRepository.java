package Main.repository;

import Main.entity.Book;
import Main.entity.Client;
import Main.entity.Entry;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EntryRepository extends CrudRepository<Entry, Integer>
{
  Optional<Entry> findByClient(Client client);
  Optional<Entry> findByClientAndBook(Client client, Book book);

  List<Entry> findAllByClient(Client client);

  List<Entry> findAllByBook(Book book);
}
