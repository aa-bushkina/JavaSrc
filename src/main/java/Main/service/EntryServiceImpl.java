package Main.service;

import Main.entity.Book;
import Main.entity.Client;
import Main.entity.Entry;
import Main.exception.BookNotFoundException;
import Main.exception.ClientNotFoundException;
import Main.exception.EntryNotFoundException;
import Main.repository.BookRepository;
import Main.repository.ClientRepository;
import Main.repository.EntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EntryServiceImpl implements EntryService
{
  @Autowired
  private EntryRepository entryRepository;
  @Autowired
  private ClientRepository clientRepository;
  @Autowired
  private BookRepository bookRepository;

  @Override
  public List<Entry> getAllEntries()
  {
    return (List<Entry>) entryRepository.findAll();
  }

  @Override
  public List<Entry> getAllEntriesByClient(Integer clientId)
  {
    Optional<Client> client = clientRepository.findById(clientId);
    if (client.isPresent())
    {
      return entryRepository.findAllByClient(client.get());
    }
    throw new ClientNotFoundException("Ошибка! Клиент не найден!");
  }

  @Override
  public List<Entry> getAllEntriesByBook(Integer bookId)
  {
    Optional<Book> book = bookRepository.findById(bookId);
    if (book.isPresent())
    {
      return entryRepository.findAllByBook(book.get());
    }
    throw new BookNotFoundException("Ошибка! Книга не найдена!");
  }

  @Override
  public Entry addEntry(Integer clientId, Integer bookId)
  {
    Optional<Client> client = clientRepository.findById(clientId);
    if (client.isEmpty())
    {
      throw new ClientNotFoundException("Ошибка! Клиент не найден!");
    }
    Optional<Book> book = bookRepository.findById(bookId);
    if (book.isEmpty())
    {
      throw new BookNotFoundException("Ошибка! Книга не найдена!");
    }
    if ( book.get().getCnt() == 0)
    {
      throw new BookNotFoundException("Ошибка! Свободных книг нет!");
    }

    Optional<Entry> entry = entryRepository.findByClientAndBook(client.get(), book.get());
    if (entry.isPresent())
    {
      log.info("Жанр книги уже существует!");
      return entry.get();
    }
    Integer dayCount = book.get().getBookType().getDayCount();
    Timestamp dataBeg = Timestamp.valueOf(LocalDateTime.now());
    Timestamp dataEnd = Timestamp.valueOf(LocalDateTime.now().plusDays(dayCount));
    Entry newEntry = new Entry(book.get(), client.get(), dataBeg, dataEnd, null);
    book.get().setCnt(book.get().getCnt() - 1);
    bookRepository.save(book.get());

    return entryRepository.save(newEntry);
  }

  @Override
  public Entry updateDataRet(Integer id)
  {
    Optional<Entry> entry = entryRepository.findById(id);
    if (entry.isPresent())
    {
      Timestamp dataRet = Timestamp.valueOf(LocalDateTime.now());
      entry.get().setDataRet(dataRet);
      Book book = bookRepository.findById(entry.get().getBook().getId()).get();
      book.setCnt(book.getCnt() + 1);
      bookRepository.save(book);
      return entryRepository.save(entry.get());
    }
    throw new EntryNotFoundException("Ошибка! Запись не найдена!");
  }

  @Override
  public Entry deleteEntry(Integer id)
  {
    Optional<Entry> entry = entryRepository.findById(id);
    if (entry.isPresent())
    {
      if (entry.get().getDataRet() == null)
      {
        throw new EntryNotFoundException("Ошибка! Невозможно удалить запись, клиент не вернул книгу!");
      }
      entryRepository.deleteById(entry.get().getId());
      return entry.get();
    }
    throw new EntryNotFoundException("Ошибка! Запись не найдена!");
  }
}
