package com.example.lock.coupon;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;

public interface CouponRepository extends JpaRepository<Coupon, String> {
	@Lock(LockModeType.OPTIMISTIC)
	Optional<Coupon> findById(String id);
}
