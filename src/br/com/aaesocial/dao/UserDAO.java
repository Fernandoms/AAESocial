package br.com.aaesocial.dao;

import br.com.aaesocial.model.FactoryUser;
import br.com.aaesocial.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    private static final String TAG = UserDAO.class.getSimpleName();

    private Connection connection;

    public UserDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public void insertUser(User user) {
        String sql = "insert into user(email, password, firstName, lastName, birthDate, photoUrl, bgColor) values" +
                "(?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setDate(5, Date.valueOf(user.getBirthDate()));
            statement.setString(6, user.getPhotoUrl());
            statement.setString(7, user.getBgColor());

            statement.execute();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(TAG).log(Level.SEVERE, null, ex);
        }
    }

    public List<User> findAllUsers(User except) {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                if (except.getId() != rs.getInt("id")) {
                    FactoryUser factoryUser = new FactoryUser();
                    User user = factoryUser.getUser(rs.getBoolean("corporative"));

                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setFirstName(rs.getString("firstName"));
                    user.setLastName(rs.getString("lastName"));
                    user.setPhotoUrl(rs.getString("photoUrl"));
                    user.setBgColor(rs.getString("bgColor"));
                    user.setBirthDate(rs.getDate("birthDate").toLocalDate());
                    users.add(user);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TAG).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    public User getUser(String login, String password) {
        User user = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE email = ? AND password = ?");
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                FactoryUser factoryUser = new FactoryUser();
                user = factoryUser.getUser(rs.getBoolean("corporative"));

                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setBirthDate(rs.getDate("birthDate").toLocalDate());
                user.setPhotoUrl(rs.getString("photoUrl"));
                user.setBgColor(rs.getString("bgColor"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TAG).log(Level.SEVERE, null, ex);
        }

        return user;
    }


    public User getUser(int id) {
        User user = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                FactoryUser factoryUser = new FactoryUser();
                user = factoryUser.getUser(rs.getBoolean("corporative"));

                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setBirthDate(rs.getDate("birthDate").toLocalDate());
                user.setPhotoUrl(rs.getString("photoUrl"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TAG).log(Level.SEVERE, null, ex);
        }

        return user;
    }
}
