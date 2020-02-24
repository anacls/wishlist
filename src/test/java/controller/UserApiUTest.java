package controller;

import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.UserService;
import spark.Request;
import spark.Response;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class UserApiUTest {
    @InjectMocks
    UserApi userApi = new UserApi();

    @Mock
    UserService userService;

    @Mock
    Response response;

    @Mock
    Request request;

    @Before
    public void onSetup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnUserInformation_whenUserExists() {
        User user = new User();
        user.setId(12345);
        user.setEmail("email-test@gmail.com");
        user.setName("Test");
        user.setRole("ROLE_USER");
        when(userService.getUser(12345)).thenReturn(user);
        when(request.params("id")).thenReturn("12345");
        assertEquals("Usuário\n Nome: Test, Email: email-test@gmail.com, Role: ROLE_USER", userApi.getUser(request, response));
    }

    @Test
    public void shouldReturnNonExistentUserMessage_whenUserDoesntExist() {
        when(request.params("id")).thenReturn("12345");
        assertEquals("Usuário não existente na base de dados", userApi.getUser(request, response));
    }

    @Test
    public void shouldReturnErrorWhileCreatingUser() {
        assertEquals("Não foi possível criar um novo usuário, verifique se esse usuário já existe", userApi.addUser(request, response));
    }

    @Test
    public void shouldReturnUserCreatedSuccessfully_whenCreateUser() {
        User user = new User();
        when(userService.addUser("joao", "joao@gmail.com")).thenReturn(user);
        when(request.queryParams("name")).thenReturn("joao");
        when(request.queryParams("email")).thenReturn("joao@gmail.com");
        assertEquals("Usuário criado com sucesso", userApi.addUser(request, response));
    }

    @Test
    public void shouldReturnUsersList() {
        List<User> userList = new LinkedList<>();
        User user1 = new User();
        user1.setName("Joao");
        user1.setEmail("joao@gmail.com");
        user1.setRole("ROLE_USER");

        User user2 = new User();
        user2.setName("Ana");
        user2.setEmail("ana@gmail.com");
        user2.setRole("ROLE_USER");

        userList.add(user1);
        userList.add(user2);

        when(userService.getAllUsers()).thenReturn(userList);
        assertEquals("Usuários\nNome: Joao, Email: joao@gmail.com, Role: ROLE_USER\nNome: Ana, Email: ana@gmail.com, Role: ROLE_USER\n", userApi.getAllUsers());
    }

    @Test
    public void shouldReturnErrorMessage_whenIsntPossibleDeleteUser() {
        User user = new User();
        user.setId(123);
        when(userService.getUser(123)).thenReturn(user);
        when(request.params("id")).thenReturn("123");
        assertEquals("Não foi possível excluir o usuário", userApi.deleteUser(request, response));
    }

    @Test
    public void shouldReturnUserDeletedSuccessfully_whenDeleteUser() {
        when(userService.getUser(123)).thenReturn(null);
        when(request.params("id")).thenReturn("123");
        assertEquals("Excluido com sucesso", userApi.deleteUser(request, response));
    }

    @Test
    public void shouldReturnErrorMessage_whenIsntPossibleUpdateUser() {
        when(request.params("id")).thenReturn("123");
        assertEquals("Não foi possível atualizar usuário, usuário não existe", userApi.updateUser(request, response));
    }

    @Test
    public void shouldReturnUserUpdatedSuccessfully_whenUpdateUser() {
        User user = new User();
        user.setId(123);
        when(userService.updateUser(123, "Joao", "joao@gmail.com")).thenReturn(user);
        when(request.params("id")).thenReturn("123");
        when(request.queryParams("name")).thenReturn("Joao");
        when(request.queryParams("email")).thenReturn("joao@gmail.com");
        assertEquals("Usuário atualizado com sucesso", userApi.updateUser(request, response));
    }
}
