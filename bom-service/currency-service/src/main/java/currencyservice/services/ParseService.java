package currencyservice.services;

import currencyservice.model.DefaultCurrency;
import currencyservice.repository.BaseCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParseService {

    //  TODO - write in to log, what date make update defaultCurrency

    @Autowired
    BaseCurrencyRepository baseCurrencyRepository;

    @Value("${is}")
    private String fileName;

    public void parseCurrencyFromJson() throws FileNotFoundException, JSONException {
        baseCurrencyRepository.deleteAll();
        InputStream is = new FileInputStream(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(is))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = new JSONArray(stringBuilder.toString());
        List<DefaultCurrency> defaultCurrencies = new ArrayList<>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            DefaultCurrency c = new DefaultCurrency().builder()
                    .country((String) jsonArray.getJSONObject(i).get("COUNTRY"))
                    .name((String) jsonArray.getJSONObject(i).get("NAME"))
                    .build();
            defaultCurrencies.add(c);

        }
        baseCurrencyRepository.saveAll(defaultCurrencies);
//        TODO realization
//        LOGGER.info("Json with currency parsed {} from file {} ", LocalDate.now(), fileName);
    }

    public List<DefaultCurrency> getAllCurrency() {
        List<DefaultCurrency> currencies = baseCurrencyRepository.findAll();
        if(currencies == null){
            return new ArrayList<>(0);
        }
        //        TODO realization
//        LOGGER.info("List of default currency");
        return currencies;
    }
}


