package Main.controller;

import Main.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Main.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController
{
  @Autowired
  private ClientService clientService;

  @GetMapping("/all")
  public List<Client> getAllClients()
  {
    return clientService.getAllClients();
  }

  @GetMapping("/client/get")
  public Client getClient(@RequestParam Integer id)
  {
    return clientService.getClientById(id);
  }

  @PostMapping("/client/add")
  public Client addClient(@RequestParam String lastName, @RequestParam String firstName,
                          @RequestParam String patherName, @RequestParam String passportSer,
                          @RequestParam String passportNum)
  {
    return clientService.addClient(lastName, firstName, patherName, passportSer, passportNum);
  }

  @PutMapping("/client/update/passport")
  Client updateClientPassport(@RequestParam Integer id, @RequestParam String passportSer, @RequestParam String passportNum)
  {
    return clientService.updateClientPassport(id, passportSer, passportNum);
  }

  @PutMapping("/client/update/firstname")
  Client updateClientFirstName(@RequestParam Integer id, @RequestParam String firstName)
  {
    return clientService.updateClientFirstName(id, firstName);
  }

  @PutMapping("/client/update/lastname")
  Client updateClientLastName(@RequestParam Integer id, @RequestParam String lastName)
  {
    return clientService.updateClientLastName(id, lastName);
  }

  @DeleteMapping("/client/delete")
  Client deleteClient(@RequestParam Integer id)
  {
    return clientService.deleteClientById(id);
  }
}
