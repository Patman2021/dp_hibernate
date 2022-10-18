package nl.hu.dp.ovchip;

import nl.hu.dp.ovchip.dao.*;
import nl.hu.dp.ovchip.domein.Adres;
import nl.hu.dp.ovchip.domein.OvChipkaart;
import nl.hu.dp.ovchip.domein.Product;
import nl.hu.dp.ovchip.domein.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Testklasse - deze klasse test alle andere klassen in deze package.
 * <p>
 * System.out.println() is alleen in deze klasse toegestaan (behalve voor exceptions).
 *
 * @author tijmen.muller@hu.nl
 */
public class Main {
    // Creëer een factory voor Hibernate sessions.
    private static final SessionFactory factory;

    static {
        try {
            // Create a Hibernate session factory
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Retouneer een Hibernate session.
     *
     * @return Hibernate session
     * @throws HibernateException
     */


    public static void main(String[] args) throws SQLException, ParseException {
        //testFetchAll();
        ReizigerDAO reizigerDAO = new ReizigerDAOHibernate(factory);
        AdresDAO adresDAO = new AdresDAOHibernate(factory);
        OvChipDao ovChipDao = new OvChipkaartDAOHibernate(factory);
        ProductDao productDao = new ProductDAOHibernate(factory);
        testDAOHibernate(adresDAO, reizigerDAO, ovChipDao, productDao);

    }

    /**
     * P6. Haal alle (geannoteerde) entiteiten uit de database.
     */
    private static void testFetchAll() {

        Session session = factory.openSession();
        try {
            Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                Query query = session.createQuery("from " + entityType.getName());

                System.out.println("[Test] Alle objecten van type " + entityType.getName() + " uit database:");
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
                System.out.println();
            }
        } finally {
            session.close();
        }
    }

    private static void testDAOHibernate(AdresDAO adresDAO, ReizigerDAO reizigerDAO, OvChipDao ovChipDao, ProductDao productDao) throws SQLException, ParseException {
        Reiziger r1 = new Reiziger(12345, "T", "ES", "Ten", new Date());
        Adres adres1 = new Adres(12345, "2312xx", "187A", "Oude kerkLaan", "Boxtel", 12345);
        OvChipkaart ovChipkaart1 = new OvChipkaart(123456, new Date(2019 - 01 - 31), 2, 20000.00F);
        Product p1 = new Product(98, "test", "testen is leuk", 10F);
        // reiziger linken aan een adres en ov_chipkaart;
        r1.setAdres(adres1);
        ovChipkaart1.addProductToList(p1);
        r1.addOvChipKaart(ovChipkaart1);



        System.out.println("\n--saven van elke class:\n");
        if (reizigerDAO.save(r1)) {
            System.out.println("reiziger  met id:" + r1.getId() + "save gelukt!");
            System.out.println("Ov chipkaart met kaartnummer: " + ovChipkaart1.getKaartNummer() + "save  gelukt!");
        } else {
            System.out.println("oeps er ging iets fout");
        }
        if (adresDAO.save(adres1)) {
            System.out.println("adres met id: " + adres1.getId() + "save gelukt!");
        } else {
            System.out.println("oeps er ging iets fout");
        }
        if (productDao.save(p1)) {
            System.out.println("--product  met productnummer:" + p1.getProductNummer() + "save gelukt!");
        } else {
            System.out.println("oeps er ging iets fout");
        }

        System.out.println("\n--find reiziger by id!");
        System.out.println(reizigerDAO.findById(12345));
        System.out.println("\n--find reiziger by geboortedatum!");
        System.out.println(reizigerDAO.findByGbdatum("2022-10-17"));
        System.out.println("\n--findall reiziger ");
        System.out.println(reizigerDAO.findAll());
        System.out.println("\n--find adres by id!");
        System.out.println(adresDAO.findById(12345));
        System.out.println("\n--find adres by reiziger!");
        System.out.println(adresDAO.findByReiziger(r1));
        System.out.println("\n--find ov_chipkaart by reiziger!");
        System.out.println(ovChipDao.findByReiziger(r1));
        System.out.println("\n--find ov_chipkaart by kaartnummer!");
        System.out.println(ovChipDao.findByKaart_Nummer(123456));
        System.out.println("\n--findall producten");
        System.out.println(productDao.findAll());


        System.out.println("\n--updaten van elke class:\n");
        r1.setAchternaam("kees");
        System.out.println("\nUpdate reiziger");
        if (reizigerDAO.update(r1)) {
            System.out.println("reiziger  met id :" + r1.getId() + " Update gelukt!");
        } else System.out.println("oeps er ging iets mis");

        System.out.println("\nUpdate ovChipkaart");
        ovChipkaart1.setKlasse(1);
        if (ovChipDao.update(ovChipkaart1)) {
            System.out.println("--ov_chipkaart met kaartnummer: " + ovChipkaart1.getKaartNummer() + " Update gelukt!");
        } else System.out.println("oeps er ging iets mis");

        System.out.println("\nUpdate product");
        p1.setBeschrijving("assd");
        if (productDao.update(p1)) {
            System.out.println("product met productnummer: " + p1.getProductNummer() + " Update gelukt!");
        } else System.out.println("oeps er ging iets mis");

        System.out.println("\nUpdate adress");
        adres1.setHuisnummer("1");
        if (adresDAO.update(adres1)) {
            System.out.println("adres met id: " + adres1.getId() + " Update gelukt!");
        } else System.out.println("oeps er ging iets mis");

        System.out.println("\n--deleten van elke class:\n");
        if (adresDAO.delete(adres1)) {
            System.out.println("Adres met id: "+ adres1.getId()+" delete werkt");
        }else {
            System.out.println("oeps er ging iets fout");
        }
        if (productDao.delete(p1)) {
            System.out.println("\nproduct met nummer: " + p1.getProductNummer() + " delete werkt");
        }else {
            System.out.println("oeps er ging iets fout");
        }
        if (ovChipDao.delete(ovChipkaart1)) {
            System.out.println("\nov_chipkaart met kaartnummer:"+ ovChipkaart1.getKaartNummer()+" delete werkt");
        }else {
            System.out.println("oeps er ging iets fout");
        }
        if (reizigerDAO.delete(r1)) {
            System.out.println("\nreiziger met id: "+ r1.getId()+" delete werkt");
        }else {
            System.out.println("oeps er ging iets fout");
        }


    }


}