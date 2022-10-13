package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domein.Adres;
import nl.hu.dp.ovchip.domein.Reiziger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.SQLException;

public class AdresDAOHibernate  implements  AdresDAO{

     private  final Session session;

    public AdresDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Adres adres) throws SQLException {
        Transaction tx= session.getTransaction();
        try {
            tx.begin();
            session.save(adres);
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
    public Adres findByReiziger(Reiziger reiziger) {
        Transaction tx= session.getTransaction();
        try {
            tx.begin();
            Query q=session.createQuery("from Adres where reizigerId=:i ");
            q.setParameter("i",reiziger.getId());
            Adres tmo = (Adres) q.getSingleResult();
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
    public boolean update(Adres adres) {
        Transaction tx= session.getTransaction();
        try {
            tx.begin();
            session.delete(adres);
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
    public boolean delete(Adres adres) {
        Transaction tx= session.getTransaction();
        try {
            tx.begin();
            session.delete(adres);
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
    public Adres findById(int id) {
        Transaction tx= session.getTransaction();
        try {
            tx.begin();
            Query q=session.createQuery("from Adres where id=:i ");
            q.setParameter("i",id);
            Adres tmo = (Adres) q.getSingleResult();
            tx.commit();
            return  tmo;
        }catch (RuntimeException e){
            tx.rollback();
            throw e;

        }finally {
            session.close();
        }
    }
}
