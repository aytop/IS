package klase;

public class Predmet {

    private long idp;
    private String naziv;
    private long fondCasova;

    public Predmet(long idp, String naziv, long fondCasova) {
        this.idp = idp;
        this.naziv = naziv;
        this.fondCasova = fondCasova;
    }

    public long getIdp() {
        return idp;
    }

    public void setIdp(long idp) {
        this.idp = idp;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public long getFondCasova() {
        return fondCasova;
    }

    public void setFondCasova(long fondCasova) {
        this.fondCasova = fondCasova;
    }
}
