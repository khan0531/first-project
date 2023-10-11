package com.beauty.api.model.reservation.service;

import com.beauty.api.model.reservation.persist.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationService {

  private final ReservationRepository reservationRepository;
}
