package controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import models.Gastos;
import models.Usuarios;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GastosController {

    private SessionFactory sf;

    public GastosController() {
        sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Gastos.class)
                .addAnnotatedClass(Usuarios.class)
                .buildSessionFactory();
    }

    public String crearGasto(Date fecha, double importe, int idCategoriaTransaccion, Usuarios usuario) {
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();
            Gastos gasto = new Gastos(fecha, BigDecimal.valueOf(importe), idCategoriaTransaccion, usuario);
            session.save(gasto);
            transaction.commit();
            return "Gasto creado";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al crear el gasto";
        }
    }

    public String borrarGasto(int idGasto) {
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();
            Gastos gasto = session.get(Gastos.class, idGasto);
            if (gasto != null) {
                session.delete(gasto);
                transaction.commit();
                return "Gasto eliminado";
            } else {
                return "Gasto no encontrado";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al eliminar el gasto";
        }
    }

    public String editarGasto(int idGasto, Date nuevaFecha, double nuevoImporte, int nuevoIdCategoriaTransaccion, Usuarios nuevoUsuario) {
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();
            Gastos gasto = session.get(Gastos.class, idGasto);
            if (gasto != null) {
                gasto.setFecha(nuevaFecha);
                gasto.setImporte(BigDecimal.valueOf(nuevoImporte));
                gasto.setId_categoria_transaccion(nuevoIdCategoriaTransaccion);
                gasto.setUsuario(nuevoUsuario);
                session.update(gasto);
                transaction.commit();
                return "Gasto actualizado";
            } else {
                return "Gasto no encontrado";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al actualizar el gasto";
        }
    }

    public Gastos obtenerGasto(int idGasto) {
        try (Session session = sf.openSession()) {
            return session.get(Gastos.class, idGasto);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Gastos> obtenerTodosGastos() {
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM Gastos", Gastos.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
