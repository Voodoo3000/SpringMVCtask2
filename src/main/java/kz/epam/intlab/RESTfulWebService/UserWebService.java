package kz.epam.intlab.RESTfulWebService;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dto.UserDTO;
import kz.epam.intlab.service.UserService;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/userWS")
@Stateless
@LocalBean
public class UserWebService {

    @EJB
    private UserService userService;

    @GET
    @Path("getById/{id}")
    public UserDTO getById(@PathParam("id") int id) throws DaoException {
        return userService.getUserById(id);
    }

    @POST
    @Path("/addUpdate")
    public void addUpdateUser(UserDTO userDTO) throws DaoException {
        userService.addUpdateUser(userDTO);
    }
}
