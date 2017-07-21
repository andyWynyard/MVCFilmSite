package com.skilldistillery.film.data;

import java.util.List;

public interface FilmDao {
    Film getFilmById(int id);
    List<Film> getListOfFilmsBySearch(String search);
}