package kz.epam.intlab.converter;

import kz.epam.intlab.dto.UserDTO;
import kz.epam.intlab.entity.User;

import javax.ejb.Stateless;

@Stateless
public class UserConverterImpl implements DTOEntityConverter<User, UserDTO> {

    public User convertDTOToEntity(UserDTO userDTO) {

        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(User.Role.READER);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        return user;
    }

    public UserDTO convertEntityToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        return userDTO;
    }
}
