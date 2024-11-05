package controller;

import model.Comanda;
import model.Usuari;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UsuariController {

    private SessionFactory factory;

    public UsuariController() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Usuari.class)
                .addAnnotatedClass(Comanda.class)
                .buildSessionFactory();
    }

    // Mètode per afegir un nou usuari a la base de dades
    public void afegirUsuari(String nom, String correu) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Usuari nouUsuari = new Usuari(nom, correu);
            session.save(nouUsuari);
            transaction.commit();
            System.out.println("Usuari afegit: " + nouUsuari);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Mètode per afegir una nova comanda per a un usuari específic
    public void afegirComanda(int usuariId, String producte, double preu, String detall) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Usuari usuari = session.get(Usuari.class, usuariId);
            if (usuari != null) {
                Comanda novaComanda = new Comanda(producte, preu, detall, usuari);
                session.save(novaComanda);
                transaction.commit();
                System.out.println("Comanda afegida: " + novaComanda);
            } else {
                System.out.println("Usuari no trobat.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Mètode per consultar totes les comandes d'un usuari
    public void consultarComandes(int usuariId) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Usuari usuari = session.get(Usuari.class, usuariId);
            if (usuari != null) {
                List<Comanda> comandas = session.createQuery("from Comanda where usuari.id = :usuariId", Comanda.class)
                        .setParameter("usuariId", usuariId)
                        .getResultList();
                if (comandas.isEmpty()) {
                    System.out.println("L'usuari no té comandes.");
                } else {
                    System.out.println("Comandes de l'usuari " + usuari.getNom() + ":");
                    for (Comanda comanda : comandas) {
                        System.out.println(comanda);
                    }
                }
            } else {
                System.out.println("Usuari no trobat.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Mètode per modificar el correu electrònic d'un usuari
    public void modificarCorreu(int usuariId, String nouCorreu) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Usuari usuari = session.get(Usuari.class, usuariId);
            if (usuari != null) {
                usuari.setCorreu(nouCorreu);
                transaction.commit();
                System.out.println("Correu modificat per: " + nouCorreu);
            } else {
                System.out.println("Usuari no trobat.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Mètode per eliminar una comanda per ID
    public void eliminarComanda(int comandaId) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Comanda comanda = session.get(Comanda.class, comandaId);
            if (comanda != null) {
                session.delete(comanda);
                transaction.commit();
                System.out.println("Comanda eliminada: " + comandaId);
            } else {
                System.out.println("Comanda no trobada.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Mètode per llistar tots els usuaris de la base de dades
    public void listarUsuaris() {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List<Usuari> usuaris = session.createQuery("from Usuari", Usuari.class).getResultList();
            for (Usuari usuari : usuaris) {
                System.out.println(usuari);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Mètode per tancar la fàbrica de sessions
    public void tancar() {
        factory.close();
    }
}
