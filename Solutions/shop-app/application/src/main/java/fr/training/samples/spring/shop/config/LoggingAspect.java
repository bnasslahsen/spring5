package fr.training.samples.spring.shop.config;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);

	@Pointcut("execution( * fr.training.samples.spring.shop.application..*(..))")
	private void profilingApplicationLayer() { }

	@Around("profilingApplicationLayer()")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		Object output = pjp.proceed();
		long elapsedTime = System.currentTimeMillis() - start;
		LOGGER.info("Execution time for method " + pjp.getSignature().getName() + " is " + elapsedTime + " milliseconds.");
		return output;
	}
}