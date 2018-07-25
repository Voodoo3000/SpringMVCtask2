package kz.epam.intlab.util;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dto.UserDTO;
import kz.epam.intlab.service.Service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Stateless
public class UserCredentialValidator {

    @EJB
    private Service service;

    public List<String> validateUser(UserDTO userDTO, String rePassword) throws DaoException {

        List<String> errorMessages = new ArrayList<>();

        boolean emailValidResult = validateEmail(userDTO.getEmail());
        boolean passwordValidResult = validatePassword(userDTO.getPassword());
        boolean firstNameValidResult = validateNames(userDTO.getFirstName());
        boolean lastNameValidResult = validateNames(userDTO.getLastName());

        if (!emailValidResult) {
            errorMessages.add("Invalid email address");
        }
        if (service.getUserByEmail(userDTO.getEmail()) != null) {
            errorMessages.add("Entered login is busy");
        }
        if (!passwordValidResult) {
            errorMessages.add("Invalid password");
        }
        if (!userDTO.getPassword().equals(rePassword)) {
            errorMessages.add("Passwords mismatching");
        }
        if (!firstNameValidResult) {
            errorMessages.add("Invalid first name");
        }
        if (!lastNameValidResult) {
            errorMessages.add("Invalid last name");
        }
        return errorMessages;
    }

    private static boolean validateEmail(String email) {
        return Pattern.compile("[\\w\\u002E\\u005F]{0,20}@([a-zA-Z]+\\u002E){1,2}[a-zA-Z]{2,3}").matcher(email).matches();
    }

    private static boolean validatePassword(String password) {
        return Pattern.compile("[\\w]{3,20}").matcher(password).matches();
    }

    private static boolean validateNames(String names) {
        return Pattern.compile("([A-Z]{1}[a-z]{0,19})|([\\u0410-\\u042F]{1}[\\u0430-\\u044F]{0,19})").matcher(names).matches();
    }
}
