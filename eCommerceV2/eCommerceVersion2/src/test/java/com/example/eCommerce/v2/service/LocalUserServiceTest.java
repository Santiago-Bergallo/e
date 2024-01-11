package com.example.eCommerce.v2.service;

import com.example.eCommerce.v2.Dto.RegistrationBody;
import com.example.eCommerce.v2.exceptions.UserAlreadyExistsException;
import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetupTest;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LocalUserServiceTest {

    @RegisterExtension
    private static GreenMailExtension greenMailExtension = new GreenMailExtension(ServerSetupTest.SMTP)
            .withConfiguration(GreenMailConfiguration.aConfig().withUser("springboot", "secret"))
            .withPerMethodLifecycle(true);

    @Autowired
    private LocalUserService userService;



    @Test
    @Transactional
    public void testRegisterUser() throws MessagingException {
        RegistrationBody registrationBody = new RegistrationBody();
        registrationBody.setUsername("UserA");
        registrationBody.setEmail("userServiceTest$testRegisterUser@junit.com");
        registrationBody.setFirstName("FirstName");
        registrationBody.setLastName("LasName");
        registrationBody.setPassword("MySecurePassword123");
        Assertions.assertThrows(UserAlreadyExistsException.class,
                ()-> userService.registerUser(registrationBody), "Username should already be in use.");

        registrationBody.setUsername("UserServiceTest$testRegisterUser");
        registrationBody.setEmail("userA@junit.com");
        Assertions.assertThrows(UserAlreadyExistsException.class,
                ()-> userService.registerUser(registrationBody), "Email should already be in use.");
        registrationBody.setEmail("userServiceTest$testRegisterUser@junit.com");
        Assertions.assertDoesNotThrow(()-> userService.registerUser(registrationBody), "User should register successfully.");
        Assertions.assertEquals(registrationBody.getEmail(),
                greenMailExtension.getReceivedMessages()[0].getRecipients(Message.RecipientType.TO)[0].toString());
    }




}
