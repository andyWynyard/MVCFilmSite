package com.skilldistillery.film.data;

import java.util.List;

public interface FilmDao {
    Film getFilmById(int id);
    List<Film> getListOfFilmsBySearch(String search);
	List<Actor> getListOfActorsBySearch(String keyword);
	List<Actor> createCast(int filmId);
	Actor addActor(Actor actor);
	boolean deleteActor(Actor actor);
	boolean saveActor(Actor actor);
	Actor getActorById(int id);
	Film addFilm(Film film);
	int deleteFilm(int id);
	Actor addActorToFilm(Actor actor, Film film);
}