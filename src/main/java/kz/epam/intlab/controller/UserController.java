package kz.epam.intlab.controller;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dto.UserDTO;
import kz.epam.intlab.service.Service;
import kz.epam.intlab.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    public static String takeUsernameFromSecurityContext() {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        if (currentUser.contains("anonymousUser")) {
            currentUser = "guest";
        }
        return currentUser;
    }

    @Autowired
    private Service service;

    @Autowired
    private Validator validator;

    @RequestMapping(value = "openLoginPage", method = RequestMethod.GET)
    public String openLoginPage() {
        return "login";
    }

    @RequestMapping(value = "openRegisterPage", method = RequestMethod.GET)
    public ModelAndView openRegisterPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("reg");
        model.addObject("userModel", new UserDTO());
        return model;
    }

    @RequestMapping(value = "/addUpdateUser", method = RequestMethod.POST)
    public ModelAndView addUpdateUser(@ModelAttribute UserDTO userDTO, @RequestParam String rePassword) throws DaoException {
        List<String> errorMessages = validator.validateUser(userDTO, rePassword);
        ModelAndView model = new ModelAndView();
        if (errorMessages.isEmpty()) {
            service.addUpdateUser(userDTO);
            model.setViewName("login");
        } else {
            model.setViewName("reg");
            model.addObject("errorMessages", errorMessages);
            model.addObject("userModel", new UserDTO());
        }
        return model;
    }
}
