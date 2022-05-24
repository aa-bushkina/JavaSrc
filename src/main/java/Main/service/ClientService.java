package Main.service;

import Main.entity.Client;

import java.util.List;

public interface ClientService
{
  List<Client> getAllClients();

  Client getClientById(Integer id);

  Client addClient(String lastName, String firstName, String patherName, String passportSer, String passportNum);

  Client updateClientPassport(Integer id, String passportSer, String passportNum);

  Client updateClientFirstName(Integer id, String firstName);

  Client updateClientLastName(Integer id, String lastName);

  Client deleteClientById(Integer id);
}
