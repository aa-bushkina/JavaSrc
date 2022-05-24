package Main.repository;

import Main.entity.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Integer>
{
  Optional<Client> findByPassportSeriaAndPassportNum(String passportSer, String passportNum);
}
