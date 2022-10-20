package nl.hu.dp.ovchip.domein;

import javax.naming.Name;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "ov_chipkaart")
public class OvChipkaart {

    @Id
    @Column(name = "kaart_nummer")
    private int kaartNummer;
    @Temporal(TemporalType.DATE)
    @Column(name = "geldig_tot")
    private Date geligTot;
    @Column(name = "klasse")
    private int klasse;
    @Column(name = "saldo")
    private float saldo;

    @ManyToOne(cascade= CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "reiziger_id")
    private  Reiziger reiziger;




    @ManyToMany(mappedBy = "ovChipkaartenList",fetch = FetchType.EAGER)
    private List<Product> productenLijst = new ArrayList<>();



    public OvChipkaart( int kaartNummer, Date geligTot, int klasse, float saldo){
        this.kaartNummer= kaartNummer;
        this.geligTot= geligTot;
        this.klasse = klasse;
        this.saldo= saldo;
        this.productenLijst = new ArrayList<Product>();
    }

    public OvChipkaart() {

    }


    public Date getGeligTot() {
        return geligTot;
    }

    public float getSaldo() {
        return saldo;
    }

    public int getKaartNummer() {
        return kaartNummer;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setGeligTot(Date geligTot) {
        this.geligTot = geligTot;
    }

    public void setKaartNummer(int kaartNummer) {
        this.kaartNummer = kaartNummer;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }


    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public List<Product> getProductenLijst() {
        return productenLijst;
    }

    public void addProductToList(Product p){
        if(!productenLijst.contains(p)){
            productenLijst.add(p);
        }
        if(!p.getOvChipkaarten().contains(this)) {
            p.addOvkaart(this);
        }

    }

    public void addProductList(List<Product> p){
    this.productenLijst = p;

    }

    public void deleteProductFromList(Product p){
        if(productenLijst.contains(p)){
            productenLijst.remove(p);
        }
        if(p.getOvChipkaarten().contains(this)) {
            p.deleteOvkaart(this);
        }

    }

    @Override
    public String toString() {
        ArrayList product= new ArrayList<>();
        for (Product p: productenLijst){
            product.add(p.getProductNummer());
        }

        return "OvChipkaart{" +
                "kaartNummer=" + kaartNummer +
                ", Reiziger_id=" + reiziger.getId()+
                ", geligTot=" + geligTot +
                ", klasse=" + klasse +
                ", saldo=" + saldo +
                ", productenLijst=" + product +
                "} \n ";
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof  OvChipkaart){
            if (((OvChipkaart) obj).getKaartNummer() == this.getKaartNummer()) {
                return true;
        }
        }
        return false;}

}
