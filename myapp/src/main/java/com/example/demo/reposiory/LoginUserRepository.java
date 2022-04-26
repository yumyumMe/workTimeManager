package com.example.demo.reposiory;

import java.util.Optional;

import com.example.demo.entity.User;

public interface LoginUserRepository {

	Optional<User> findUser(String userName);

}