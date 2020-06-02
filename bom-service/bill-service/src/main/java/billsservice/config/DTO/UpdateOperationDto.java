package billsservice.config.DTO;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateOperationDto {

    private String billUuid;
    private Double sum;
    private String description;
    private LocalDate createDate;
    private String currency;
}
