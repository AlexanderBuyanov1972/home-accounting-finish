package usersservice.dto;

import lombok.*;
import usersservice.enums.Gender;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements Serializable {

    private static final long serialVersionUID = 9207198770014069837L;

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String userId;
    private String password;
    private Gender gender;
    private boolean deleted;








}
