package billsservice.config.DTO;

import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AllOperationDto {
    private Date date;
    private List<OperationDto> operations;

}
