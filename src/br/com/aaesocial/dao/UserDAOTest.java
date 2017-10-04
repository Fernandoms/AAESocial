package br.com.aaesocial.dao;

import br.com.aaesocial.model.User;
import org.junit.Assert;

import java.time.LocalDate;
import java.util.List;

public class UserDAOTest {


    /**
     *  TESTE MAIS FAJUTO DA VIDA PARA TESTAR O BANCO
     */
    @org.junit.Test
    public void insertUser() throws Exception {
        UserDAO userDAO = new UserDAO();
        User user = new User();
        user.setFirstName("Fernando");
        user.setLastName("Marques");
        user.setEmail("fernando@email.com");
        user.setBirthDate(LocalDate.of(1994, 2, 6));
        user.setPassword("123456");
        user.setPhotoUrl("wwww.photo.com");
        userDAO.insertUser(user);
        List<User> userList = userDAO.findAllUsers();
        for (User index : userList) {
            if (user.getLastName().equals(index.getLastName())) {
                Assert.assertEquals(user.getEmail(), index.getEmail());
            }
        }

    }
}