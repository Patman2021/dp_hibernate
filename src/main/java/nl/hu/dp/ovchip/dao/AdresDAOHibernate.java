package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domein.Adres;
import nl.hu.dp.ovchip.domein.Reiziger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

public class AdresDAOHibernate implements AdresDAO {

    private final SessionFactory factory;

    public AdresDAOHibernate(SessionFactory factory) {
        this.factory = factory;
    }


    private Session generateSession() {
        return factory.openSession();
    }

    @Override
    public boolean save(Adres adres) {
        Session session = generateSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.save(adres);
            session.flush();
            tx.commit();
            return true;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        Session session = generateSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Query q = session.createQuery("from Adres where reiziger=:i ");
            q.setParameter("i", reiziger);
            Adres tmo = (Adres) q.getSingleResult();
            tx.commit();
            return tmo;
        } catch (RuntimeException e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Adres adres) {
        Session session = generateSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.update(adres);
            session.flush();
            tx.commit();
            return true;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Adres adres) {
        Session session = generateSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.delete(adres);
            session.flush();
            tx.commit();
            return true;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Adres findById(int id) {
        System.out.println("id " + id);
        Session session = generateSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Query q = session.createQuery("from Adres where id=:i ");
            q.setParameter("i", id);
            Adres tmo = (Adres) q.getSingleResult();
            tx.commit();
            return tmo;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
    @Override
    public List<Adres> findAll() {
        Session session= generateSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Query q = session.createQuery("from Adres  ");
            List<Adres> tmo = (List<Adres>) q.getResultList();
            return tmo;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;

        } finally {
            session.close();
        }
    }
}
