package com.example.jini_umc10th.domain.mission.repository;

import com.example.jini_umc10th.domain.mission.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
