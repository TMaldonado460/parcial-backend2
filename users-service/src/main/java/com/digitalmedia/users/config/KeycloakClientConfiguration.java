package com.digitalmedia.users.config;

import lombok.Setter;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "keycloak")
@Setter
public class KeycloakClientConfiguration {
    private String serverUrl;
    private String realm;
    private String clientId;
    private String clientSecret;

    @Bean
    public Keycloak getInstance() {
        System.out.println(serverUrl);
        System.out.println(realm);
        System.out.println(clientId);
        System.out.println(clientSecret);
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();
    }
}