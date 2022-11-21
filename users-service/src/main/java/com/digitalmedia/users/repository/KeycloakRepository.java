package com.digitalmedia.users.repository;

import com.digitalmedia.users.model.User;
import com.digitalmedia.users.model.dto.UserGroupDTO;
import com.digitalmedia.users.model.dto.UserInfoDTO;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class KeycloakRepository {
    @Autowired
    private Keycloak keycloak;
    @Value("${keycloak.realm}")
    private String realm;

    public List<UserInfoDTO> findByUsername(String username) {
        List<UserRepresentation> userRepresentationList = keycloak
                .realm(realm)
                .users()
                .search(username);
        return userRepresentationList.stream().map(user -> {
            return new UserInfoDTO(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail());
        }).collect(Collectors.toList());
    }
    public Optional<User> findById(String id) {
        UserRepresentation userRepresentation = keycloak
                .realm(realm)
                .users()
                .get(id)
                .toRepresentation();
        return Optional.of(fromRepresentation(userRepresentation));
    }

    public List<UserGroupDTO> getAllNonAdminUsers() {
        List<GroupRepresentation> groupRepresentationsList = keycloak
                .realm(realm)
                .groups()
                .groups();
        List<UserRepresentation> userRepresentationList = new ArrayList<>();
        for (GroupRepresentation gr : groupRepresentationsList) {
            if(!Objects.equals(gr.getName(), "admin")) {
                userRepresentationList.addAll(
                        keycloak.realm(realm).groups().group(gr.getId()).members()
                );
            }
        }

        return userRepresentationList.stream().map(user -> {
            return new UserGroupDTO(user.getUsername(), user.getEmail());
        }).collect(Collectors.toList());
    }
    private User fromRepresentation(UserRepresentation user) {
        return new User(user.getUsername());
    }
}
