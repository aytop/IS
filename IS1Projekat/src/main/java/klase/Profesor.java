package klase;

public class Profesor {

    private long mbr;
    private String ime;
    private String prezime;
    private long fondCasova;
    private long radniStaz;
    private String brojTelefona;
    private String username;

    public Profesor(long mbr, String ime, String prezime, long fondCasova, long radniStaz, String brojTelefona, String username) {
        this.mbr = mbr;
        this.ime = ime;
        this.prezime = prezime;
        this.fondCasova = fondCasova;
        this.radniStaz = radniStaz;
        this.brojTelefona = brojTelefona;
        this.username = username;
    }

    public long getMbr() {
        return mbr;
    }

    public void setMbr(long mbr) {
        this.mbr = mbr;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public long getFondCasova() {
        return fondCasova;
    }

    public void setFondCasova(long fondCasova) {
        this.fondCasova = fondCasova;
    }

    public long getRadniStaz() {
        return radniStaz;
    }

    public void setRadniStaz(long radniStaz) {
        this.radniStaz = radniStaz;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
