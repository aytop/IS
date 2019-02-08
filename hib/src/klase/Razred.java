package klase;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Razred {
    @Id
    private String idr;
    private long godina;
    private long odelenje;
    private long razredniStaresina;

    public Razred() {
    }

    @Id
    @Column(name = "idr", nullable = false, length = -1)
    public String getIdr() {
        return idr;
    }

    public void setIdr(String idr) {
        this.idr = idr;
    }

    @Basic
    @Column(name = "godina", nullable = false)
    public long getGodina() {
        return godina;
    }

    public void setGodina(long godina) {
        this.godina = godina;
    }

    @Basic
    @Column(name = "odelenje", nullable = false)
    public long getOdelenje() {
        return odelenje;
    }

    public void setOdelenje(long odelenje) {
        this.odelenje = odelenje;
    }

    @Basic
    @Column(name = "razredni_staresina", nullable = false)
    public long getRazredniStaresina() {
        return razredniStaresina;
    }

    public void setRazredniStaresina(long razredniStaresina) {
        this.razredniStaresina = razredniStaresina;
    }

    public Razred(String idr, long godina, long odelenje, long razredniStaresina) {
        this.idr = idr;
        this.godina = godina;
        this.odelenje = odelenje;
        this.razredniStaresina = razredniStaresina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Razred razred = (Razred) o;
        return godina == razred.godina &&
                odelenje == razred.odelenje &&
                razredniStaresina == razred.razredniStaresina &&
                Objects.equals(idr, razred.idr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idr, godina, odelenje, razredniStaresina);
    }
}
