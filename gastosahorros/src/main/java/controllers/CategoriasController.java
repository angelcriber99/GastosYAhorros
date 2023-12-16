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
import models.Categorias;

public class CategoriasController {

    private SessionFactory sf;
    
    public CategoriasController() {
        sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Categorias.class)
                .buildSessionFactory();
    }
    
    public String crearCategoria(String nombre_categoria, String descripcion_categoria) {
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();
            Categorias categoria = new Categorias();
            categoria.setNombre_categoria(nombre_categoria);
            categoria.setDescripcion_categoria(descripcion_categoria);
            session.save(categoria);
            transaction.commit();
            return "Categoría creada";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al registrar la categoría";
        }
    }

    public String borrarCategoria(int id_categoria) {
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();
            Categorias categoria = session.get(Categorias.class, id_categoria);
            if (categoria != null) {
                session.delete(categoria);
                transaction.commit();
                return "Categoría eliminada";
            } else {
                return "Categoría no encontrada";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al eliminar la categoría";
        }
    }

    public String editarCategoria(int id_categoria, String nuevoNombre, String nuevaDescripcion) {
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();
            Categorias categoria = session.get(Categorias.class, id_categoria);
            if (categoria != null) {
                categoria.setNombre_categoria(nuevoNombre);
                categoria.setDescripcion_categoria(nuevaDescripcion);
                session.update(categoria);
                transaction.commit();
                return "Categoría actualizada";
            } else {
                return "Categoría no encontrada";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al actualizar la categoría";
        }
    }

    public Categorias obtenerCategoria(int id_categoria) {
        try (Session session = sf.openSession()) {
            return session.get(Categorias.class, id_categoria);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Categorias> obtenerTodasCategorias() {
        try (Session session = sf.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Categorias> query = builder.createQuery(Categorias.class);
            query.from(Categorias.class);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Categorias> buscarCategoriasPorNombre(String nombre) {
        try (Session session = sf.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Categorias> query = builder.createQuery(Categorias.class);
            Root<Categorias> root = query.from(Categorias.class);
            query.where(builder.like(root.get("nombre_categoria"), "%" + nombre + "%"));
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    
}
