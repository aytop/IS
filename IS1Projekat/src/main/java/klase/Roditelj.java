package klase;

import java.util.List;

public class Roditelj {

    private long idr;
    private String ime;
    private String prezime;
    private String username;
    private List<Ucenik> deca;

    public Roditelj(long idr, String ime, String prezime, String username, List<Ucenik> deca) {
        this.idr = idr;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.deca = deca;
    }

    public long getIdr() {
        return idr;
    }

    public void setIdr(long idr) {
        this.idr = idr;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Ucenik> getDeca() {
        return deca;
    }

    public void setDeca(List<Ucenik> deca) {
        this.deca = deca;
    }
}
