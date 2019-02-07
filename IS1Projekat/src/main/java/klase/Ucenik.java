package klase;

import java.util.Date;

public class Ucenik {

    private long redniBroj;
    private long razred;
    private String ime;
    private String prezime;
    private String srednjeIme;
    private Date datumRodjenja;
    private String mestoPrebivalista;
    private String brojTelefona;
    private String username;

    public Ucenik(long redniBroj, long razred, String ime, String prezime, String srednjeIme, Date datumRodjenja, String mestoPrebivalista, String brojTelefona, String username) {
        this.redniBroj = redniBroj;
        this.razred = razred;
        this.ime = ime;
        this.prezime = prezime;
        this.srednjeIme = srednjeIme;
        this.datumRodjenja = datumRodjenja;
        this.mestoPrebivalista = mestoPrebivalista;
        this.brojTelefona = brojTelefona;
        this.username = username;
    }

    public long getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(long redniBroj) {
        this.redniBroj = redniBroj;
    }

    public long getRazred() {
        return razred;
    }

    public void setRazred(long razred) {
        this.razred = razred;
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

    public String getSrednjeIme() {
        return srednjeIme;
    }

    public void setSrednjeIme(String srednjeIme) {
        this.srednjeIme = srednjeIme;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getMestoPrebivalista() {
        return mestoPrebivalista;
    }

    public void setMestoPrebivalista(String mestoPrebivalista) {
        this.mestoPrebivalista = mestoPrebivalista;
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
