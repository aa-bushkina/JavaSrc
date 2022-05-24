package Main;

import Main.entity.Client;
import Main.entity.Role;
import Main.entity.User;
import Main.service.ClientService;
import Main.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class Main
{
  public static void main(String[] args)
  {
    SpringApplication.run(Main.class, args);
  }

  @Bean
  PasswordEncoder passwordEncoder()
  {
    return new BCryptPasswordEncoder();
  }

  @Bean
  CommandLineRunner run(UserService userService, ClientService clientService)
  {
    Client client1 = clientService.addClient("Бушкина", "Алёна", "Олеговна",
      "1715", "195350");
    Client client2 = clientService.addClient("Иванова", "Мария", "Вячеславовна",
      "1615", "195350");
    Client client3 = clientService.addClient("Пашнюк", "Анастасия", "Владимировна",
      "18515", "195350");
    Client client4 = clientService.addClient("Тарасов", "Денис", "Олегович",
      "1415", "195350");
    Client client5 = clientService.addClient("Твердохлеб", "Роман", "Игоревич",
      "1315", "195350");
    Client client6 = clientService.addClient("Админ", "Админович", "Адмонов",
      "1215", "195350");
    return args ->
    {
      userService.saveRole(new Role(null, "ROLE_USER"));
      userService.saveRole(new Role(null, "ROLE_ADMIN"));

      userService.saveUser(new User(null, client1, "u", "u", new ArrayList<>()));
      userService.saveUser(new User(null, client2, "user", "12345", new ArrayList<>()));
      userService.saveUser(new User(null, client3, "user_", "1111", new ArrayList<>()));
      userService.saveUser(new User(null, client4, "admin", "22", new ArrayList<>()));
      userService.saveUser(new User(null, client5, "a", "a", new ArrayList<>()));
      userService.saveUser(new User(null, client6, "test_admin", "test_admin", new ArrayList<>()));

      userService.addRoleToUser("u", "ROLE_USER");
      userService.addRoleToUser("user", "ROLE_USER");
      userService.addRoleToUser("user_", "ROLE_USER");
      userService.addRoleToUser("admin", "ROLE_ADMIN");
      userService.addRoleToUser("a", "ROLE_ADMIN");
      userService.addRoleToUser("test_admin", "ROLE_ADMIN");
    };
  }

}

