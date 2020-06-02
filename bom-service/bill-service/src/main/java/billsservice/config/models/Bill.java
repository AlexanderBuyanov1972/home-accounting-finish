package billsservice.config.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Entity
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Bill extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userUuid;
    private Date createDate;
    private Boolean deleted;
    private String billUuid;
    private String billName;
    private Integer billNumber;
    private Double startSum;
    private String description;


}
