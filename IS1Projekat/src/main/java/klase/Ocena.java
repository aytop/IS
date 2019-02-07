package klase;

import java.util.Date;

public class Ocena {

    private long id;
    private long razred;
    private long redniBroj;
    private long ocena;
    private long idp;
    private Date datum;

    public Ocena(long id, long razred, long redniBroj, long ocena, long idp, Date datum) {
        this.id = id;
        this.razred = razred;
        this.redniBroj = redniBroj;
        this.ocena = ocena;
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

    public long getOcena() {
        return ocena;
    }

    public void setOcena(long ocena) {
        this.ocena = ocena;
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
