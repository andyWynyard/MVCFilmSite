package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.data.Film;
import com.skilldistillery.film.data.FilmDao;

@Controller
public class FilmController {
    
    @Autowired
    private FilmDao dao;
    
    @RequestMapping(path = "home.do")
    public ModelAndView home() {
      return new ModelAndView("WEB-INF/views/home.jsp");
    }

    @RequestMapping(path = "getTitle.do")
    public ModelAndView getFilmTitleById(@RequestParam(name = "filmId") Integer filmId) {
      String viewName = "WEB-INF/views/home.jsp";
      ModelAndView mv = new ModelAndView(viewName);
      Film title = dao.getFilmById(filmId);
      mv.addObject("filmTitle", title);
      return mv;
    }
    
    @RequestMapping(path = "searchTitle.do")
    public ModelAndView getListOfFilmsBySearch(@RequestParam(name = "search")String search) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("WEB-INF/views/home.jsp");
        mv.addObject("filmList", dao.getListOfFilmsBySearch(search));
        return mv;
    }

}