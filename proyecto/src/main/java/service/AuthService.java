package service;

import service.custom_exceptions.CustomNotFoundException;
import data.dtos.UserDTO;
import data.entities.User;
import data.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;


import java.util.Optional;

@Service
@Transactional
public class AuthService
{
    @Autowired
    private UserRepository userRepository;


    // public boolean isInWhitelist(String email){
    //     User user = userRepository.findByEmail(email);
    //     if(user!=null && user.getStatus() == 1){
    //         return true;
    //     }
    //     return false;
    // }



    
    
}