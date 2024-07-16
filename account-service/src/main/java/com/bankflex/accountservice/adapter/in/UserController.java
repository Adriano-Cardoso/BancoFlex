package com.bankflex.accountservice.adapter.in;

import com.bankflex.accountservice.api.UsersApi;
import com.bankflex.accountservice.model.UserRequest;
import com.bankflex.accountservice.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController implements UsersApi {

    @Override
    public ResponseEntity<UserResponse> createUser(UserRequest userRequest) {
        return UsersApi.super.createUser(userRequest);
    }

}
