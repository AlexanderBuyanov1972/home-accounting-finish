package billsservice.config.DTO;

import billsservice.config.enums.CategoryType;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SubCategoryDto {
//    @NotNull(message = "subcategory name is necessary")
    String subCategoryName;
    String subCategoryUuid;
//    @NotNull(message = "user_uuid is necessary")
    String userUuid;
    CategoryDto category;
    CategoryType type;
    String decryption;
}
