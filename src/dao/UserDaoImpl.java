package dao;

import config.DatabaseConnection;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    @Override
    public User login(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Menggunakan tanda tanya (?) mencegah SQL Injection secara total
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Jika data ditemukan, kembalikan objek User
                    return new User(
                        rs.getInt("id_user"),
                        rs.getString("username"),
                        rs.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Mengembalikan null jika username/password salah atau terjadi error
    }
}