package dao;

/**
 * Implementasi dari interface TiketDao yang berinteraksi dengan database MySQL.
 * Menggunakan JDBC untuk melakukan operasi CRUD pada tabel tiket.
 * @Author Frederico Wijaya
 * @version 1.2
*/

import config.DatabaseConnection;
import model.Tiket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TiketDaoImpl implements TiketDao {

    @Override
    public void create(Tiket tiket) {
        String sql = "INSERT INTO tiket (nama_tiket, harga, stok_tiket) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, tiket.getNamaTiket());
            stmt.setDouble(2, tiket.getHarga());
            stmt.setInt(3, tiket.getStokTiket());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Tiket> readAll() {
        List<Tiket> list = new ArrayList<>();
        String sql = "SELECT * FROM tiket";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Tiket tiket = new Tiket(
                    rs.getInt("id_tiket"),
                    rs.getString("nama_tiket"),
                    rs.getDouble("harga"),
                    rs.getInt("stok_tiket")
                );
                list.add(tiket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void update(Tiket tiket) {
        String sql = "UPDATE tiket SET nama_tiket = ?, harga = ?, stok_tiket = ? WHERE id_tiket = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, tiket.getNamaTiket());
            stmt.setDouble(2, tiket.getHarga());
            stmt.setInt(3, tiket.getStokTiket());
            stmt.setInt(4, tiket.getIdTiket());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int idTiket) {
        String sql = "DELETE FROM tiket WHERE id_tiket = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idTiket);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStok(int idTiket, int stokBaru) {
        String sql = "UPDATE tiket SET stok_tiket = ? WHERE id_tiket = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, stokBaru);
            stmt.setInt(2, idTiket);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}