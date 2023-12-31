package com.example.eCommerce.v2.service;

import com.example.eCommerce.v2.Dto.LoginBody;
import com.example.eCommerce.v2.Dto.RegistrationAddress;
import com.example.eCommerce.v2.Dto.RegistrationBody;
import com.example.eCommerce.v2.exceptions.EmailFailureException;
import com.example.eCommerce.v2.exceptions.UserAlreadyExistsException;
import com.example.eCommerce.v2.exceptions.UserNotFoundException;
import com.example.eCommerce.v2.exceptions.UserNotVerifiedException;
import com.example.eCommerce.v2.model.Address;
import com.example.eCommerce.v2.model.LocalUser;
import com.example.eCommerce.v2.model.VerificationToken;
import com.example.eCommerce.v2.repository.LocalUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocalUserService {

    @Autowired
    LocalUserDao localUserDao;

    @Autowired
    EncryptionService encryptionService;

    @Autowired
    EmailService emailService;

    @Autowired
    JWTService jwtService;

    public LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException, EmailFailureException {

        if (localUserDao.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent() || localUserDao.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

    LocalUser newUser = new LocalUser();
        if (!registrationBody.getAddresses().isEmpty()){

            RegistrationAddress registrationAddress = registrationBody.getAddresses().get(0);

            Address newAddress = new Address();
            List<Address> newAddresses = new ArrayList<>();
            newAddress.setCountry(registrationAddress.getCountry());
            newAddress.setCity(registrationAddress.getCity());
            newAddress.setLocalUser(newUser);
            newAddresses.add(newAddress);
            newUser.setAddresses(newAddresses);

        }
    newUser.setFirstName(registrationBody.getFirstName());
    newUser.setLastName(registrationBody.getLastName());
    newUser.setEmail(registrationBody.getEmail());
    newUser.setPassword(encryptionService.EncryptPassword(registrationBody.getPassword()));
    newUser.setUsername(registrationBody.getUsername());

    VerificationToken verificationToken = createVerificationToken(newUser);
    emailService.verificationMessage(verificationToken);

    localUserDao.save(newUser);
    return newUser;
    }

    public VerificationToken createVerificationToken(LocalUser user) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(jwtService.createVerificationToken(user));
        verificationToken.setCreatedTimeStamp(new Timestamp(System.currentTimeMillis()));
        verificationToken.setLocalUser(user);
        user.getVerificationTokens().add(verificationToken);
        return verificationToken;
    }

    public Optional<LocalUser> findUser(Long id) throws UserNotFoundException {
        Optional<LocalUser> user = localUserDao.findById(id);
        if (user.isPresent()) {return user;}
        else {
            throw new UserNotFoundException();
        }
    }

    public List<LocalUser> listUsers() {
        return localUserDao.findAll();
    }

    public String login(LoginBody loginBody) throws EmailFailureException, UserNotVerifiedException {
        Optional<LocalUser> opuser = localUserDao.findByUsernameIgnoreCase(loginBody.getUsername());
        if (opuser.isPresent()) {
            LocalUser user = opuser.get();
            if (encryptionService.checkPassword(loginBody.getPassword(), user.getPassword())) {
                if (user.getEmailVerified()) {
                    return jwtService.CreateJWT(user);
                }
                else {
                    List<VerificationToken> tokens = user.getVerificationTokens();
                    boolean resend = user.getVerificationTokens().size() == 0 ||
                            tokens.get(0).getCreatedTimeStamp().before(new Timestamp(System.currentTimeMillis() - 60 * 60 * 1000));
                    if (resend) {
                        VerificationToken token = createVerificationToken(user);
                        user.getVerificationTokens().add(token);
                        emailService.verificationMessage(token);
                    }
                    throw new UserNotVerifiedException(resend);
                }
            }

        }
        else{
            return null;
        }
    return null;
    }


}
