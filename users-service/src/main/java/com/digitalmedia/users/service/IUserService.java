package com.digitalmedia.users.service;

import com.digitalmedia.users.model.User;
import com.digitalmedia.users.model.dto.UserBillDTO;
import com.digitalmedia.users.model.dto.UserGroupDTO;
import com.digitalmedia.users.model.dto.UserInfoDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {
  User validateAndGetUserExtra(String username);

  Optional<User> getUserExtra(String username);

  User saveUserExtra(User userExtra);

  List<UserGroupDTO> getAllNonAdminUsers();

  List<UserInfoDTO> getByUsername(String userName);

  List<UserBillDTO> getBills(String username);
}
