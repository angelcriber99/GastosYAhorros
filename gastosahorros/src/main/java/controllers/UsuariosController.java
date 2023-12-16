package controllers;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import models.Usuarios;

public class UsuariosController {

    private SessionFactory sf;

    public UsuariosController() {
        sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Usuarios.class)
                .buildSessionFactory();
    }

    public String crearUsuario(String nombre_usuario, String contraseña) {
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();
            Usuarios usuario = new Usuarios(nombre_usuario, contraseña);
            session.save(usuario);
            transaction.commit();
            return "Usuario creado";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al registrar el usuario";
        }
    }

    public String borrarUsuario(int id_usuario) {
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();
            Usuarios usuario = session.get(Usuarios.class, id_usuario);
            if (usuario != null) {
                session.delete(usuario);
                transaction.commit();
                return "Usuario eliminado";
            } else {
                return "Usuario no encontrado";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al eliminar el usuario";
        }
    }

    public String editarUsuario(int id_usuario, String nuevoNombre, String nuevaContraseña) {
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();
            Usuarios usuario = session.get(Usuarios.class, id_usuario);
            if (usuario != null) {
                usuario.setNombre(nuevoNombre);
                usuario.setContraseña(nuevaContraseña);
                session.update(usuario);
                transaction.commit();
                return "Usuario actualizado";
            } else {
                return "Usuario no encontrado";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al actualizar el usuario";
        }
    }

    public Usuarios obtenerUsuario(int id_usuario) {
        try (Session session = sf.openSession()) {
            return session.get(Usuarios.class, id_usuario);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Otros métodos CRUD que puedes añadir según tus necesidades

    // Método para obtener todos los usuarios
    public List<Usuarios> obtenerTodosUsuarios() {
        try (Session session = sf.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Usuarios> query = builder.createQuery(Usuarios.class);
            query.from(Usuarios.class);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // Método para buscar usuarios por nombre
    public List<Usuarios> buscarUsuariosPorNombre(String nombre) {
        try (Session session = sf.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Usuarios> query = builder.createQuery(Usuarios.class);
            Root<Usuarios> root = query.from(Usuarios.class);
            query.where(builder.like(root.get("nombre_usuario"), "%" + nombre + "%"));
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public boolean iniciarSesion(String nombreUsuario, String contraseña) {
        try (Session session = sf.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Usuarios> query = builder.createQuery(Usuarios.class);
            Root<Usuarios> root = query.from(Usuarios.class);

            query.where(
                    builder.equal(root.get("nombre_usuario"), nombreUsuario),
                    builder.equal(root.get("contraseña"), contraseña)
            );

            Usuarios usuario = session.createQuery(query).uniqueResult();
            return usuario != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
