package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domein.OvChipkaart;
import nl.hu.dp.ovchip.domein.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDao {
    boolean save(Product product);


    boolean update(Product product);

    boolean delete(Product product);


    ArrayList<Product> findAll();
}
