package usersservice.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;
import usersservice.dto.UserDto;

import java.util.List;

public interface UsersService extends UserDetailsService {
    UserDto createUser(UserDto userDetails);

    UserDto getUserDetailsByEmail(String userName);

    UserDto deleteUser(String id);

    List<UserDto> getAllUsers();

    UserDto getUser(String id);
}
