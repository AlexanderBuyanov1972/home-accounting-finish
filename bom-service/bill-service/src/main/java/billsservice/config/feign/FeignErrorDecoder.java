package billsservice.config.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignErrorDecoder implements ErrorDecoder {
	
	Environment environment;
	
	@Autowired
	public FeignErrorDecoder(Environment environment)
	{
		this.environment = environment;
	}

	public FeignErrorDecoder() {

	}

	@Override
	public Exception decode(String methodKey, Response response) {
		switch (response.status()) {
		case 500:

			// Do something
//			 return new Exception(response.reason());
			break;
		case 404: {
			if (methodKey.contains("currency")) {
				return new ResponseStatusException(HttpStatus.valueOf(response.status()),
						environment.getProperty("currency.exceptions.currency-not-found"));
			}
			break;
		}
		default:
			return new Exception(response.reason());
		}
		return null;
	}

}
