package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domein.OvChipkaart;
import nl.hu.dp.ovchip.domein.Product;
import nl.hu.dp.ovchip.domein.Reiziger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOHibernate implements ProductDao {
    private final SessionFactory factory;


    public ProductDAOHibernate(SessionFactory factory) {
        this.factory = factory;
    }

    private Session generateSession() {
        return factory.openSession();
    }

    @Override
    public boolean save(Product product) {
        Session session = generateSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.save(product);
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
    public boolean update(Product product) {
        Session session = generateSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.update(product);
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
    public boolean delete(Product product) {
        Session session = generateSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.delete(product);
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
    public ArrayList<Product> findAll() {
        Session session = generateSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Query q = session.createQuery("from Product ");
            ArrayList<Product> tmp = (ArrayList<Product>) q.getResultList();
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
    public Product findById(int id) {
        Session session= generateSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Query q = session.createQuery("from Product where id=:i ");
            q.setParameter("i", id);
            Product tmo = (Product) q.getSingleResult();
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
