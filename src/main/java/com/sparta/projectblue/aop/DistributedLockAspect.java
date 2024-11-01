package com.sparta.projectblue.aop;

import com.sparta.projectblue.config.AopForTransaction;
import com.sparta.projectblue.config.CustomSpringELParser;
import com.sparta.projectblue.config.DistributedLock;
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

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class DistributedLockAspect {
    private static final String REDISSON_LOCK_PREFIX = "lock:";

    private final RedissonClient redissonClient;
    private final AopForTransaction aopForTransaction;

    @Around("@annotation(com.sparta.projectblue.config.DistributedLock)")
    public Object distributedLock(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);

        String key =
                REDISSON_LOCK_PREFIX
                        + CustomSpringELParser.getDynamicValue(
                                methodSignature.getParameterNames(),
                                joinPoint.getArgs(),
                                distributedLock.key());
        RLock lock = redissonClient.getLock(key);

        lock.lock();

        try {
            boolean available =
                    lock.tryLock(
                            distributedLock.waitTime(),
                            distributedLock.leaseTime(),
                            distributedLock.timeUnit());
            if (!available) {
                return new IllegalStateException("락을 획득하지 못했습니다."); // 락을 획득 못했을 경우 false 반환
            }else{
                log.info("락을 획득");
            }
            return aopForTransaction.proceed(joinPoint);
        } catch (InterruptedException e) {
            throw new InterruptedException("인터럽트 발생");
        } finally {
            try {
                lock.unlock();
                log.info("lock 해제 완료");
            } catch (IllegalMonitorStateException e) {
                log.info("lock 이 이미 종료 되었습니다.");
            }
        }
    }
}