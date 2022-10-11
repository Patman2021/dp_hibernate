package nl.hu.dp.ovchip.domein;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @GeneratedValue
    @Id
    @Column(name = "product_nummer")
    private int productNummer;

    @Column(name = "naam")
    private String naam;
    @Column(name = "beschrijving")
    private String beschrijving;
    @Column(name = "prijs")
    private float prijs;
    @Transient
    private OvChipkaart ovChipkaart;
    @ManyToMany
    @JoinTable(name = "ov_chipkaart_product",
             joinColumns = @JoinColumn(name = "product_nummer"), inverseJoinColumns = @JoinColumn(name = "kaart_nummer"))
    private List<OvChipkaart> ovChipkaartenList = new ArrayList<>();




    public Product(int productNummer, String naam, String beschrijving, float prijs) {
        this.productNummer = productNummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        this.ovChipkaartenList = new ArrayList<>();
    }

    public  Product(){}


    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void deleteOvkaart(OvChipkaart ov) {
        if (ovChipkaartenList.contains(ov)) {
            ovChipkaartenList.remove(ov);
        }

        if (ov.getProductenLijst().contains(this)) {
            ov.deleteProductFromList(this);
        }
    }

    public void addOvkaart(OvChipkaart ov) {
        if (!ovChipkaartenList.contains(ov)) {
            ovChipkaartenList.add(ov);
        }

        if (!ov.getProductenLijst().contains(this)) {
            ov.addProductToList(this);
        }


    }

    public List<OvChipkaart> getOvChipkaarten() {
        return ovChipkaartenList;
    }


    public float getPrijs() {
        return prijs;
    }

    public int getProductNummer() {
        return productNummer;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public String getNaam() {
        return naam;
    }

    public OvChipkaart getOvChipkaart() {
        return ovChipkaart;
    }

    public List<OvChipkaart> getOvChipkaartenList() {
        return ovChipkaartenList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productNummer=" + productNummer +
                ", naam='" + naam + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", prijs=" + prijs +
                " \n , ovChipkaartenList=" + ovChipkaartenList +
                '}';
    }
}
