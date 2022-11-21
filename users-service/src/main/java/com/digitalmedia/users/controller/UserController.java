package com.digitalmedia.users.controller;

import com.digitalmedia.users.model.User;
import com.digitalmedia.users.model.dto.UserBillDTO;
import com.digitalmedia.users.model.dto.UserGroupDTO;
import com.digitalmedia.users.model.dto.UserInfoDTO;
import com.digitalmedia.users.model.dto.UserRequest;
import com.digitalmedia.users.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
  private final IUserService userService;
 //TODO  estos dos endpoints funcionaran cuando este configurada la seguridad en el proyecto

  @GetMapping("/me")
  @PreAuthorize("hasRole('ROLE_USER')")
  public User getUserExtra(Principal principal) {
    return userService.validateAndGetUserExtra(principal.getName());
  }


  @PostMapping("/me")
  @PreAuthorize("hasRole('ROLE_USER')")
  public User saveUserExtra(@Valid @RequestBody UserRequest updateUserRequest, Principal principal) {
    Optional<User> userOptional = userService.getUserExtra(principal.getName());
    User userExtra = userOptional.orElseGet(() -> new User(principal.getName()));
    userExtra.setAvatar(updateUserRequest.getAvatar());
    return userService.saveUserExtra(userExtra);
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public List<UserGroupDTO> getAllNonAdminUsers() {
    return userService.getAllNonAdminUsers();
  }

  @GetMapping("/admin/{userName}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public List<UserInfoDTO> getUserByUsername(@PathVariable String userName) {
    return userService.getByUsername(userName);
  }

  @GetMapping("/bills/{userName}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public List<UserBillDTO> getBillsPerUser(@PathVariable String userName) {
    return userService.getBills(userName);
  }
}
