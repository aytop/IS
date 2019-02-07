package klase;

public class RazredniStaresina extends Profesor {

    private Razred razred;

    public RazredniStaresina(long mbr, String ime, String prezime, long fondCasova, long radniStaz, String brojTelefona, String username, Razred razred) {
        super(mbr, ime, prezime, fondCasova, radniStaz, brojTelefona, username);
        this.razred = razred;
    }

    public Razred getRazred() {
        return razred;
    }

    public void setRazred(Razred razred) {
        this.razred = razred;
    }
}
