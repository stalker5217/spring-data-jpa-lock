package com.example.lock.coupon;

import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponService {
	private final CouponRepository couponRepository;

	@Transactional
	@Retryable(maxAttempts = 100)
	public void issue(String id) {
		Coupon coupon = couponRepository
			.findById(id)
			.orElseThrow(IllegalArgumentException::new);

		coupon.setAmount(coupon.getAmount() - 1);

		couponRepository.save(coupon);
	}
}
