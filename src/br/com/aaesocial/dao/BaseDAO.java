package br.com.aaesocial.dao;

import br.com.aaesocial.model.FactoryUser;
import br.com.aaesocial.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

class BaseDAO {

    private static final String TAG = BaseDAO.class.getSimpleName();

    Connection connection;

    User instanceUser(ResultSet rs) {
        User user = null;
        FactoryUser factoryUser = new FactoryUser();
        try {
            user = factoryUser.getUser(rs.getBoolean("corporative"));

            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setEmail(rs.getString("email"));
            user.setBirthDate(rs.getDate("birthDate").toLocalDate());
            user.setPhotoUrl(rs.getString("photoUrl"));
            user.setBgColor(rs.getString("bgColor"));
        } catch (SQLException ex) {
            Logger.getLogger(TAG).log(Level.SEVERE, null, ex);
        }
        return user;
    }

}
