package kz.epam.intlab.controller;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dao.NewsDao;
import kz.epam.intlab.dto.DTOModel;
import kz.epam.intlab.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringController {
    
    @Autowired
    private Service service;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView openMainPage() {
        return new ModelAndView("main", "newsMap", service.getAllNews());
    }

    @RequestMapping(value = "/openAddNews", method = RequestMethod.GET)
    public ModelAndView openAddNewsPage() {
        return new ModelAndView("edit_mode", "model", new DTOModel());
    }

    @RequestMapping(value = "/addUpNews", method = RequestMethod.POST)
    public String addUpdateNews(@ModelAttribute DTOModel model, @RequestParam int id) throws DaoException {
        System.out.println(id);
        service.addUpdateNews(model);
        return "redirect:main";
    }

    @RequestMapping(value = "/openSelectedNews", method = RequestMethod.GET)
    public ModelAndView openSelectedNews(@RequestParam int id) throws DaoException {
        return new ModelAndView("selected_news", "model", service.getNewsById(id));
    }

    @RequestMapping(value = "/openEditMode", method = RequestMethod.POST)
    public ModelAndView openEditMode(@RequestParam int id) throws DaoException {
        return new ModelAndView("edit_mode", "model", service.getNewsById(id));
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

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public ModelAndView addComment(@ModelAttribute DTOModel model, @RequestParam int id) throws DaoException {
        service.addComment(model, id);
        return new ModelAndView("selected_news", "model", service.getNewsById(id));
    }

    @RequestMapping(value = "/deleteCommentNews", method = RequestMethod.POST)
    public ModelAndView deleteComment(@RequestParam String[] deleteCommentCheckbox, @RequestParam int newsId) throws DaoException {
        int commentId;
        for (String s : deleteCommentCheckbox) {
            commentId = Integer.parseInt(s);
            service.deleteComment(commentId, newsId);
        }
        return new ModelAndView("selected_news", "model", service.getNewsById(newsId));
    }
}