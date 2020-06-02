package billsservice.config.DTO;

import billsservice.config.enums.CategoryType;
import lombok.*;


import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OperationsByDateDto {
    Double Sum;
    CategoryType type;
    String categoryName;
    LocalDate createDate;
}
