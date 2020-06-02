package billsservice.config.feign;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CurrencyFromFeign {
    private String currencyName;
    private String abbr;
}
