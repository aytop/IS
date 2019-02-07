package klase;

import java.util.Date;

public class Izostanak {

    private long id;
    private long razred;
    private long redniBroj;
    private String opis;
    private long idp;
    private Date datum;

    public Izostanak(long id, long razred, long redniBroj, String opis, long idp, Date datum) {
        this.id = id;
        this.razred = razred;
        this.redniBroj = redniBroj;
        this.opis = opis;
        this.idp = idp;
        this.datum = datum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRazred() {
        return razred;
    }

    public void setRazred(long razred) {
        this.razred = razred;
    }

    public long getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(long redniBroj) {
        this.redniBroj = redniBroj;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public long getIdp() {
        return idp;
    }

    public void setIdp(long idp) {
        this.idp = idp;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
}
