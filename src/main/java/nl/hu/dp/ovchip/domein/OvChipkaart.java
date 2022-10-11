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

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "reiziger_id")
    private  Reiziger reiziger;

    @Transient
    private  Product product;



    @ManyToMany(mappedBy = "ovChipkaartenList")
    private List<Product> productenLijst = new ArrayList<>();



    public OvChipkaart( int kaartNummer, Date geligTot, int klasse, float saldo){
        this.kaartNummer= kaartNummer;
        this.geligTot= geligTot;
        this.klasse = klasse;
        this.saldo= saldo;
        this.productenLijst = new List<Product>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Product> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Product product) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Product> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Product> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Product get(int index) {
                return null;
            }

            @Override
            public Product set(int index, Product element) {
                return null;
            }

            @Override
            public void add(int index, Product element) {

            }

            @Override
            public Product remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Product> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Product> listIterator(int index) {
                return null;
            }

            @Override
            public List<Product> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
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
                ", geligTot=" + geligTot +
                ", klasse=" + klasse +
                ", saldo=" + saldo +
                ", productenLijst=" + product +
                "} \n ";
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
