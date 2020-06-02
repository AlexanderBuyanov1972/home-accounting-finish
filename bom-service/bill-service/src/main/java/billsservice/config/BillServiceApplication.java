package billsservice.config;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
public class BillServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillServiceApplication.class, args);
    }
        @Bean
        public CommonsRequestLoggingFilter requestLoggingFilter() {
            CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
            loggingFilter.setIncludePayload(true);
            loggingFilter.setIncludeHeaders(true);
            loggingFilter.setMaxPayloadLength(1000);
            loggingFilter.setAfterMessagePrefix("REQ:");
            return loggingFilter;
        }

    @Bean
    Logger.Level feignDefaultLoggerLevel()
    {
        return Logger.Level.FULL;
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    }


