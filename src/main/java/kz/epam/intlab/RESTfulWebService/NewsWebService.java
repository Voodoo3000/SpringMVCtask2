package kz.epam.intlab.RESTfulWebService;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dto.NewsDTO;
import kz.epam.intlab.service.NewsService;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Map;

@Path("/newsWS")
@Stateless
@LocalBean
public class NewsWebService {

    @EJB
    private NewsService newsService;

    @GET
    @Path("/getById/{id}")
    public NewsDTO getById (@PathParam("id") int id) throws DaoException {
        return newsService.getNewsById(id);
    }

    @GET
    @Path("/getAll")
    public Map<Integer, NewsDTO> getAll(){
        return newsService.getAllNews();
    }

    @POST
    @Path("/addUpdate")
    public void addUpdate(NewsDTO newsDTO) throws DaoException {
        newsService.addUpdateNews(newsDTO);
    }

    @POST
    @Path("/delete/{id}")
    public void delete(@PathParam("id") int id) throws DaoException {
        newsService.deleteNews(id);
    }

}
