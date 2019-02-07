package klase;

public class User {

    private String username;
    private String password;
    private Uloga uloga;
    private enum Uloga {admin, roditelj, ucenik, profesor, razredni}

    public User(String username, String password, long idu) {
        this.username = username;
        this.password = password;
        switch((int)idu){
            case 0:
                this.uloga = Uloga.admin;
                break;
            case 1:
                this.uloga = Uloga.profesor;
                break;
            case 2:
                this.uloga = Uloga.razredni;
                break;
            case 3:
                this.uloga = Uloga.ucenik;
                break;
            case 4:
                this.uloga = Uloga.roditelj;
                break;
        }
    }
}
