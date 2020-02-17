import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.UserServiceImpl;

public class UserServiceImplUTest {

    @InjectMocks
    private UserServiceImpl userService = new UserServiceImpl();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnNameAndEmail(){
        assertEquals("Name: Ana / Email: ana.souza", userService.addUser("Ana", "ana.souza"));
    }
}
