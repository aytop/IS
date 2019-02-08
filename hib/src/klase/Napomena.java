package klase;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Napomena {
    @Id
    private long idn;
    private long razredDjaka;
    private long redniBrojDjaka;
    private String opis;
    private long idp;
    private Date datum;

    public Napomena() {
    }

    @Id
    @Column(name = "idn", nullable = false)
    public long getIdn() {
        return idn;
    }

    public void setIdn(long idn) {
        this.idn = idn;
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
    @Column(name = "opis", nullable = false, length = -1)
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

    public Napomena(long idn, long razredDjaka, long redniBrojDjaka, String opis, long idp, Date datum) {
        this.idn = idn;
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
        Napomena napomena = (Napomena) o;
        return idn == napomena.idn &&
                razredDjaka == napomena.razredDjaka &&
                redniBrojDjaka == napomena.redniBrojDjaka &&
                idp == napomena.idp &&
                Objects.equals(opis, napomena.opis) &&
                Objects.equals(datum, napomena.datum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idn, razredDjaka, redniBrojDjaka, opis, idp, datum);
    }
}
