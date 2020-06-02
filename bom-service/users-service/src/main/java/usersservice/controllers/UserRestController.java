package usersservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usersservice.dto.UserDto;
import usersservice.model.CreateUserRequestModel;
import usersservice.model.CreateUserResponseModel;
import usersservice.services.UsersService;

import java.util.List;



@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    Environment env;

    @Autowired
    UsersService usersService;

    @GetMapping("/status/check")
    public  String checkStatus(){
        return "Users-service working on port: " + env.getProperty("local.server.port") + ", " +
                "with token = " + env.getProperty("token.secret");
    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity createUser(@RequestBody CreateUserRequestModel userDetails)
    {
        userDetails.setDeleted(false);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
//
        UserDto createdUser = usersService.createUser(userDto);
//
        CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(returnValue);
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable String id) throws JsonProcessingException {
        return usersService.getUser(id);
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers(){
        return usersService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public UserDto deleteUser(@PathVariable String id) throws JsonProcessingException {
        return usersService.deleteUser(id);
    }

}
