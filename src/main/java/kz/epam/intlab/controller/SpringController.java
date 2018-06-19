package kz.epam.intlab.controller;


import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dao.NewsDao;
import kz.epam.intlab.entity.Comment;
import kz.epam.intlab.entity.News;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class SpringController {

    @Autowired
    private NewsDao newsDao;
    private ModelAndView modelAndView;

//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
//    public String printHello(ModelMap model) {
//        model.addAttribute("message", "Hello Spring MVC Framework!");
//        return "hello";
//    }
//    @RequestMapping(value = "/student", method = RequestMethod.GET)
//    public ModelAndView student() {
//        return new ModelAndView("student", "command", new Student());
//    }
//    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
//    public String addStudent(@ModelAttribute("SpringWeb")Student student, ModelMap model) {
//        model.addAttribute("name", student.getName());
//        model.addAttribute("age", student.getAge());
//        model.addAttribute("id", student.getId());
//
//        return "result";
//    }
//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public String index() {
//        return "index_test";
//    }
//    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
//    public String redirect() {
//        return "redirect:finalPage";
//    }
//    @RequestMapping(value = "/finalPage", method = RequestMethod.GET)
//    public String finalPage() {
//        return "final";
//    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView openMainPage() {
        Map<Integer, News> newsMap = newsDao.getAllNews();
        modelAndView = new ModelAndView("main", "newsMap", newsMap);
        return modelAndView;
    }

    @RequestMapping(value = "/openAddNews", method = RequestMethod.GET)
    public ModelAndView openAddNewsPage() {
        return new ModelAndView("edit_mode", "newsModel", new News());
    }

    @RequestMapping(value = "/addUpNews", method = RequestMethod.POST)
    public String addUpdateNews(@ModelAttribute News newsModel) throws DaoException {
        newsDao.addUpdateNews(newsModel);
        return "redirect:main";
    }

    @RequestMapping(value = "/openSelectedNews", method = RequestMethod.GET)
    public ModelAndView openSelectedNews(@RequestParam int id) throws DaoException {
        News news = newsDao.getNewsById(id);
        return new ModelAndView("selected_news", "selectedNewsModel", news);
    }

    @RequestMapping(value = "/openEditMode", method = RequestMethod.POST)
    public ModelAndView openEditMode(@RequestParam int id) throws DaoException {
        News news = newsDao.getNewsById(id);
        return new ModelAndView("edit_mode", "newsModel", news);
    }

    @RequestMapping(value = "/deleteNews", method = RequestMethod.POST)
    public String deleteNews(@RequestParam int id) throws DaoException {
        newsDao.deleteNews(newsDao.getNewsById(id));
        return "redirect:main";
    }

    @RequestMapping(value = "/deleteSelectedNews", method = RequestMethod.POST)
    public String deleteSelectedNews(@RequestParam String[] deleteNewsCheckbox) throws DaoException {
        int selectedId;
        for (String s : deleteNewsCheckbox) {
            selectedId = Integer.parseInt(s);
            newsDao.deleteNews(newsDao.getNewsById(selectedId));
        }
        return "redirect:main";
    }
}