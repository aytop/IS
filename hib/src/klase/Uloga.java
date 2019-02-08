package klase;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class    Uloga {
    private long idu;
    private boolean adminPrava;
    private boolean profesorPrava;
    private boolean razredniPrava;
    private boolean ucenikPrava;
    private boolean roditeljPrava;

    public Uloga() {
    }

    @Id
    @Column(name = "idu", nullable = false)
    public long getIdu() {
        return idu;
    }

    public void setIdu(long idu) {
        this.idu = idu;
    }

    @Basic
    @Column(name = "admin_prava", nullable = false)
    public boolean isAdminPrava() {
        return adminPrava;
    }

    public void setAdminPrava(boolean adminPrava) {
        this.adminPrava = adminPrava;
    }

    @Basic
    @Column(name = "profesor_prava", nullable = false)
    public boolean isProfesorPrava() {
        return profesorPrava;
    }

    public void setProfesorPrava(boolean profesorPrava) {
        this.profesorPrava = profesorPrava;
    }

    @Basic
    @Column(name = "razredni_prava", nullable = false)
    public boolean isRazredniPrava() {
        return razredniPrava;
    }

    public void setRazredniPrava(boolean razredniPrava) {
        this.razredniPrava = razredniPrava;
    }

    @Basic
    @Column(name = "ucenik_prava", nullable = false)
    public boolean isUcenikPrava() {
        return ucenikPrava;
    }

    public void setUcenikPrava(boolean ucenikPrava) {
        this.ucenikPrava = ucenikPrava;
    }

    @Basic
    @Column(name = "roditelj_prava", nullable = false)
    public boolean isRoditeljPrava() {
        return roditeljPrava;
    }

    public void setRoditeljPrava(boolean roditeljPrava) {
        this.roditeljPrava = roditeljPrava;
    }

    public Uloga(long idu, boolean adminPrava, boolean profesorPrava, boolean razredniPrava, boolean ucenikPrava, boolean roditeljPrava) {
        this.idu = idu;
        this.adminPrava = adminPrava;
        this.profesorPrava = profesorPrava;
        this.razredniPrava = razredniPrava;
        this.ucenikPrava = ucenikPrava;
        this.roditeljPrava = roditeljPrava;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Uloga uloga = (Uloga) o;
        return idu == uloga.idu &&
                adminPrava == uloga.adminPrava &&
                profesorPrava == uloga.profesorPrava &&
                razredniPrava == uloga.razredniPrava &&
                ucenikPrava == uloga.ucenikPrava &&
                roditeljPrava == uloga.roditeljPrava;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idu, adminPrava, profesorPrava, razredniPrava, ucenikPrava, roditeljPrava);
    }
}
