package info.unproj.service;

import info.unproj.AppRunner;
import info.unproj.dao.UserDAO;
import info.unproj.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringJUnitConfig(AppRunner.class)
@ActiveProfiles("test")
class UserServiceTest {

    @MockBean
    UserDAO userDAO;

    @Autowired
    UserService userService;

    @Test
    void getByLoginAndPassword() {
        User user = new User("testLogin", "testPassword", "testName", "testSurname");
        when(userDAO.getFirstByLoginAndPassword("testLogin", "testPassword")).thenReturn(user);

        User savedUser = userService.getByLoginAndPassword("testLogin", "testPassword");

        assertEquals(savedUser.getFirstName(), "testName");

        verify(userDAO, times(1)).getFirstByLoginAndPassword("testLogin", "testPassword");
    }
}