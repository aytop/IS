package klase;

public class Razred {

    private String idr;
    private long godina;
    private long odelenje;
    private RazredniStaresina razredniStaresina;

    public Razred(long godina, long odelenje, RazredniStaresina razredniStaresina) {
        this.idr = godina + "/" + odelenje;
        this.godina = godina;
        this.odelenje = odelenje;
        this.razredniStaresina = razredniStaresina;
    }

    public String getIdr() {
        return idr;
    }

    public void setIdr(String idr) {
        this.idr = idr;
    }

    public long getGodina() {
        return godina;
    }

    public void setGodina(long godina) {
        this.godina = godina;
    }

    public long getOdelenje() {
        return odelenje;
    }

    public void setOdelenje(long odelenje) {
        this.odelenje = odelenje;
    }

    public RazredniStaresina getRazredniStaresina() {
        return razredniStaresina;
    }

    public void setRazredniStaresina(RazredniStaresina razredniStaresina) {
        this.razredniStaresina = razredniStaresina;
    }
}
