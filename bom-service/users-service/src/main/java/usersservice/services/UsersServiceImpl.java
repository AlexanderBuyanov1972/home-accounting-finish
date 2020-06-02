package usersservice.services;

import com.sun.xml.bind.v2.TODO;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import usersservice.dto.UserDto;
import org.modelmapper.ModelMapper;
import usersservice.model.UserEntity;
import usersservice.repo.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDto createUser(UserDto userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        userDetails.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);

        usersRepository.save(userEntity);

        UserDto returnValue = modelMapper.map(userEntity, UserDto.class);

        return returnValue;
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserEntity userEntity = usersRepository.findByEmail(email);
        if(userEntity == null) throw new UsernameNotFoundException(email);
        return new ModelMapper().map(userEntity, UserDto.class);
    }

    @Override
    public UserDto deleteUser(String id) {
        UserEntity user = usersRepository.findByUserId(id);
        if (user == null) {
//            TODO implementation
//            throw new HomeBuhNotFoundException("User", id);
        }
        user.setDeleted(true);
        user = usersRepository.save(user);
//        TODO implementation
//        LOGGER.info("User deleted {}", mapper.writeValueAsString(user));
       return modelMapper.map(user, UserDto.class);
//        UserDto userDto = userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity>userList = usersRepository.findAllByDeleted(false);
        if(userList.size() == 0){
            return null;
        }
        List<UserDto> userDtoList = userList.stream().map(this::userToDto).collect(Collectors.toList());
        //        TODO implementation
//        LOGGER.info("List of users");
        return userDtoList;

    }
    private UserDto userToDto(UserEntity user) {
       UserDto userDto = modelMapper.map(user, UserDto.class);
        return  userDto;
    }

    @Override
    public UserDto getUser(String id) {
        UserEntity user = usersRepository.findByUserId(id);
        if (user == null) {
            //        TODO implementation
//            throw new HomeBuhNotFoundException("User", id);
        }
        user.setDeleted(true);
        usersRepository.save(user);
        //        TODO implementation
//        LOGGER.info("User deleted {}", mapper.writeValueAsString(user));
        UserDto userDto = userToDto(user);
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = usersRepository.findByEmail(username);
        if(userEntity == null) throw new UsernameNotFoundException(username);
        return new User(userEntity.getEmail(), userEntity.getPassword(), true, true, true, true, new ArrayList<>());
    }
}
