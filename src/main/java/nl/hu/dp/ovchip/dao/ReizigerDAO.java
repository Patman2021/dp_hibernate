package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domein.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface ReizigerDAO {

    boolean save(Reiziger reiziger) throws SQLException;

    boolean update(Reiziger reiziger) throws SQLException;

    boolean delete(Reiziger reiziger);

    Reiziger findById(int id);

    List<Reiziger> findByGbdatum(String datum);

    List<Reiziger> findAll() throws SQLException;

}
