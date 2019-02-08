package klase;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Roditelj {
    private long idr;
    private String ime;
    private String prezime;
    private String username;

    public Roditelj() {
    }

    @Id
    @Column(name = "idr", nullable = false)
    public long getIdr() {
        return idr;
    }

    public void setIdr(long idr) {
        this.idr = idr;
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
    @Column(name = "username", nullable = false, length = -1)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Roditelj(long idr, String ime, String prezime, String username) {
        this.idr = idr;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roditelj roditelj = (Roditelj) o;
        return idr == roditelj.idr &&
                Objects.equals(ime, roditelj.ime) &&
                Objects.equals(prezime, roditelj.prezime) &&
                Objects.equals(username, roditelj.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idr, ime, prezime, username);
    }
}
