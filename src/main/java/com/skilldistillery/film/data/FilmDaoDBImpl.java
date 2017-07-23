package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

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
    public Actor getActorById(int id) {
        Actor actor = null;
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            String sql = "Select id, first_name, last_name FROM actor WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                actor = new Actor(rs.getString(3), rs.getString(2), rs.getInt(1));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(actor);
        return actor;
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
    
    
    @Override
    public List<Actor> getListOfActorsBySearch(String keyword) {
        List<Actor> actorList = new ArrayList<>();;
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            String sql = "SELECT id, first_name , last_name FROM actor WHERE first_name LIKE ? OR last_name LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Actor x = new Actor(rs.getString(2), rs.getString(3), rs.getInt(1));
                actorList.add(x);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actorList;
    }
    
    
    @Override
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
    @Override
    public Actor addActor(Actor actor) {
    	  Connection conn = null;
    	  try {
    	    conn = DriverManager.getConnection(url, user, pass);
    	    conn.setAutoCommit(false); // START TRANSACTION
    	    String sql = "INSERT INTO actor (first_name, last_name) "
    	                     + " VALUES (?,?)";
    	    PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    	    stmt.setString(1, actor.getFirstName());
    	    stmt.setString(2, actor.getLastName());
    	    int updateCount = stmt.executeUpdate();
    	    if (updateCount == 1) {
    	      ResultSet keys = stmt.getGeneratedKeys();
    	      if (keys.next()) {
    	        int newActorId = keys.getInt(1);
    	        actor.setId(newActorId);
    	        if (actor.getFilms() != null && actor.getFilms().size() > 0) {
    	          sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
    	          stmt = conn.prepareStatement(sql);
    	          for (Film film : actor.getFilms()) {
    	            stmt.setInt(1, film.getId());
    	            stmt.setInt(2, newActorId);
    	            updateCount = stmt.executeUpdate();
    	          }
    	        }
    	      }
    	    } else {
    	      actor = null;
    	    }
    	    conn.commit(); // COMMIT TRANSACTION
    	  } catch (SQLException sqle) {
    	    sqle.printStackTrace();
    	    if (conn != null) {
    	      try { conn.rollback(); }
    	      catch (SQLException sqle2) {
    	        System.err.println("Error trying to rollback");
    	      }
    	    }
    	    throw new RuntimeException("Error inserting actor " + actor);
    	  }
    	  return actor;
    	}
    @Override
    public boolean deleteActor(Actor actor) {
    	System.out.println("in delect actor");
    	  Connection conn = null;
    	  try {
    	    conn = DriverManager.getConnection(url, user, pass);
    	    conn.setAutoCommit(false); // START TRANSACTION
    	    String sql = "DELETE FROM film_actor WHERE actor_id = ?";
    	    PreparedStatement stmt = conn.prepareStatement(sql);
    	    stmt.setInt(1, actor.getId());
    	    int updateCount = stmt.executeUpdate();
    	    String sql2 = "DELETE FROM actor WHERE id = ?";
    	    PreparedStatement stmt2 = conn.prepareStatement(sql2);
    	    stmt2.setInt(1, actor.getId());
    	    updateCount = stmt2.executeUpdate();
    	    conn.commit();             // COMMIT TRANSACTION
    	  }
    	  catch (SQLException sqle) {
    	    sqle.printStackTrace();
    	    if (conn != null) {
    	      try { conn.rollback(); }
    	      catch (SQLException sqle2) {
    	        System.err.println("Error trying to rollback");
    	      }
    	    }
    	    return false;
    	  }
    	  return true;
    	}
    
    @Override
    public boolean saveActor(Actor actor) {
    	  Connection conn = null;
    	  try {
    	    conn = DriverManager.getConnection(url, user, pass);
    	    conn.setAutoCommit(false); // START TRANSACTION
    	    String sql = "UPDATE actor SET first_name=?, last_name=? "
    	               + " WHERE id=?";
    	    PreparedStatement stmt = conn.prepareStatement(sql);
    	    stmt.setString(1, actor.getFirstName());
    	    stmt.setString(2, actor.getLastName());
    	    stmt.setInt(3, actor.getId());
    	    int updateCount = stmt.executeUpdate();
    	    System.out.println(actor);
//    	    if (updateCount == 1) {
//    	      // Replace actor's film list
//    	      sql = "DELETE FROM film_actor WHERE actor_id = ?";
//    	      stmt = conn.prepareStatement(sql);
//    	      stmt.setInt(1, actor.getId());
//    	      updateCount = stmt.executeUpdate();
//    	      sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
//    	      stmt = conn.prepareStatement(sql);
//    	      for (Film film : actor.getFilms()) {
//    	        stmt.setInt(1, film.getId());
//    	        stmt.setInt(2, actor.getId());
//    	        updateCount = stmt.executeUpdate();
//    	      }
    	      conn.commit();           // COMMIT TRANSACTION
//    	    }
    	  } catch (SQLException sqle) {
    	    sqle.printStackTrace();
    	    if (conn != null) {
    	      try { conn.rollback(); } // ROLLBACK TRANSACTION ON ERROR
    	      catch (SQLException sqle2) {
    	        System.err.println("Error trying to rollback");
    	      }
    	    }
    	    return false;
    	  }
    	  return true;
    	}

}