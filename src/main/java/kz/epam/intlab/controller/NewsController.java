package kz.epam.intlab.controller;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dto.CommentDTO;
import kz.epam.intlab.dto.NewsDTO;
import kz.epam.intlab.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewsController {

    @Autowired
    private Service service;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView openMainPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("main");
        model.addObject("newsMap", service.getAllNews());
        model.addObject("user", UserController.takeUsernameFromSecurityContext());
        return model;
    }

    @RequestMapping(value = "/openAddNews", method = RequestMethod.GET)
    public ModelAndView openAddNewsPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("edit_mode");
        model.addObject("newsModel", new NewsDTO());
        model.addObject("user", UserController.takeUsernameFromSecurityContext());
        return model;
    }

    @RequestMapping(value = "/addUpNews", method = RequestMethod.POST)
    public String addUpdateNews(@ModelAttribute NewsDTO newsModel) throws DaoException {
        service.addUpdateNews(newsModel);
        return "redirect:main";
    }

    @RequestMapping(value = "/openSelectedNews", method = RequestMethod.GET)
    public ModelAndView openSelectedNews(@RequestParam int id) throws DaoException {
        ModelAndView model = new ModelAndView();
        model.setViewName("selected_news");
        model.addObject("newsModel", service.getNewsById(id));
        model.addObject("commentModel", new CommentDTO());
        model.addObject("user", UserController.takeUsernameFromSecurityContext());
        return model;
    }

    @RequestMapping(value = "/openEditMode", method = RequestMethod.POST)
    public ModelAndView openEditMode(@RequestParam int id) throws DaoException {
        ModelAndView model = new ModelAndView();
        model.setViewName("edit_mode");
        model.addObject("newsModel", service.getNewsById(id));
        model.addObject("user", UserController.takeUsernameFromSecurityContext());
        return model;
    }

    @RequestMapping(value = "/deleteNews", method = RequestMethod.POST)
    public String deleteNews(@RequestParam int id) throws DaoException {
        service.deleteNews(id);
        return "redirect:main";
    }

    @RequestMapping(value = "/deleteSelectedNews", method = RequestMethod.POST)
    public String deleteSelectedNews(@RequestParam String[] deleteNewsCheckbox) throws DaoException {
        int newsId;
        for (String s : deleteNewsCheckbox) {
            newsId = Integer.parseInt(s);
            service.deleteNews(newsId);
        }
        return "redirect:main";
    }


}