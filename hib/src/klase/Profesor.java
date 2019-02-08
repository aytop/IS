package klase;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Profesor {
    @Id
    private long mbr;
    private String ime;
    private String prezime;
    private long fondCasova;
    private long radniStaz;
    private String brojTelefona;
    private String username;

    public Profesor() {
    }

    @Id
    @Column(name = "mbr", nullable = false)
    public long getMbr() {
        return mbr;
    }

    public void setMbr(long mbr) {
        this.mbr = mbr;
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
    @Column(name = "prezime", nullable = false, length = -1)
    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Basic
    @Column(name = "fond_casova", nullable = false)
    public long getFondCasova() {
        return fondCasova;
    }

    public void setFondCasova(long fondCasova) {
        this.fondCasova = fondCasova;
    }

    @Basic
    @Column(name = "radni_staz", nullable = false)
    public long getRadniStaz() {
        return radniStaz;
    }

    public void setRadniStaz(long radniStaz) {
        this.radniStaz = radniStaz;
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

    public Profesor(long mbr, String ime, String prezime, long fondCasova, long radniStaz, String brojTelefona, String username) {
        this.mbr = mbr;
        this.ime = ime;
        this.prezime = prezime;
        this.fondCasova = fondCasova;
        this.radniStaz = radniStaz;
        this.brojTelefona = brojTelefona;
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profesor profesor = (Profesor) o;
        return mbr == profesor.mbr &&
                fondCasova == profesor.fondCasova &&
                radniStaz == profesor.radniStaz &&
                Objects.equals(ime, profesor.ime) &&
                Objects.equals(prezime, profesor.prezime) &&
                Objects.equals(brojTelefona, profesor.brojTelefona) &&
                Objects.equals(username, profesor.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mbr, ime, prezime, fondCasova, radniStaz, brojTelefona, username);
    }
}
