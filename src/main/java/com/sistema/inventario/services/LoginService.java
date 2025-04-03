package com.sistema.inventario.services;

import com.sistema.inventario.entities.Login;
import com.sistema.inventario.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository repo;

    public Login login(String nombre, String contrasena) {
        Login user = repo.findByNombreAndContrasena(nombre, contrasena);
        return user;
    }

}