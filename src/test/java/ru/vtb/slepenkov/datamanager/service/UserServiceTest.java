package ru.vtb.slepenkov.datamanager.service;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vtb.slepenkov.datamanager.exceptions.UserNotFoundException;
import ru.vtb.slepenkov.datamanager.model.User;
import ru.vtb.slepenkov.datamanager.model.Vacation;
import ru.vtb.slepenkov.datamanager.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest extends TestCase {


    @Autowired
    IUserService userService;

    @Autowired
    UserRepository repository;

    private User defaultUser(int number) {
        User user = new User();
        user.setLogin("user_" + number);
        user.setPassword("user1234");
        user.setEmail("user" + number + "@gmail.com");
        user.setVacationsList(new ArrayList<>());
        return user;
    }

    private Vacation defaultVacation(User user) {
        Vacation vacation = new Vacation();
        LocalDate from = LocalDate.now();
        LocalDate to = LocalDate.now().plusDays(30);
        vacation.setFrom(from);
        vacation.setTo(to);
        vacation.setUser(user);
        return vacation;
    }

    private List<Vacation> generateVacations(int numVacations, User user) {
        List<Vacation> vacations = new ArrayList<>();
        for (int i = 0; i < numVacations; i++) {
            vacations.add(defaultVacation(user));
        }
        return vacations;
    }

    @Test
    public void getFirstPageOfUsersTest() {
        List<User> arrayOfUsers = new ArrayList<>();
        try {
            for (int i = 0; i < 40; i++) {
                User current = defaultUser(i);
                arrayOfUsers.add(current);
                userService.create(current);
            }
            Page<User> firstPage = userService.list(PageRequest.of(0, 10));
            assertThat(firstPage.getSize()).isEqualTo(10);
            assertThat(firstPage.getNumber()).isEqualTo(0);
        } finally {
            for (var user : arrayOfUsers) {
                repository.deleteById(user.getId());
            }
        }
    }

    @Test
    public void findByCorrectIdTest() {
        User newUser = userService.create(defaultUser(1));
        Long fromDB = userService.findById(newUser.getId()).getId();
        repository.deleteById(fromDB);
        assertThat(fromDB).isEqualTo(newUser.getId());
    }

    @Test(expected = UserNotFoundException.class)
    public void findByNonExistentIdTest() {
        userService.findById(0L);
    }

    @Test
    public void updateUserByIdTest() {
        User newUser = userService.create(defaultUser(1));
        String description = "Hello";
        newUser.setDescription(description);
        User updatedUser = userService.update(newUser.getId(), newUser);
        repository.deleteById(newUser.getId());

        if (repository.existsById(updatedUser.getId())) {
            repository.deleteById(updatedUser.getId());
        }
        assertThat(updatedUser.getId()).isEqualTo(newUser.getId());
        assertThat(updatedUser.getDescription()).isEqualTo(description);
    }

    @Test
    public void updateUserVacationsTest() {
        User newUser = userService.create(defaultUser(1));
        newUser.setVacationsList(generateVacations(3, newUser));
        List<Vacation> vacations = userService.update(newUser.getId(), newUser).getVacationsList();
        repository.deleteById(newUser.getId());
        assertThat(vacations.size()).isGreaterThan(0);
    }

    @Test
    public void deleteUserVacationsTest() {
        User newUser = defaultUser(1);
        newUser.setVacationsList(generateVacations(5, newUser));
        User toDB = userService.create(newUser);
        toDB.setVacationsList(new ArrayList<>());
        List<Vacation> updatedVacations = userService.update(toDB.getId(), toDB).getVacationsList();
        repository.deleteById(toDB.getId());
        assertThat(updatedVacations.size()).isEqualTo(0);
    }

    @Test(expected = UserNotFoundException.class)
    public void updateByNonExistentIdTest() {
        userService.update(0L, defaultUser(1));
    }

    @Test
    public void deleteUserByIdLogicallyTest() {
        User newUser = userService.create(defaultUser(1));
        Long id = newUser.getId();
        try {
            userService.delete(id);
            assertThat(repository.existsById(id)).isTrue();
            Assert.assertThrows(UserNotFoundException.class, () -> userService.findById(id));
        } finally {
            repository.deleteById(id);
        }
    }


    @Test(expected = UserNotFoundException.class)
    public void deleteByNonExistentIdTest() {
        userService.delete(0L);
    }

}