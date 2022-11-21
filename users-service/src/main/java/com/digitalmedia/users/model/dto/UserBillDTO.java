package com.digitalmedia.users.model.dto;

import com.digitalmedia.users.model.Bill;
import com.digitalmedia.users.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserBillDTO extends UserInfoDTO {
    private Bill bill;

    public UserBillDTO(UserInfoDTO user, Bill bill) {
        super(user.getUsername(), user.getFirstname(), user.getLastname(), user.getEmail());
        this.bill = bill;
    }
}
