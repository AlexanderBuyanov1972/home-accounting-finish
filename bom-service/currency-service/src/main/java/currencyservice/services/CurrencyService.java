package currencyservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import currencyservice.model.Currency;
import currencyservice.repository.CurrencyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

import java.util.List;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(currencyservice.services.CurrencyService.class);
    private ObjectMapper mapper = new ObjectMapper();

    public Currency addCurrency(Currency currency) throws JsonProcessingException {
        Currency currencyCheck = currencyRepository.findByCurrencyNameAndUserUuidAndDeleted(
                currency.getCurrencyName(), currency.getUserUuid(), false);
        if(currencyCheck != null){
//            TODO implementation logger and exceptions
            LOGGER.info("Currency exists {}", mapper.writeValueAsString(currency));
//            throw new HomeBuhNotUniqueException("currency", "name "+currency.getCurrencyName());
        }
        currency.setDeleted(false);
        currency = currencyRepository.save(currency);
        LOGGER.info("Currency created {}", mapper.writeValueAsString(currency));
        return currency;
    }

    public List<Currency> getAllCurrency(String userUuid) {
        List<Currency> currencyList = currencyRepository.findAllByUserUuidAndDeleted(userUuid, false);
        if(currencyList.isEmpty()){
            return new ArrayList<>(0);
        }
        //            TODO implementation logger and exceptions
        LOGGER.info("List of currency for user with uuid {} ", userUuid);
        return currencyList;
    }

    public Currency deleteCurrency(String userUuid, String abbr) throws JsonProcessingException {
        Currency currency = currencyRepository.findByAbbrAndUserUuidAndDeleted(abbr, userUuid,
                false);
        if(currency == null){
            LOGGER.info("Abbr {} not exists ", abbr);
//            throw new HomeBuhNotFoundException("currency", abbr);
        }
        //            TODO implementation logger and exceptions
        currency.setDeleted(true);
        currency = currencyRepository.save(currency);
        LOGGER.info("Currency deleted {} ", mapper.writeValueAsString(currency));
        return currency;
    }
}
