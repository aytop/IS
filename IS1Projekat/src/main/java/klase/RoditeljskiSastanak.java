package klase;

import java.util.Date;

public class RoditeljskiSastanak {
    private String razred;
    private Date datum;
    private String opis;

    public RoditeljskiSastanak(String godina, String redniBrojRazreda, Date datum, String opis) {
        this.razred = razred;
        this.datum = datum;
        this.opis = opis;
    }

    public String getRazred() {
        return razred;
    }

    public void setRazred(String razred) {
        this.razred = razred;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
