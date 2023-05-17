package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository // Anotación que indica que la clase es un componente de acceso a datos
@Transactional // Anotación que indica que se manejan transacciones en la clase
public class UsuarioDaoImp implements UsuarioDao{

    @PersistenceContext // Anotación que inyecta un objeto EntityManager
    private EntityManager entityManager;

    @Override
    public List<Usuario> getUsuarios() {
        String query= "FROM Usuario";
        return entityManager.createQuery(query).getResultList();
        // Obtiene una lista de todos los usuarios a través de una consulta JPQL
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
        // Elimina el usuario especificado mediante el método EntityManager.remove()
    }

    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);
        // Registra un nuevo usuario o actualiza uno existente mediante el método EntityManager.merge()
    }

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        String query= "FROM Usuario WHERE email = :email";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();
        // Obtiene una lista de usuarios que tengan el correo electrónico especificado mediante una consulta JPQL

        if(lista.isEmpty()){
            return null;
        }

        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, usuario.getPassword())){
            return lista.get(0);
            // Verifica si la contraseña especificada por el usuario coincide con la contraseña almacenada en la base de datos mediante la función verify() de la biblioteca Argon2
        }
        return null;
    }
}
