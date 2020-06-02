package usersservice.model;

import lombok.*;
import usersservice.enums.Gender;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserResponseModel {
    private String firstName;
    private String lastName;
    private String email;
    private String userId;
    private Gender gender;
    private String userName;

}
