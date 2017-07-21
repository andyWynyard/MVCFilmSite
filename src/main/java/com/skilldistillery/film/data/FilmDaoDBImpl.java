package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDaoDBImpl implements FilmDao {
    
    private static String url = "jdbc:mysql://localhost:3306/sdvid";
    private String user = "student";
    private String pass = "student";

    public FilmDaoDBImpl() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error loading MySQL Driver!!!");
        }
    }

    @Override
    public Film getFilmById(int id) {
        Film title = null;
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            String sql = "Select id, title, description, release_year, rental_duration, rental_rate, length, replacement_cost, rating FROM film WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                title = new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getDouble(6), rs.getInt(7), rs.getDouble(8), rs.getString(9), createCast(rs.getInt(1)));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return title;
    }
    
    @Override
    public List<Film> getListOfFilmsBySearch(String search) {
        List<Film> movieList = new ArrayList<>();;
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            String sql = "Select id, title, description, release_year, rental_duration, rental_rate, length, replacement_cost, rating FROM film WHERE title LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + search + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Film x = new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getDouble(6), rs.getInt(7), rs.getDouble(8), rs.getString(9), createCast(rs.getInt(1)));
                movieList.add(x);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movieList;
    }
    
    public List<Actor> createCast(int filmId) {
        List<Actor> cast = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            String sql = "SELECT id, first_name, last_name FROM actor WHERE id IN (SELECT actor_id FROM film_actor WHERE film_id = ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, filmId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cast.add(new Actor(rs.getString(2), rs.getString(3), rs.getInt(1)));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cast;
        
    }

}