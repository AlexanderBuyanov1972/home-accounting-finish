package usersservice.model;



import lombok.*;
import usersservice.enums.Gender;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequestModel {
//    @NotNull(message="User name cannot be null")
//    @Size(min=2, message= "User name must not be less than two characters")
    private String userName;
//    @NotNull(message="First name cannot be null")
//    @Size(min=2, message= "First name must not be less than two characters")
    private String firstName;
//    @NotNull(message="First name cannot be null")
//    @Size(min=2, message= "First name must not be less than two characters")
    private String lastName;
//    @NotNull(message="Last name cannot be null")
//    @Size(min=2, message= "Last name must not be less than two characters")
    private String password;
//    @NotNull(message="Password cannot be null")
//    @Size(min=2, message= "password must not be less than two characters")
    private String email;
//    @NotNull(message="Gender cannot be null")
//    @Size(min=2, message= "Gender should be one of MALE, FEMALE, UNDEFINED")
    private Gender gender;
    private boolean deleted;


}
