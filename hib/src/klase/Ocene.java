package klase;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Ocene {
    @Id
    private long ido;
    private long razredDjaka;
    private long redniBrojDjaka;
    private long ocena;
    private long idp;
    private Date datum;

    public Ocene() {
    }

    @Id
    @Column(name = "ido", nullable = false)
    public long getIdo() {
        return ido;
    }

    public void setIdo(long ido) {
        this.ido = ido;
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
    @Column(name = "ocena", nullable = false)
    public long getOcena() {
        return ocena;
    }

    public void setOcena(long ocena) {
        this.ocena = ocena;
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

    public Ocene(long ido, long razredDjaka, long redniBrojDjaka, long ocena, long idp, Date datum) {
        this.ido = ido;
        this.razredDjaka = razredDjaka;
        this.redniBrojDjaka = redniBrojDjaka;
        this.ocena = ocena;
        this.idp = idp;
        this.datum = datum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ocene ocene = (Ocene) o;
        return ido == ocene.ido &&
                razredDjaka == ocene.razredDjaka &&
                redniBrojDjaka == ocene.redniBrojDjaka &&
                ocena == ocene.ocena &&
                idp == ocene.idp &&
                Objects.equals(datum, ocene.datum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ido, razredDjaka, redniBrojDjaka, ocena, idp, datum);
    }
}
