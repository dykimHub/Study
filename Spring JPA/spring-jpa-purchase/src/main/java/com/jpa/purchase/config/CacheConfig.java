package com.jpa.purchase.config;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CacheConfig {
	// 에케시도 매니저를 만들어봤는데 카페인캐시가 편하고 동시에 사용하면 충돌나서 그냥 하나만 남김
	// 얘는 1차 캐시 매니저 2차 캐시는 application.properties에서 에케시매니저가 만듦
	// 메모리 안에서 각 매니저 하에 관리됨

	@Bean
	public CacheManager caffeinCacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();

		cacheManager.setCaches(Arrays.asList(
				new CaffeineCache("user",
						Caffeine.newBuilder()
						.expireAfterWrite(600, TimeUnit.SECONDS) // 1,2차 캐시 전부 삭제
						.maximumSize(10000) // 객체 수
						.build()),
				new CaffeineCache("product",
						Caffeine.newBuilder()
						.expireAfterWrite(600, TimeUnit.SECONDS)
						.maximumSize(10000)
						.build())));

		return cacheManager;
	}

}
