package kz.epam.intlab.service;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dto.UserDTO;

import javax.ejb.Local;

@Local
public interface UserService {
    public void addUpdateUser(UserDTO userDTO) throws DaoException;
    public UserDTO getUserByEmail(String email) throws DaoException;
    public UserDTO getUserById(int id) throws DaoException;
}
