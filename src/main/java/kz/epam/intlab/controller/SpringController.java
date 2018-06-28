package kz.epam.intlab.controller;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dto.DTOModel;
import kz.epam.intlab.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringController {

    @Autowired
    private Service service;

    private static String takeUsernameFromSecurityContext() {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        if (currentUser.contains("anonymousUser")) {
            currentUser = "guest";
        }
        return currentUser;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView openMainPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("main");
        model.addObject("newsMap", service.getAllNews());
        model.addObject("user", SpringController.takeUsernameFromSecurityContext());
        return model;
    }

    @RequestMapping(value = "/openAddNews", method = RequestMethod.GET)
    public ModelAndView openAddNewsPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("edit_mode");
        model.addObject("model", new DTOModel());
        model.addObject("user", SpringController.takeUsernameFromSecurityContext());
        return model;
    }

    @RequestMapping(value = "/addUpNews", method = RequestMethod.POST)
    public String addUpdateNews(@ModelAttribute DTOModel model, @RequestParam int id) throws DaoException {
        service.addUpdateNews(model);
        return "redirect:main";
    }

    @RequestMapping(value = "/openSelectedNews", method = RequestMethod.GET)
    public ModelAndView openSelectedNews(@RequestParam int id) throws DaoException {
        ModelAndView model = new ModelAndView();
        model.setViewName("selected_news");
        model.addObject("model", service.getNewsById(id));
        model.addObject("user", SpringController.takeUsernameFromSecurityContext());
        return model;
    }

    @RequestMapping(value = "/openEditMode", method = RequestMethod.POST)
    public ModelAndView openEditMode(@RequestParam int id) throws DaoException {
        ModelAndView model = new ModelAndView();
        model.setViewName("edit_mode");
        model.addObject("model", service.getNewsById(id));
        model.addObject("user", SpringController.takeUsernameFromSecurityContext());
        return model;
//        return new ModelAndView("edit_mode", "model", service.getNewsById(id));
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
        ModelAndView newModel = new ModelAndView();
        newModel.setViewName("selected_news");
        newModel.addObject("model", service.getNewsById(id));
        newModel.addObject("user", SpringController.takeUsernameFromSecurityContext());
//        return new ModelAndView("selected_news", "model", service.getNewsById(id));
        return newModel;
    }

    @RequestMapping(value = "/deleteCommentNews", method = RequestMethod.POST)
    public ModelAndView deleteComment(@RequestParam String[] deleteCommentCheckbox, @RequestParam int newsId) throws DaoException {
        ModelAndView model = new ModelAndView();
        int commentId;
        for (String s : deleteCommentCheckbox) {
            commentId = Integer.parseInt(s);
            service.deleteComment(commentId, newsId);
        }
        model.setViewName("selected_news");
        model.addObject("model", service.getNewsById(newsId));
        model.addObject("user", SpringController.takeUsernameFromSecurityContext());
//        return new ModelAndView("selected_news", "model", service.getNewsById(newsId));
        return model;
    }

    @RequestMapping(value = "openLoginPage", method = RequestMethod.GET)
    public String openLoginPage() {
        return "login";
    }
}