package com.example.eCommerce.v2.service;

import com.example.eCommerce.v2.Dto.RegistrationAddress;
import com.example.eCommerce.v2.Dto.RegistrationBody;
import com.example.eCommerce.v2.exceptions.UserAlreadyExistsException;
import com.example.eCommerce.v2.exceptions.UserNotFoundException;
import com.example.eCommerce.v2.model.Address;
import com.example.eCommerce.v2.model.LocalUser;
import com.example.eCommerce.v2.repository.AddressDao;
import com.example.eCommerce.v2.repository.LocalUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocalUserService {

    @Autowired
    LocalUserDao localUserDao;

    @Autowired
    AddressDao addressDao;

    public LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException{

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
    newUser.setPassword(registrationBody.getPassword());
    newUser.setUsername(registrationBody.getUsername());

    localUserDao.save(newUser);
    return newUser;
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


}
