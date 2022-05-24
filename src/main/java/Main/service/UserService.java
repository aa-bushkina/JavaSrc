package Main.service;

import Main.entity.Role;
import Main.entity.User;

import java.util.List;

public interface UserService
{
  User saveUser(User user);

  Role saveRole(Role role);

  void addRoleToUser(String username, String roleName) throws Exception;

  User getUser(String username) throws Exception;

  String getRole(String username) throws Exception;

  List<User> getUsers();
}
