package com.digitalmedia.users.repository;

import com.digitalmedia.users.config.feign.OAuthFeignConfig;
import com.digitalmedia.users.model.Bill;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "ms-bill", configuration = OAuthFeignConfig.class)
public interface BillClient {

    @RequestMapping(value = "/bills/details/{username}", method = RequestMethod.GET)
    public ResponseEntity<List<Bill>> getAllBillsPerUsername(@PathVariable String username);

}
