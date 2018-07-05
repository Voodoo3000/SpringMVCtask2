package kz.epam.intlab.controller;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dto.CommentDTO;
import kz.epam.intlab.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommentController {

    @Autowired
    private Service service;

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public ModelAndView addComment(@ModelAttribute CommentDTO commentModel, @RequestParam int newsId) throws DaoException {
        service.addComment(commentModel, newsId);
        ModelAndView newModel = new ModelAndView();
        newModel.setViewName("selected_news");
        newModel.addObject("newsModel", service.getNewsById(newsId));
        newModel.addObject("commentModel", new CommentDTO());
        newModel.addObject("user", UserController.takeUsernameFromSecurityContext());
        return newModel;
    }

    @RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
    public ModelAndView deleteComment(@RequestParam String[] deleteCommentCheckbox, @RequestParam int newsId) throws DaoException {
        ModelAndView model = new ModelAndView();
        int commentId;
        for (String s : deleteCommentCheckbox) {
            commentId = Integer.parseInt(s);
            service.deleteComment(commentId, newsId);
        }
        model.setViewName("selected_news");
        model.addObject("newsModel", service.getNewsById(newsId));
        model.addObject("user", UserController.takeUsernameFromSecurityContext());
        return model;
    }
}
