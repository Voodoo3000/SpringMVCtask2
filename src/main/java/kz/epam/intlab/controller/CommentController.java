package kz.epam.intlab.controller;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dto.CommentDTO;
import kz.epam.intlab.service.Service;

import javax.inject.Inject;

//@Controller
public class CommentController {

//    private Service service;
//
//    @Inject
//    public void setService(Service service) {
//        this.service = service;
//    }
//
//    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
//    public ModelAndView addComment(@ModelAttribute CommentDTO commentModel, @RequestParam int newsId) throws DaoException {
//        service.addComment(commentModel, newsId);
//        ModelAndView newModel = new ModelAndView();
//        newModel.setViewName("selected_news");
//        newModel.addObject("newsModel", service.getNewsById(newsId));
//        newModel.addObject("commentModel", new CommentDTO());
//        newModel.addObject("user", UserController.takeUsernameFromSecurityContext());
//        return newModel;
//    }
//
//    @RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
//    public ModelAndView deleteComment(@RequestParam String[] deleteCommentCheckbox, @RequestParam int newsId) throws DaoException {
//        ModelAndView model = new ModelAndView();
//        int commentId;
//        for (String s : deleteCommentCheckbox) {
//            commentId = Integer.parseInt(s);
//            service.deleteComment(commentId, newsId);
//        }
//        model.setViewName("selected_news");
//        model.addObject("newsModel", service.getNewsById(newsId));
//        model.addObject("user", UserController.takeUsernameFromSecurityContext());
//        return model;
//    }
}
