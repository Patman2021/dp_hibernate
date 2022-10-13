package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domein.Reiziger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAO {
    private  final  Session session;

    public ReizigerDAOHibernate(Session session){
        this.session = session;
    }

    @Override
    public boolean save(Reiziger reiziger) throws SQLException {
        Transaction tx= session.getTransaction();
        try {
            tx.begin();
            session.save(reiziger);
            session.flush();
            tx.commit();
            return  true;
        }catch (RuntimeException e){
            tx.rollback();
            throw e;

        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(Reiziger reiziger) throws SQLException {
        Transaction tx= session.getTransaction();
        try {
            tx.begin();
            session.update(reiziger);
            session.flush();
            tx.commit();
            return  true;
        }catch (RuntimeException e){
            tx.rollback();
            throw e;

        }finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        Transaction tx= session.getTransaction();
        try {
            tx.begin();
            session.delete(reiziger);
            session.flush();
            tx.commit();
            return  true;
        }catch (RuntimeException e){
            tx.rollback();
            throw e;

        }finally {
            session.close();
        }
    }

    @Override
    public Reiziger findById(int id) {
        Transaction tx= session.getTransaction();
        try {
            tx.begin();
            Query q=session.createQuery("from Reiziger where id=:i ");
            q.setParameter("i",id);
            Reiziger tmo = (Reiziger) q.getSingleResult();
            tx.commit();
            return  tmo;
        }catch (RuntimeException e){
            tx.rollback();
            throw e;

        }finally {
            session.close();
        }
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        return null;
    }

    @Override
    public List<Reiziger> findAll() throws SQLException {
        Transaction tx= session.getTransaction();
        try {
            tx.begin();
            Query q=session.createQuery("from Reiziger ");
            List<Reiziger> tmo = (List<Reiziger>) q.getResultList();
            tx.commit();
            System.out.println(tmo);
            return  tmo;
        }catch (RuntimeException e){
            tx.rollback();
            throw e;

        }finally {
            session.close();
        }
    }
}
