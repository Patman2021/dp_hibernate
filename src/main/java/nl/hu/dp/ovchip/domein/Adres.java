package nl.hu.dp.ovchip.domein;



import javax.persistence.*;

@Entity
@Table(name = "adres")
public class Adres {

    @Id
    @Column(name = "adres_id")
    private  int id;

    @Column(name = "postcode")
    private  String postcode;
    @Column(name = "huisnummer")
    private  String huisnummer;

    @Column(name = "straat")
    private String straat;
    @Column(name = "woonplaats")
    private  String woonplaats;
    @Column(name = "reiziger_id")
    private  int reizigerId;
    @OneToOne(mappedBy = "adres")
    private  Reiziger reiziger;


    public Adres(int id, String postcode, String huisnummer, String straat, String woonplaats, int reiziger){
        this.id= id;
        this.postcode= postcode;
        this.huisnummer= huisnummer;
        this.straat = straat;
        this.woonplaats= woonplaats;
        this.reizigerId =reiziger;

    }

    public Adres() {

    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public int getId() {
        return id;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public String getStraat() {
        return straat;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public int getReizigerId() {
        return reizigerId;
    }

    @Override
    public String toString() {
        return  " Adres{   #"+ reizigerId +",met adres_id="+id +": Postcode="+ postcode +", Huisnummer= "+ huisnummer +"}";
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
