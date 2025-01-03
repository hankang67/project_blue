package com.sparta.projectblue.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {

    // 락의 이름 설정
    String key();

    // 락의 시간 단위
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    // 락 획득을 위해 waitTime 만큼 대기
    long waitTime() default 5L;

    // 락을 획득한 이후 leaseTime 이 지나면 락을 해제
    long leaseTime() default 10L;
}
