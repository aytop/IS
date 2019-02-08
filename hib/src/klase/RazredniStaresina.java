package klase;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Razredni_staresina", schema = "public", catalog = "is1_projekat_baza")
public class RazredniStaresina {
    private long mbr;
    private String idr;

    public RazredniStaresina() {
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
    @Column(name = "idr", nullable = false, length = -1)
    public String getIdr() {
        return idr;
    }

    public void setIdr(String idr) {
        this.idr = idr;
    }

    public RazredniStaresina(long mbr, String idr) {
        this.mbr = mbr;
        this.idr = idr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RazredniStaresina that = (RazredniStaresina) o;
        return mbr == that.mbr &&
                Objects.equals(idr, that.idr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mbr, idr);
    }
}
