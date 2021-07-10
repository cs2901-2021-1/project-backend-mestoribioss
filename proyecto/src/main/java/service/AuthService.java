package service;

import service.custom_exceptions.CustomNotFoundException;
import data.dtos.UserDTO;
import data.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AuthService
{
    @Autowired
    private WhiteListService whiteListService;

    public void login(){

    }
}