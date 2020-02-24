package service;

import static org.mockito.Mockito.*;
import dao.UserDAO;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceImplUTest {

    @InjectMocks
    private UserServiceImpl userService = new UserServiceImpl();

    @Mock
    UserDAO userDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCallDAOAddMethod_whenUserDoesntExists() {
        when(userDAO.findUserByEmail("joaquim.silva@gmail.com")).thenReturn(null);
        userService.addUser("Joaquim", "joaquim.silva@gmail.com");
        verify(userDAO, times(1)).addNewUser("Joaquim", "joaquim.silva@gmail.com", "ROLE_USER");
    }

    @Test
    public void shouldntCallDAOAddMethod_whenUserExists() {
        User user = new User();
        user.setName("Carlos");
        user.setEmail("carlos.almeida@gmail.com");
        when(userDAO.findUserByEmail("carlos.almeida@gmail.com")).thenReturn(user);
        userService.addUser("Carlos", "carlos.almeida@gmail.com");
        verify(userDAO, times(0)).addNewUser("Carlos", "carlos.almeida@gmail.com", "ROLE_USER");
    }

    @Test
    public void shouldHaveAdminRole_whenUserIsFromLulabs() {
        userService.addUser("Jasmin", "jasmin.couto@lulabs.com");
        verify(userDAO, times(1)).addNewUser("Jasmin", "jasmin.couto@lulabs.com", "ROLE_ADMIN");
    }

    @Test
    public void shouldCallDAOFindUserByIdMethod() {
        userService.getUser(1234);
        verify(userDAO, times(1)).findUserById(1234);
    }

    @Test
    public void shouldCallDAOFindAllMethod() {
        userService.getAllUsers();
        verify(userDAO, times(1)).findAll();
    }

    @Test
    public void shouldCallDAODeleteUser() {
        User user = new User();
        when(userService.getUser(123)).thenReturn(user);
        userService.removeUser(123);
        verify(userDAO, times(1)).deleteUser(123);
    }

    @Test
    public void shoudCallDAOUpdateUser() {
        User user = new User();
        user.setId(123);
        user.setName("Carlos");
        user.setEmail("carlos.c@gmail.com");
        when(userService.getUser(123)).thenReturn(user);
        userService.updateUser(123, "Carlos", "carlos.couto@gmail.com");
        verify(userDAO, times(1)).updateUser(123, "Carlos", "carlos.couto@gmail.com");
    }
}
