package klase;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "User_info", schema = "public", catalog = "is1_projekat_baza")
public class UserInfo {
    private String username;
    private String password;
    private long uloga;

    public UserInfo() {
    }

    @Id
    @Column(name = "username", nullable = false, length = -1)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = -1)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "uloga", nullable = false)
    public long getUloga() {
        return uloga;
    }

    public void setUloga(long uloga) {
        this.uloga = uloga;
    }

    public UserInfo(String username, String password, long uloga) {
        this.username = username;
        this.password = password;
        this.uloga = uloga;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return uloga == userInfo.uloga &&
                Objects.equals(username, userInfo.username) &&
                Objects.equals(password, userInfo.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, uloga);
    }
}
