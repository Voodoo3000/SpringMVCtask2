package kz.epam.intlab.service;

import kz.epam.intlab.converter.DTOEntityConverter;
import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dao.UserDao;
import kz.epam.intlab.dto.UserDTO;
import kz.epam.intlab.entity.User;
import kz.epam.intlab.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserDao mockDao;

    @Mock(name = "userConverter")
    private DTOEntityConverter userConverter;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testAddUpdateUserWhenDataIsValid() throws DaoException {
        //GIVEN
        int givenUserId = 9;

        User givenUser = new User();
        givenUser.setId(givenUserId);
        givenUser.setFirstName("testName");
        givenUser.setLastName("testSurname");
        givenUser.setEmail("test@mail.com");
        givenUser.setPassword("test.pass");
        givenUser.setRole(User.Role.READER);

        UserDTO givenDTOUser = new UserDTO();
        givenDTOUser.setId(givenUserId);
        givenDTOUser.setFirstName("testName");
        givenDTOUser.setLastName("testSurname");
        givenDTOUser.setEmail("test@mail.com");
        givenDTOUser.setPassword("test.pass");
        givenDTOUser.setRole(User.Role.READER);

        when(userConverter.convertDTOToEntity(givenDTOUser)).thenReturn(givenUser);

        //WHEN
        userService.addUpdateUser(givenDTOUser);

        //THEN
        verify(mockDao).addUpdateUser(givenUser);

        System.out.println(givenUser.getId() + "; " + givenUser.getFirstName() + "; " + givenUser.getLastName() + "; " + givenUser.getEmail() + "; " + givenUser.getPassword() + "; " + givenUser.getRole());
    }

    @Test
    public void testGetUserById() throws DaoException {
        //GIVEN
        int givenUserId = 9;

        User givenUser = new User();

        UserDTO expectedUser = new UserDTO();
        UserDTO actualResult;

        expectedUser.setId(9);
        expectedUser.setFirstName("testName");
        expectedUser.setLastName("testSurname");
        expectedUser.setEmail("test@mail.com");
        expectedUser.setPassword("test.pass");
        expectedUser.setRole(User.Role.READER);

        when(mockDao.getUserById(givenUserId)).thenReturn(givenUser);
        when(userConverter.convertEntityToDTO(givenUser)).thenReturn(expectedUser);

        //WHEN
        actualResult = userService.getUserById(givenUserId);

        //THEN
        assertEquals(actualResult.getEmail(), expectedUser.getEmail());
        assertEquals(actualResult.getPassword(), expectedUser.getPassword());
        assertEquals(actualResult.getFirstName(), expectedUser.getFirstName());
        assertEquals(actualResult.getLastName(), expectedUser.getLastName());
        assertEquals(actualResult.getId(), expectedUser.getId());
        assertEquals(actualResult.getRole(), expectedUser.getRole());
    }

    @Test
    public void testGetUserByEmail() throws DaoException {
        //GIVEN
        String givenEmail = "test@mail.com";

        User givenUser = new User();

        UserDTO expectedUser = new UserDTO();
        UserDTO actualResult;

        expectedUser.setId(9);
        expectedUser.setFirstName("testName");
        expectedUser.setLastName("testSurname");
        expectedUser.setEmail("test@mail.com");
        expectedUser.setPassword("test.pass");
        expectedUser.setRole(User.Role.READER);

        when(mockDao.getUserByEmail(givenEmail)).thenReturn(givenUser);
        when(userConverter.convertEntityToDTO(givenUser)).thenReturn(expectedUser);

        //WHEN
        actualResult = userService.getUserByEmail(givenEmail);

        //THEN
        assertEquals(actualResult.getEmail(), expectedUser.getEmail());
        assertEquals(actualResult.getPassword(), expectedUser.getPassword());
        assertEquals(actualResult.getFirstName(), expectedUser.getFirstName());
        assertEquals(actualResult.getLastName(), expectedUser.getLastName());
        assertEquals(actualResult.getId(), expectedUser.getId());
        assertEquals(actualResult.getRole(), expectedUser.getRole());
    }
}