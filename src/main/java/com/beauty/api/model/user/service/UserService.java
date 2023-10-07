package com.beauty.api.model.user.service;

import com.beauty.api.model.user.persist.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;

}
