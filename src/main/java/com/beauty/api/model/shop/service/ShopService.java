package com.beauty.api.model.shop.service;

import com.beauty.api.model.shop.persist.repository.ShopRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShopService {

  private final ShopRepository shopRepository;

}
