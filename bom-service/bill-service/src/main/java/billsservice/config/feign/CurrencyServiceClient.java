package billsservice.config.feign;


import feign.FeignException;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "currency-service", fallbackFactory = CurrencyFallbackFactory.class)

public interface CurrencyServiceClient {
    @GetMapping("/currency/{userId}")
    public List<CurrencyFromFeign> getAllCurrencyByUser(@PathVariable String userId);

    @Component
    class CurrencyFallbackFactory implements FallbackFactory<CurrencyServiceClient> {

        @Override
        public CurrencyServiceClient create(Throwable cause) {
            // TODO Auto-generated method stub
            return new CurrencyServiceClientFallback(cause);
        }

    }

    class AlbumsServiceClientFallback implements CurrencyServiceClient {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        private final Throwable cause;

        public AlbumsServiceClientFallback(Throwable cause) {
            this.cause = cause;
        }

        @Override
        public List<CurrencyFromFeign> getAllCurrencyByUser(String id) {
            // TODO Auto-generated method stub

            if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
                logger.error("404 error took place when getAllCurrencyByUser was called with userId: " + id + ". Error message: "
                        + cause.getLocalizedMessage());
            } else {
                logger.error("Other error took place: " + cause.getLocalizedMessage());
            }

            return new ArrayList<>();
        }

    }

}
