package nl.hu.dp.ovchip.domein;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reiziger")
public class Reiziger {
    @GeneratedValue
    @Id
    @Column(name = "reiziger_id")
    private  int id;
    @Column(name = "voorletters")
    private String voorletters;
    @Column(name = "tussenvoegsel")
    private  String tussenvoegsel;
    @Column(name = "achternaam")
    private String achternaam;
    @Temporal(TemporalType.DATE)
    @Column(name = "geboortedatum")
    private Date geboortedatum;
    @OneToOne()
    @JoinColumn(name= "reiziger_id", nullable = false)
    private Adres adres;
    @OneToMany(mappedBy = "reiziger", cascade = CascadeType.ALL,
    orphanRemoval = true)
    private List<OvChipkaart> ovChipkaart;

    public  Reiziger( int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum){
        this.id= id;
        this.voorletters = voorletters;
        this.tussenvoegsel= tussenvoegsel;
        this.achternaam= achternaam;
        this.geboortedatum= geboortedatum;
        ovChipkaart =  new ArrayList<>();
    }

    public Reiziger() {

    }

    public  int getId(){
        return  this.id;
    }


    public String getNaam(){
        return  this.voorletters + " " + this.tussenvoegsel + " " + this.achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setAdres(Adres adres){
        this.adres= adres;
    }

    public void setOvChipkaart(ArrayList<OvChipkaart> ovChipkaart) {
        this.ovChipkaart = ovChipkaart;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public List<OvChipkaart> getOvChipkaart() {
        return ovChipkaart;
    }

    public void addOvChipKaart(OvChipkaart ovChipkaart) {
        this.ovChipkaart.add(ovChipkaart);
    }

    public void removeOvChipKaart(OvChipkaart ov){
            ovChipkaart.remove(ov);
    }

    public Adres getAdres() {
        return adres;
    }



    @Override
    public String toString() {
        return   "reiziger {"+this.id +": "+ getNaam() +". Geboren op:"+ geboortedatum +" "+ adres +"}  Ov_chipkaart" + ovChipkaart;
    }

}
