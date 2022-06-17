package com.example.BookingAppTeam05.student1.JUnit;

import com.example.BookingAppTeam05.dto.users.UserDTO;
import com.example.BookingAppTeam05.model.Place;
import com.example.BookingAppTeam05.repository.users.UserRepository;
import com.example.BookingAppTeam05.model.users.Client;
import com.example.BookingAppTeam05.model.users.Role;
import com.example.BookingAppTeam05.model.users.User;
import com.example.BookingAppTeam05.service.PlaceService;
import com.example.BookingAppTeam05.service.users.*;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;
    @Autowired
    private PlaceService placeService;
    @Autowired
    private RoleService roleService;

    private @Mock List<User> users;

    @InjectMocks
    private UserService userService;

    @Test
    @Transactional
    @Rollback(true)
    void findUserById() {

        when(userRepositoryMock.findUserById(7L)).thenReturn(new Client("bookingapp05mzr++jescieMullins@gmail.com", "Jescie", "Mullins", "Ap #769-2030 Mauris. Rd.", LocalDate.of(1971,12,20), "034-33-356-88", "$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra", false, new Place(),new Role(), 0));

        User user = userService.findUserById(7L);

        assertEquals(user.getFirstName(), "Jescie");

        verify(userRepositoryMock, times(1)).findUserById(7L);

        verifyNoMoreInteractions(userRepositoryMock);
    }

    @Test
    @Transactional
    @Rollback(true)
    void updateUser() {
        Place place = placeService.getPlaceById(1L);
        Role role = roleService.findByName("ROLE_CLIENT");
        when(userRepositoryMock.findUserById(7L)).thenReturn(new Client("bookingapp05mzr++jescieMullins@gmail.com", "Jescie", "Mullins", "Ap #769-2030 Mauris. Rd.", LocalDate.of(1971,12,20), "034-33-356-88", "$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra", false, place,role, 0));

        User userForUpdate = userRepositoryMock.findUserById(7L);

        UserDTO userDTO = new UserDTO(userForUpdate);
        userDTO.setPlace(userForUpdate.getPlace());
        userDTO.setLastName("Janes");
        userService.updateUser(7L, userDTO);

        assertThat(userForUpdate).isNotNull();

        userForUpdate = userRepositoryMock.findUserById(7L);
        assertThat(userForUpdate.getFirstName()).isEqualTo("Jescie");
        assertThat(userForUpdate.getLastName()).isEqualTo("Janes");

        verify(userRepositoryMock, times(4)).findUserById(7L);
        verify(userRepositoryMock,times(1)).save(userForUpdate);
        verifyNoMoreInteractions(userRepositoryMock);
    }

    @Test
    @Transactional
    @Rollback(true)
    void getAllUsers() {

        when(userRepositoryMock.getAllUsers()).thenReturn(users);

        List<User> userList =  userService.getAllUsers();

        //assertThat()


    }
}