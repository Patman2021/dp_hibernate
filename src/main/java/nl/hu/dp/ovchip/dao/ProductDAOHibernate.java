package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domein.OvChipkaart;
import nl.hu.dp.ovchip.domein.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOHibernate implements ProductDao {

    @Override
    public boolean save(Product product) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<Product> findByOvkaart(OvChipkaart ovChipkaart) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Product product) {
        return false;
    }

    @Override
    public ArrayList<Product> findAll() throws SQLException {
        return null;
    }
}
