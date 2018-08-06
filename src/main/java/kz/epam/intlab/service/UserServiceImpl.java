package kz.epam.intlab.service;

import kz.epam.intlab.converter.DTOEntityConverter;
import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dao.UserDao;
import kz.epam.intlab.dto.UserDTO;
import kz.epam.intlab.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UserServiceImpl implements UserService {

    @EJB
    private UserDao userDao;

    @EJB(beanName = "UserConverterImpl")
    private DTOEntityConverter userConverter;

    @Override
    public void addUpdateUser(UserDTO userDTO) throws DaoException {
        userDao.addUpdateUser((User) userConverter.convertDTOToEntity(userDTO));
    }

    @Override
    public UserDTO getUserByEmail(String email) throws DaoException {
        UserDTO userDTO = null;

        if (userDao.getUserByEmail(email) != null) {
            userDTO = (UserDTO) userConverter.convertEntityToDTO(userDao.getUserByEmail(email));
        }
        return userDTO;
    }

    @Override
    public UserDTO getUserById(int id) throws DaoException {
        User user = userDao.getUserById(id);
        return (UserDTO) userConverter.convertEntityToDTO(user);
    }
}
