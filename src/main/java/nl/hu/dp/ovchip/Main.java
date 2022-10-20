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

        testReizigerDAO(reizigerDAO);
        testAdresDAO(reizigerDAO, adresDAO);
        testOvChipKaartDao(reizigerDAO, ovChipDao);
        testproductDao(productDao, reizigerDAO);

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


    private static void testReizigerDAO(ReizigerDAO reizigerDAO) throws SQLException, ParseException {
        System.out.println("\n----------testReizigerDAO----------");

        Reiziger reiziger1 = new Reiziger(12345, "T", "ES", "Ten", new Date());

        System.out.println("--findAll rezigers:");
        System.out.println(reizigerDAO.findAll());

        System.out.println("\n---save reiziger:");
        System.out.println("Voor de save: " + reizigerDAO.findAll().size());
        reizigerDAO.save(reiziger1);
        System.out.println("na save: " + reizigerDAO.findAll().size());


        System.out.println("\n--find reiziger by geboortedatum:");
        System.out.println(reizigerDAO.findByGbdatum("1998-09-01"));

        System.out.println("\n--find reiziger by id:");
        System.out.println(reizigerDAO.findById(12345));


        System.out.println("\n--updaten reiziger:");
        System.out.println("reiziger: " + reiziger1);
        reiziger1.setAchternaam("kees");
        reizigerDAO.update(reiziger1);
        System.out.println(" na update:reiziger: " + reizigerDAO.findById(reiziger1.getId()));

        int beginLengte = reizigerDAO.findAll().size();
        System.out.println("\n--delete reiziger: " + beginLengte);
        reizigerDAO.delete(reiziger1);
        int eindeLengte = reizigerDAO.findAll().size();
        System.out.println("na verwijderen: " + eindeLengte);
    }

    private static void testAdresDAO(ReizigerDAO reizigerDAO, AdresDAO adresDAO) throws SQLException {
        System.out.println("\n----------testAdresDAO----------");

        Reiziger reiziger1 = new Reiziger(99, "T", "ES", "Ten", new Date());
        Adres adres1 = new Adres(12345, "2312xx", "187A", "Oude kerkLaan", "Boxtel");
        adres1.setReiziger(reiziger1);

        System.out.println("\nsave adres:");
        reizigerDAO.save(reiziger1);
        System.out.println("Voor de save: " + adresDAO.findAll().size());
        adresDAO.save(adres1);
        System.out.println("Na de save: " + adresDAO.findAll().size());

        System.out.println("\n--find adres by id!");
        System.out.println(adresDAO.findById(12345));

        System.out.println("\n--find adres by reiziger!");
        System.out.println(adresDAO.findByReiziger(reiziger1));

        System.out.println(adresDAO.findAll());
        System.out.println("\n--updaten adress:");
        System.out.println("adres: " + adres1);
        adres1.setHuisnummer("7647");
        adresDAO.update(adres1);
        System.out.println("adres: " + adresDAO.findById(12345));


        System.out.println("\n--delete adres ");
        System.out.println("Aantal adressen voor het verwijderen: " + adresDAO.findAll().size());
        adresDAO.delete(adres1);
        System.out.println("Na  het verwijderen: " + adresDAO.findAll().size());
    }


    private static void testOvChipKaartDao(ReizigerDAO reizigerDAO, OvChipDao ovChipDao) throws SQLException {
        System.out.println("\n----------testOvChipKaartDAO----------");

        Reiziger reiziger1 = new Reiziger(99, "T", "ES", "Ten", new Date());
        OvChipkaart ovChipkaart1 = new OvChipkaart(123456, new Date(2019 - 01 - 31), 2, 20000.00F);
        ovChipkaart1.setReiziger(reiziger1);

        System.out.println("\nsave ov-kaart:");
        reizigerDAO.save(reiziger1);
        if (ovChipDao.save(ovChipkaart1)) {
            System.out.println("opslaan ovChipkaart gelukt!");
        } else {
            System.out.println("oeps er ging iets fout!");
        }

        System.out.println("\n--find ovkaart by kaartnummer!");
        System.out.println(ovChipDao.findByKaart_Nummer(ovChipkaart1.getKaartNummer()));

        System.out.println("\n--find ovChipKaart by reiziger!");
        System.out.println(ovChipDao.findByReiziger(reiziger1));

        System.out.println("\n--updaten ov-chipkaart");
        System.out.println("ov Chipkaart: " + ovChipkaart1);
        ovChipkaart1.setSaldo(1000f);
        ovChipDao.update(ovChipkaart1);
        System.out.println("adres: " + ovChipDao.findByKaart_Nummer(ovChipkaart1.getKaartNummer()));


        System.out.println("\n--delete adres ov-chipkaart ");
        if (ovChipDao.delete(ovChipkaart1)) {
            System.out.println("verwijderen is gelukt");
        } else {
            System.out.println("oeps er ging iets fout");
        }
    }

    private static void testproductDao(ProductDao productDao, ReizigerDAO reizigerDAO) throws SQLException {
        System.out.println("\n----------testProductDao----------");


        OvChipkaart ovChipkaart1 = new OvChipkaart(1234567, new Date(2019 - 01 - 31), 2, 20000.00F);
        Reiziger reiziger1 = new Reiziger(999, "T", "ES", "Ten", new Date());
        reiziger1.addOvChipKaart(ovChipkaart1);
        Product product1 = new Product(998, "test", "testen is leuk", 10F);
        product1.addOvkaart(ovChipkaart1);


        System.out.println("--findAll producten:");
        System.out.println(productDao.findAll());

        System.out.println("\nsave Product:");
        reizigerDAO.save(reiziger1);
        System.out.println("Voor de save: " + productDao.findAll().size());
        productDao.save(product1);
        System.out.println("Na de save: " + productDao.findAll().size());

        System.out.println("\n--findby  product id");
        productDao.update(product1);
        System.out.println( productDao.findById(product1.getProductNummer()));


        System.out.println("\n--updaten product");
        System.out.println("product: " + productDao.findById(product1.getProductNummer()));
        product1.setBeschrijving("aanpassing");
        productDao.update(product1);
        System.out.println("product: " +  productDao.findById(product1.getProductNummer()));


        System.out.println("\n--delete product ");
        if (productDao.delete(product1)) {
            reizigerDAO.delete(reiziger1);
            System.out.println("verwijderen is gelukt");
        } else {
            System.out.println("oeps er ging iets fout");
        }
    }





}