package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Indica que esta clase es un controlador REST
public class AuthController {
    @Autowired // Inyecta una instancia de UsuarioDao
    private UsuarioDao usuarioDao;
    @Autowired // Inyecta una instancia de JWTUtils
    private JWTUtils jwtUtils;

    @RequestMapping(value = "api/login", method = RequestMethod.POST) // Mapea la ruta "/api/login" al método login y el método HTTP POST
    public String login(@RequestBody Usuario usuario){ // Recibe un objeto Usuario en formato JSON en el cuerpo de la solicitud HTTP

        // Llama al método obtenerUsuarioPorCredenciales del DAO para buscar un usuario con las credenciales proporcionadas
        Usuario usuarioLoggeado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);

        // Si se encuentra un usuario con las credenciales proporcionadas
        if(usuarioLoggeado != null){

            // Crea un token JWT con el id y el email del usuario
            String token = jwtUtils.create(String.valueOf(usuarioLoggeado.getId()), usuarioLoggeado.getEmail());

            // Retorna el token
            return token;
        }else {
            // Si no se encuentra un usuario con las credenciales proporcionadas, retorna "FAIL"
            return "FAIL";
        }
    }
}

