package billsservice.config.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
class CurrencyFallbackFactory implements FallbackFactory<CurrencyServiceClient> {

	@Override
	public CurrencyServiceClient create(Throwable cause) {
		// TODO Auto-generated method stub
		return new CurrencyServiceClientFallback(cause);
	}

}

