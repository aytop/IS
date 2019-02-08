package klase;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Izostanak {
    @Id
    private long idi;
    private long razredDjaka;
    private long redniBrojDjaka;
    private String opis;
    private long idp;
    private Date datum;

    public Izostanak() {
    }

    @Id
    @Column(name = "idi", nullable = false)
    public long getIdi() {
        return idi;
    }

    public void setIdi(long idi) {
        this.idi = idi;
    }

    @Basic
    @Column(name = "razred_djaka", nullable = false)
    public long getRazredDjaka() {
        return razredDjaka;
    }

    public void setRazredDjaka(long razredDjaka) {
        this.razredDjaka = razredDjaka;
    }

    @Basic
    @Column(name = "redni_broj_djaka", nullable = false)
    public long getRedniBrojDjaka() {
        return redniBrojDjaka;
    }

    public void setRedniBrojDjaka(long redniBrojDjaka) {
        this.redniBrojDjaka = redniBrojDjaka;
    }

    @Basic
    @Column(name = "opis", nullable = true, length = -1)
    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Basic
    @Column(name = "idp", nullable = false)
    public long getIdp() {
        return idp;
    }

    public void setIdp(long idp) {
        this.idp = idp;
    }

    @Basic
    @Column(name = "datum", nullable = false)
    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Izostanak(long idi, long razredDjaka, long redniBrojDjaka, String opis, long idp, Date datum) {
        this.idi = idi;
        this.razredDjaka = razredDjaka;
        this.redniBrojDjaka = redniBrojDjaka;
        this.opis = opis;
        this.idp = idp;
        this.datum = datum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Izostanak izostanak = (Izostanak) o;
        return idi == izostanak.idi &&
                razredDjaka == izostanak.razredDjaka &&
                redniBrojDjaka == izostanak.redniBrojDjaka &&
                idp == izostanak.idp &&
                Objects.equals(opis, izostanak.opis) &&
                Objects.equals(datum, izostanak.datum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idi, razredDjaka, redniBrojDjaka, opis, idp, datum);
    }
}
