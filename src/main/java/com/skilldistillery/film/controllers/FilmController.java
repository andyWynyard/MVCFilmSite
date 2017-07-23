package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.data.Actor;
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
	public ModelAndView getListOfFilmsBySearch(@RequestParam(name = "search") String search) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/home.jsp");
		mv.addObject("filmList", dao.getListOfFilmsBySearch(search));
		return mv;
	}

	@RequestMapping(path = "searchActor.do")
	public ModelAndView getListOfActorsBySearch(@RequestParam(name = "searchActor") String search) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/home.jsp");
		mv.addObject("actorList", dao.getListOfActorsBySearch(search));
		return mv;
	}

	@RequestMapping(path = "deleteActor.do", method = RequestMethod.POST)
	public ModelAndView deleteActor(@RequestParam(name = "actorId") int actorId) {
		ModelAndView mv = new ModelAndView();
		System.out.println(actorId);
		Actor actor = dao.getActorById(actorId);
		String name = actor.getFirstName() + actor.getLastName() + " with ID of " + actor.getId() + " was deleted";
		System.out.println(actor.toString() + dao.deleteActor(actor));	
		mv.setViewName("WEB-INF/views/home.jsp");
		mv.addObject("name", name);
		return mv;
	}
	
	@RequestMapping(path = "editActor.do", method = RequestMethod.POST)
	public ModelAndView editActor(@RequestParam(name = "id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		ModelAndView mv = new ModelAndView();
		Actor actor = new Actor(firstName, lastName, id);
		dao.saveActor(actor);
		mv.setViewName("WEB-INF/views/home.jsp");
		mv.addObject("alert", "Actor was edited");
		return mv;
	}
	@RequestMapping(path = "callEditPage.do", method = RequestMethod.GET)
	public ModelAndView goToEditPage(@RequestParam(name = "actorId") int actorId) {
		ModelAndView mv = new ModelAndView();
		Actor actor = dao.getActorById(actorId);
		
		mv.setViewName("WEB-INF/views/actorView.jsp");
		mv.addObject("actor", actor);
		return mv;
	}
	
	@RequestMapping(path = "callAddActorPage.do", method = RequestMethod.GET)
	public ModelAndView goToAddActorPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/addActor.jsp");		
		return mv;
	}
	
	@RequestMapping(path = "addActor.do", method = RequestMethod.POST)
	public ModelAndView addActor(@RequestParam(name = "id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		Actor actor = new Actor(firstName, lastName, id);
		dao.addActor(actor);	
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/home.jsp");	
		mv.addObject("alert", "Actor was added");
		return mv;
	}
}