package klase;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UcenikPK implements Serializable {
    private long redniBroj;
    private String razred;

    @Column(name = "redni_broj", nullable = false)
    @Id
    public long getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(long redniBroj) {
        this.redniBroj = redniBroj;
    }

    @Column(name = "razred", nullable = false, length = -1)
    @Id
    public String getRazred() {
        return razred;
    }

    public void setRazred(String razred) {
        this.razred = razred;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UcenikPK ucenikPK = (UcenikPK) o;
        return redniBroj == ucenikPK.redniBroj &&
                Objects.equals(razred, ucenikPK.razred);
    }

    @Override
    public int hashCode() {
        return Objects.hash(redniBroj, razred);
    }
}
