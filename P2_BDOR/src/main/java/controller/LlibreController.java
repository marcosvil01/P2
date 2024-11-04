package controller;

import model.Llibre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class LlibreController {

    private SessionFactory factory;

    public LlibreController() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Llibre.class)
                .buildSessionFactory();
    }

    // Método para agregar un libro
    public void agregarLlibre(String titol, String autor, String isbn) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Llibre llibre = new Llibre(titol, autor, isbn);
            session.save(llibre);
            transaction.commit();
            System.out.println("Llibre agregat: " + llibre);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Método para listar todos los libros con un filtro de ISBN
    public void listarLlibresAmbIsbn(String prefixIsbn) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            List<Llibre> llibres = session.createQuery("from Llibre where isbn like :prefix", Llibre.class)
                    .setParameter("prefix", prefixIsbn + "%")
                    .getResultList();
            for (Llibre llibre : llibres) {
                System.out.println("Títol: " + llibre.getTitol() + ", Autor: " + llibre.getAutor());
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void cerrar() {
        factory.close();
    }
}
