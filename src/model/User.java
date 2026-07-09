package model;

public class User {
    private int idUser;
    private String username;
    private String password;

    public User() {}

    public User(int idUser, String username, String password) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
    }

    // Getter dan Setter
    public int getIdUser() { return idUser; }
    public void setIdUser(int idUser) { this.idUser = idUser; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}