package GLSIB_GROUPE5.example.EgaSApplication.services.serviceImpl;

import GLSIB_GROUPE5.example.EgaSApplication.dto.UserDto;
import GLSIB_GROUPE5.example.EgaSApplication.entities.User;
import GLSIB_GROUPE5.example.EgaSApplication.exceptions.EntityNotFoundException;
import GLSIB_GROUPE5.example.EgaSApplication.exceptions.InvalidEntityException;
import GLSIB_GROUPE5.example.EgaSApplication.mappers.ApplicationsMapper;
import GLSIB_GROUPE5.example.EgaSApplication.repositories.UserRepository;
import GLSIB_GROUPE5.example.EgaSApplication.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository usersRepository;
    private final ApplicationsMapper applicationMappers;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> listUser =  usersRepository.findAll();
        return listUser.stream().map(users ->applicationMappers.convertEntityToDto(users)).collect(Collectors.toList());

    }

    @Override
    public UserDto getOneUser(int id) throws EntityNotFoundException {
        User user =  usersRepository.findById(id).orElse(null);
        if(user == null) throw new EntityNotFoundException("user not find");
        return applicationMappers.convertEntityToDto(user);
    }

    @Override
    public void deleteUser(int id) {
        User user = usersRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("user not find to delete"));
        usersRepository.delete(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, int id) {
        if(getOneUser(id) ==null) new InvalidEntityException("l'user que vous vouliez modifier n'existe pas");
        User user = applicationMappers.convertDtoToEntity(userDto);
        user.setId(id);
        return applicationMappers.convertEntityToDto(usersRepository.save(user));
    }
}
