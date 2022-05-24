package Main.service;

import Main.entity.Client;
import Main.entity.Entry;
import Main.exception.ClientNotFoundException;
import Main.repository.ClientRepository;
import Main.repository.EntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService
{
  @Autowired
  private ClientRepository clientRepository;
  private EntryRepository entryRepository;

  @Override
  public List<Client> getAllClients()
  {
    return (List<Client>) clientRepository.findAll();
  }

  @Override
  public Client getClientById(Integer id)
  {
    Optional<Client> client = clientRepository.findById(id);
    if (client.isPresent())
    {
      return client.get();
    }
    throw new ClientNotFoundException("Ошибка! Клиент не найден!");
  }

  @Override
  public Client addClient(String lastName, String firstName, String patherName, String passportSer, String passportNum)
  {
    Optional<Client> tempClient = clientRepository.findByPassportSeriaAndPassportNum(passportSer, passportNum);
    if (tempClient.isPresent())
    {
      log.info("Клиент уже существует!");
      return tempClient.get();
    }
    Client client = new Client(lastName, firstName, patherName, passportSer, passportNum);
    return clientRepository.save(client);
  }

  @Override
  public Client updateClientPassport(Integer id, String passportSer, String passportNum)
  {
    Optional<Client> client = clientRepository.findById(id);
    if (client.isPresent())
    {
      client.get().setPassportSeria(passportSer);
      client.get().setPassportNum(passportNum);
      return clientRepository.save(client.get());
    }
    throw new ClientNotFoundException("Ошибка! Клиент не найден!");

  }

  @Override
  public Client updateClientFirstName(Integer id, String firstName)
  {
    Optional<Client> client = clientRepository.findById(id);
    if (client.isPresent())
    {
      client.get().setFirstName(firstName);
      return clientRepository.save(client.get());
    }
    throw new ClientNotFoundException("Ошибка! Клиент не найден!");
  }

  @Override
  public Client updateClientLastName(Integer id, String lastName)
  {
    Optional<Client> client = clientRepository.findById(id);
    if (client.isPresent())
    {
      client.get().setLastName(lastName);
      return clientRepository.save(client.get());
    }
    throw new ClientNotFoundException("Ошибка! Клиент не найден!");
  }

  @Override
  public Client deleteClientById(Integer id)
  {
    Optional<Client> client = clientRepository.findById(id);
    if (client.isPresent())
    {
      if (entryRepository != null)
      {
        Optional<Entry> entry = entryRepository.findByClient(client.get());
        if (entry.isPresent())
        {
          throw new ClientNotFoundException("Ошибка! Клиент имеет книги!");
        }
      }
      clientRepository.deleteById(id);
      return client.get();
    }
    throw new ClientNotFoundException("Ошибка! Клиент не найден!");
  }
}
