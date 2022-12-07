package com.example.lock.coupon;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CouponServiceTest {
	@Autowired
	CouponRepository couponRepository;

	@Autowired
	CouponService couponService;

	@Test
	@DisplayName("쿠폰 발급 테스트")
	void issueCouponTest() throws InterruptedException {
		// given
		String couponId = UUID.randomUUID().toString();
		Coupon coupon = new Coupon(
			couponId,
			"테스트 쿠폰",
			100,
			1L
		);
		couponRepository.save(coupon);

		// when
		int count = 100;
		CountDownLatch latch = new CountDownLatch(count);

		ExecutorService executorService = Executors.newFixedThreadPool(10);
		while (count-- > 0) {
			executorService.submit(() -> {
				try {
					couponService.issue(couponId);
				} finally {
					latch.countDown();
				}
			});
		}

		latch.await();

		Optional<Coupon> result = couponRepository.findById(couponId);

		// then
		assertThat(result).isNotEmpty();
		assertThat(result.get().getAmount()).isZero();
	}
}