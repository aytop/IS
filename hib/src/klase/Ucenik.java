package klase;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@IdClass(UcenikPK.class)
public class Ucenik {
    private long redniBroj;
    private String razred;
    private String ime;
    private String imeOca;
    private String prezime;
    private Date datumRodjenja;
    private String mestoPrebivalista;
    private String brojTelefona;
    private String username;

    public Ucenik() {
    }

    @Id
    @Column(name = "redni_broj", nullable = false)
    public long getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(long redniBroj) {
        this.redniBroj = redniBroj;
    }

    @Id
    @Column(name = "razred", nullable = false, length = -1)
    public String getRazred() {
        return razred;
    }

    public void setRazred(String razred) {
        this.razred = razred;
    }

    @Basic
    @Column(name = "ime", nullable = false, length = -1)
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    @Basic
    @Column(name = "ime_oca", nullable = true, length = -1)
    public String getImeOca() {
        return imeOca;
    }

    public void setImeOca(String imeOca) {
        this.imeOca = imeOca;
    }

    @Basic
    @Column(name = "prezime", nullable = false, length = -1)
    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Basic
    @Column(name = "datum_rodjenja", nullable = false)
    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    @Basic
    @Column(name = "mesto_prebivalista", nullable = true, length = -1)
    public String getMestoPrebivalista() {
        return mestoPrebivalista;
    }

    public void setMestoPrebivalista(String mestoPrebivalista) {
        this.mestoPrebivalista = mestoPrebivalista;
    }

    @Basic
    @Column(name = "broj_telefona", nullable = true, length = -1)
    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    @Basic
    @Column(name = "username", nullable = false, length = -1)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Ucenik(long redniBroj, String razred, String ime, String imeOca, String prezime, Date datumRodjenja, String mestoPrebivalista, String brojTelefona, String username) {
        this.redniBroj = redniBroj;
        this.razred = razred;
        this.ime = ime;
        this.imeOca = imeOca;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.mestoPrebivalista = mestoPrebivalista;
        this.brojTelefona = brojTelefona;
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ucenik ucenik = (Ucenik) o;
        return redniBroj == ucenik.redniBroj &&
                Objects.equals(razred, ucenik.razred) &&
                Objects.equals(ime, ucenik.ime) &&
                Objects.equals(imeOca, ucenik.imeOca) &&
                Objects.equals(prezime, ucenik.prezime) &&
                Objects.equals(datumRodjenja, ucenik.datumRodjenja) &&
                Objects.equals(mestoPrebivalista, ucenik.mestoPrebivalista) &&
                Objects.equals(brojTelefona, ucenik.brojTelefona) &&
                Objects.equals(username, ucenik.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(redniBroj, razred, ime, imeOca, prezime, datumRodjenja, mestoPrebivalista, brojTelefona, username);
    }
}
