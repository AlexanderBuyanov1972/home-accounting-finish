package billsservice.config.models;

import billsservice.config.enums.CategoryType;
import lombok.*;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Category extends BaseEntity {
    private String categoryUuid;
    private String categoryName;
    private String userUuid;
    @Enumerated(EnumType.STRING)
    private CategoryType type;
    private Boolean deleted;
    private String description;

}
