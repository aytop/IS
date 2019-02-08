package klase;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Predmeti {
    @Id
    private long idp;
    private String nazivPredmeta;
    private long brCasovaNedeljno;

    public Predmeti() {
    }

    @Id
    @Column(name = "idp", nullable = false)
    public long getIdp() {
        return idp;
    }

    public void setIdp(long idp) {
        this.idp = idp;
    }

    @Basic
    @Column(name = "naziv_predmeta", nullable = false, length = -1)
    public String getNazivPredmeta() {
        return nazivPredmeta;
    }

    public void setNazivPredmeta(String nazivPredmeta) {
        this.nazivPredmeta = nazivPredmeta;
    }

    @Basic
    @Column(name = "br_casova_nedeljno", nullable = false)
    public long getBrCasovaNedeljno() {
        return brCasovaNedeljno;
    }

    public void setBrCasovaNedeljno(long brCasovaNedeljno) {
        this.brCasovaNedeljno = brCasovaNedeljno;
    }

    public Predmeti(long idp, String nazivPredmeta, long brCasovaNedeljno) {
        this.idp = idp;
        this.nazivPredmeta = nazivPredmeta;
        this.brCasovaNedeljno = brCasovaNedeljno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Predmeti predmeti = (Predmeti) o;
        return idp == predmeti.idp &&
                brCasovaNedeljno == predmeti.brCasovaNedeljno &&
                Objects.equals(nazivPredmeta, predmeti.nazivPredmeta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idp, nazivPredmeta, brCasovaNedeljno);
    }
}
