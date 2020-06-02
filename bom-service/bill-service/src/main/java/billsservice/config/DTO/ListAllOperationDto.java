package billsservice.config.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
/**
 * @deprecated
 * using BillAllDetailsDto
 */
@Deprecated()
public class ListAllOperationDto {
    String billUuid;
    String billName;
    String userUuid;
    String operationName;
    String operationSum;
    String operationType;
    Double sumByBill;

}
