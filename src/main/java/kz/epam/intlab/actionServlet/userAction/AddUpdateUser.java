package kz.epam.intlab.actionServlet.userAction;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dto.UserDTO;
import kz.epam.intlab.service.Service;
import kz.epam.intlab.util.UserCredentialValidator;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/addUpdateUser")
public class AddUpdateUser extends HttpServlet {

    @EJB
    private Service service;

    @EJB
    private UserCredentialValidator validator;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO userDTO = new UserDTO();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("rePassword");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userDTO.setFirstName(firstName);
        userDTO.setLastName(lastName);

        List<String> errorMessages = null;
        try {
            errorMessages = validator.validateUser(userDTO, rePassword);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        if (errorMessages.isEmpty()) {
            try {
                service.addUpdateUser(userDTO);
                resp.sendRedirect("/openLoginPage");
            } catch (DaoException e) {
                e.printStackTrace();
            }

        } else {
            req.setAttribute("errorMessages", errorMessages);
            req.getRequestDispatcher("/WEB-INF/pages/reg.jsp").forward(req, resp);
        }
    }
}
