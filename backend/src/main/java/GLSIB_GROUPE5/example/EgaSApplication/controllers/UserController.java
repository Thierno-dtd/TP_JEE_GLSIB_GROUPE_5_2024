package GLSIB_GROUPE5.example.EgaSApplication.controllers;

import GLSIB_GROUPE5.example.EgaSApplication.dto.UserDto;
import GLSIB_GROUPE5.example.EgaSApplication.services.serviceImpl.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    /*@PostMapping("/")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.register(userDto));
    }*/
    @GetMapping("/")
    @ApiOperation("Liste des users")
    public List<UserDto> getAllUser(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ApiOperation("get one user")
    public UserDto getOneUsers(
            @PathVariable
            @ApiParam(name = "id", required = true)
            int id){
        return userService.getOneUser(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete user by id")
    public void deleteUser(
            @PathVariable
            @ApiParam(name = "id", required = true)
            int id){
        userService.deleteUser(id);

    }

    @PutMapping("/{id}")
    @ApiOperation("modify user")
    public UserDto ModifyUser(
            @PathVariable
            @ApiParam(name = "id", required = true)
            int id,
            @Valid @RequestBody
            @ApiParam(name = "UserDto", required = true)
            UserDto userDto){
        return userService.updateUser(userDto, id);
    }

}
