package dao;

import entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserHibDAOITest {
    private User user, user2;
    private UserDAO userDAO = new UserHibDaoImpl();

    @Before
    public void onSetup() {
        user = userDAO.addNewUser("Test User", "test-user-1@gmail.com", "ROLE_USER");
        user2 = userDAO.addNewUser("Test User 2", "test-user-2@gmail.com", "ROLE_USER");
    }

    @After
    public void after() {
        userDAO.deleteUser(user.getId());
        userDAO.deleteUser(user2.getId());
    }
    @Test
    public void shouldReturnUser_whenUserIdExists() {
        assertEquals(user.getId(), userDAO.findUserById(user.getId()).getId());
    }

    @Test
    public void shouldReturnUser_whenUserEmailExists() {
        assertEquals(user.getEmail(), userDAO.findUserByEmail(user.getEmail()).getEmail());
    }

    @Test
    public void shouldReturnNull_whenUserDoesntExists() {
        assertNull(userDAO.findUserByEmail("test-user-3@gmail.com"));
    }

    @Test
    public void shouldReturnRecentAddedUser() {
        userDAO.addNewUser("Test user", "test-user-3@gmail.com", "ROLE_USER");
        User newUser = userDAO.findUserByEmail("test-user-3@gmail.com");
        assertNotNull(newUser);

        //delete user after test
        userDAO.deleteUser(newUser.getId());
    }

    @Test
    public void shouldReturnUsersList(){
        assertTrue(userDAO.findAll().size() >= 2);
    }

    @Test
    public void shouldDeleteUser() {
        userDAO.addNewUser("Test user", "test-user-3@gmail.com", "ROLE_USER");
        User newUser = userDAO.findUserByEmail("test-user-3@gmail.com");
        userDAO.deleteUser(newUser.getId());
        assertNull(userDAO.findUserByEmail("test-user-3@gmail.com"));
    }

    @Test
    public void shouldUpdateUser() {
        User newUser = userDAO.addNewUser("Test user", "test-user-3@gmail.com", "ROLE_USER");
        userDAO.updateUser(newUser.getId(), "Test user", "changedemail@gmail.com");
        assertEquals("changedemail@gmail.com", userDAO.findUserById(newUser.getId()).getEmail());
    }
}
