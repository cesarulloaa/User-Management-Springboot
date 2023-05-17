package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtils;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtils jwtUtils;

    // Método para obtener un usuario por su ID
    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario1 = new Usuario();
        usuario1.setId(id);
        usuario1.setNombre("Cesar");
        usuario1.setApellido("Ulloa");
        usuario1.setEmail("cesarulloa@gmail.com");
        usuario1.setTelefono("8293344654");
        usuario1.setPassword("1234");
        return usuario1;
    }

    // Método para obtener la lista de todos los usuarios
    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorizazion")  String token){

        // Obtiene el id del usuario a partir del token
        String usuarioID = jwtUtils.getKey(token);

        // Si el token no es válido retorna una lista vacía
        if (usuarioID == null){
            return new ArrayList<>();
        }

        return usuarioDao.getUsuarios();
    }

    // Método para registrar un nuevo usuario
    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registarUsuario(@RequestBody Usuario usuario){

        // Cifra la contraseña del usuario antes de guardarla en la base de datos
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024,1, usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    }

    // Método para editar un usuario
    @RequestMapping(value = "usuario23")
    public Usuario editarUsuario(){
        Usuario usuario1 = new Usuario();
        usuario1.setNombre("Cesar");
        usuario1.setApellido("Ulloa");
        usuario1.setEmail("cesarulloa@gmail.com");
        usuario1.setTelefono("8293344654");
        usuario1.setPassword("1234");
        return usuario1;
    }

    // Método para eliminar un usuario por su ID
    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminarUsuario(@PathVariable Long id){
        usuarioDao.eliminar(id);
    }

    // Método para buscar un usuario
    @RequestMapping(value = "usuario43")
    public Usuario buscarUsuario(){
        Usuario usuario1 = new Usuario();
        usuario1.setNombre("Cesar");
        usuario1.setApellido("Ulloa");
        usuario1.setEmail("cesarulloa@gmail.com");
        usuario1.setTelefono("8293344654");
        usuario1.setPassword("1234");
        return usuario1;
    }
}
