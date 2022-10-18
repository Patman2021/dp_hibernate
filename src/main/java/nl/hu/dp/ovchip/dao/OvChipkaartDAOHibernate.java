package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domein.Adres;
import nl.hu.dp.ovchip.domein.OvChipkaart;
import nl.hu.dp.ovchip.domein.Reiziger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.ArrayList;

public class OvChipkaartDAOHibernate implements OvChipDao {
    private final SessionFactory factory;


    public OvChipkaartDAOHibernate(SessionFactory factory) {
        this.factory = factory;
    }

    private Session generateSession() {
        return factory.openSession();
    }

    @Override
    public boolean save(OvChipkaart ovChipkaart) {
        Session session = generateSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.save(ovChipkaart);
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
    public ArrayList<OvChipkaart> findByReiziger(Reiziger reiziger) {
        Session session = generateSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Query q = session.createQuery("from OvChipkaart where reiziger=:i ");
            q.setParameter("i", reiziger);
            ArrayList<OvChipkaart> tmo = (ArrayList<OvChipkaart>) q.getResultList();
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
    public boolean update(OvChipkaart ovChipkaart) {
        Session session = generateSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.update(ovChipkaart);
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
    public boolean delete(OvChipkaart ovChipkaart) {
        Session session = generateSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.delete(ovChipkaart);
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
    public OvChipkaart findByKaart_Nummer(int id) {
        Session session = generateSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Query q = session.createQuery("from OvChipkaart where id=:i ");
            q.setParameter("i", id);
            OvChipkaart tmo = (OvChipkaart) q.getSingleResult();
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
