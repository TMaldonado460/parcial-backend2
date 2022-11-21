package com.digitalmedia.users.service;

import com.digitalmedia.users.model.Bill;
import com.digitalmedia.users.model.User;
import com.digitalmedia.users.model.dto.UserBillDTO;
import com.digitalmedia.users.model.dto.UserGroupDTO;
import com.digitalmedia.users.model.dto.UserInfoDTO;
import com.digitalmedia.users.repository.BillClient;
import com.digitalmedia.users.repository.KeycloakRepository;
import com.digitalmedia.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

  private final UserRepository userRepository;
  private final KeycloakRepository keycloakRepository;
  private final BillClient billClient;


  @Override
  public User validateAndGetUserExtra(String username) {
    return userRepository.validateAndGetUser(username);
  }

  @Override
  public Optional<User> getUserExtra(String username) {
    return userRepository.getUserExtra(username);
  }

  @Override
  public User saveUserExtra(User user) {
    return userRepository.saveUserExtra(user);
  }

  public List<UserGroupDTO> getAllNonAdminUsers(){
    return keycloakRepository.getAllNonAdminUsers();
  }

  @Override
  public List<UserInfoDTO> getByUsername(String userName) {
    return keycloakRepository.findByUsername(userName);
  }

  @Override
  public List<UserBillDTO> getBills(String username)  {
    List<Bill> bills = billClient.getAllBillsPerUsername(username).getBody();
    List<UserInfoDTO> users = this.getByUsername(username);
    assert bills != null;
    return bills.stream().flatMap(bill -> users.stream().map(user -> new UserBillDTO(user, bill))).collect(Collectors.toList());
  }
}
