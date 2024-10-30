package com.sparta.projectblue.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class DistributedLockAspect {

    private final RedissonClient redissonClient;

    @Around("@annotation(com.sparta.projectblue.aop.DistributedLock)")
    public void distributedLock(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);

        String lockKey = distributedLock.value();
        long waitTime = distributedLock.waitTime();
        long leaseTime = distributedLock.leaseTime();

        RLock lock = redissonClient.getLock(lockKey);

        try {
            boolean locked = lock.tryLock(waitTime, leaseTime, TimeUnit.MILLISECONDS);
            if (!locked) {
                log.info("Lock 획득 실패 : {}" , lockKey);
                return; // 락 획득에 실패하면 종료
            }
            joinPoint.proceed(); // 락을 성공적으로 획득했을 경우 메서드 실행
        } finally {
            log.info("락 해제");
            lock.unlock();
        }
    }
}
