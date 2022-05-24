package Main.service;

import Main.entity.Role;
import Main.entity.User;
import Main.exception.UserNotFoundException;
import Main.repository.RoleRepository;
import Main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService
{
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public User saveUser(User user)
  {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  @Override
  public Role saveRole(Role role)
  {
    return roleRepository.save(role);
  }

  @Override
  public void addRoleToUser(String username, String roleName)
  {
    Optional<User> user = userRepository.findUserByUsername(username);
    if (user.isEmpty())
    {
      throw new UsernameNotFoundException("Ошибка! Пользователь не найден!");
    }
    Role role = roleRepository.findByName(roleName);
    user.get().getRoles().add(role);
  }

  @Override
  public User getUser(String username)
  {
    Optional<User> user = userRepository.findUserByUsername(username);
    if (user.isEmpty())
    {
      throw new UsernameNotFoundException("Ошибка! Пользователь не найден!");
    }
    return user.get();
  }

  @Override
  public String getRole(String username)
  {
    log.info("Fetching role");
    Optional<User> user = userRepository.findUserByUsername(username);
    if (user.isEmpty())
    {
      throw new UsernameNotFoundException("Ошибка! Пользователь не найден!");
    }
    String role = "ROLE_USER";
    for (Role tmp_role :
      user.get().getRoles())
    {
      if (tmp_role.getName().equals("ROLE_ADMIN"))
      {
        role = "ROLE_ADMIN";
        break;
      }
    }
    return role;
  }

  @Override
  public List<User> getUsers()
  {
    return userRepository.findAll();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UserNotFoundException
  {
    Optional<User> user = userRepository.findUserByUsername(username);
    if (user.isEmpty())
    {
      throw new UserNotFoundException("Ошибка! Пользователь не найден!");
    } else
    {
      log.info("Пользователь загружен!");
    }
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.get().getRoles().forEach(role ->
    {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    });
    return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), authorities);
  }
}
