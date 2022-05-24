package Main.controller;

import Main.entity.User;
import Main.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class UserController
{
  private final UserService userService;

  @GetMapping("/users")
  public ResponseEntity<List<User>> getUsers()
  {
    return ResponseEntity.ok().body(userService.getUsers());
  }

  @GetMapping("/user/get")
  public ResponseEntity<User> getUser(@RequestParam String username) throws Exception
  {
    return ResponseEntity.ok().body(userService.getUser(username));
  }

  @PostMapping("/user/test")
  public ResponseEntity test()
  {
    return ResponseEntity.ok().body("");
  }
}
