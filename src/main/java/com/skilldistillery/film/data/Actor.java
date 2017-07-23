package com.skilldistillery.film.data;

import java.util.ArrayList;
import java.util.List;

public class Actor {
    private String firstName;
    private String lastName;
    private int id;
    private List<Film> films;
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Actor(String firstName, String lastName, int id) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }
    public Actor() {
    }
    @Override
    public String toString() {
        return "Actor [firstName=" + firstName + ", lastName=" + lastName + ", id=" + id + "]";
    }
	public List<Film> getFilms() {
		return films;
	}
	
	public boolean addFilm(Film film) {
		if (films == null) {
			films = new ArrayList<>();
		}
		return films.add(film);
	}
	public boolean removeFilm(Film film) {
		if (films != null) {
			return films.remove(film);
		}
		else {
			return false;
		}
	}
    
}