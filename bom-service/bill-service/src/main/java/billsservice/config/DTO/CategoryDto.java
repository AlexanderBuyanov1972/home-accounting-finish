package billsservice.config.DTO;

import billsservice.config.enums.CategoryType;
import lombok.*;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDto {
//    @NotNull(message = "name of category may not be null")
    String categoryName;
    String categoryUuid;
//    @NotNull(message = "user_uuid is necessary")
    String userUuid;
//    @NotNull(message = "type may not be null")
    CategoryType type;
    String decryption;
}
