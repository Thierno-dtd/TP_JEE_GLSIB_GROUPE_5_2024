package GLSIB_GROUPE5.example.EgaSApplication.services;

import GLSIB_GROUPE5.example.EgaSApplication.dto.UserDto;
import GLSIB_GROUPE5.example.EgaSApplication.exceptions.EntityNotFoundException;

import java.util.List;

public interface IUserService {
    public List<UserDto> getAllUsers();
    public UserDto getOneUser(int id) throws EntityNotFoundException;
    public void deleteUser(int id);
    UserDto updateUser(UserDto userDto, int id);
}
