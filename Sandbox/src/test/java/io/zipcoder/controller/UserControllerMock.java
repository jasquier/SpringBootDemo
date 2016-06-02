package io.zipcoder.controller;


import io.zipcoder.domain.User;
import io.zipcoder.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.google.common.collect.Lists;


public class UserControllerMock {

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUsers(){
        UserController userController = new UserController();
        ReflectionTestUtils.setField(userController, "userRepository", userRepository);

        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<Iterable<User>> allUsers = userController.getAllUsers();
        verify(userRepository, times(1)).findAll();

        assertEquals(HttpStatus.OK, allUsers.getStatusCode());
        assertEquals(0, Lists.newArrayList(allUsers.getBody()).size());
    }
}
