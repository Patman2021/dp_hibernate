package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domein.Reiziger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAO {
    private final SessionFactory factory;


    public ReizigerDAOHibernate(SessionFactory factory) {
        this.factory = factory;
    }

    private Session generateSession() {
        return factory.openSession();
    }

    @Override
    public boolean save(Reiziger reiziger) throws SQLException {
        Session session= generateSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.save(reiziger);
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
    public boolean update(Reiziger reiziger) throws SQLException {
        Session session= generateSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.update(reiziger);
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
    public boolean delete(Reiziger reiziger) {
        Session session= generateSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.delete(reiziger);
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
    public Reiziger findById(int id) {
        Session session= generateSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Query q = session.createQuery("from Reiziger where id=:i ");
            q.setParameter("i", id);
            Reiziger tmo = (Reiziger) q.getSingleResult();
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
    public List<Reiziger> findByGbdatum(String datum) throws ParseException {
        Session session= generateSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Query q = session.createQuery("from Reiziger where geboortedatum=:i ");
            q.setParameter("i", Date.valueOf(datum));
            List<Reiziger> tmp = q.getResultList();
            tx.commit();
            return tmp;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Reiziger> findAll() throws SQLException {
        Session session= generateSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Query q = session.createQuery("from Reiziger  ");
            List<Reiziger> tmo = (List<Reiziger>) q.getResultList();
            tx.commit();
            return tmo;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;

        } finally {
            session.close();
        }
    }
}
