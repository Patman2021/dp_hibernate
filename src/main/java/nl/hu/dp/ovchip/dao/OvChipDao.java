package nl.hu.dp.ovchip.dao;


import nl.hu.dp.ovchip.domein.OvChipkaart;
import nl.hu.dp.ovchip.domein.Product;
import nl.hu.dp.ovchip.domein.Reiziger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OvChipDao {

    boolean save(OvChipkaart ovChipkaart);

    ArrayList<OvChipkaart>  findByReiziger(Reiziger reiziger);

    boolean update(OvChipkaart ovChipkaart);

    boolean delete(OvChipkaart ovChipkaart);

    OvChipkaart findByKaart_Nummer(int id);





}
