package dao;

/**
 * Implementasi dari interface TransaksiDao yang berinteraksi dengan database MySQL.
 * Menggunakan JDBC untuk menyimpan transaksi dan membaca data transaksi beserta nama tiket terkait.
 * @Author Frederico Wijaya
 * @version 1.2
*/

import config.DatabaseConnection;
import model.Transaksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDaoImpl implements TransaksiDao {

    @Override
    public void simpanTransaksi(Transaksi transaksi) {
        String sql = "INSERT INTO transaksi (id_tiket, jumlah_beli, total_harga, bayar, kembalian) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, transaksi.getIdTiket());
            stmt.setInt(2, transaksi.getJumlahBeli());
            stmt.setDouble(3, transaksi.getTotalHarga());
            stmt.setDouble(4, transaksi.getBayar());
            stmt.setDouble(5, transaksi.getKembalian());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transaksi> readAllWithTiketName() {
        List<Transaksi> list = new ArrayList<>();
        String sql = "SELECT t.*, tk.nama_tiket FROM transaksi t " +
                     "JOIN tiket tk ON t.id_tiket = tk.id_tiket " +
                     "ORDER BY t.id_transaksi DESC";
                     
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Transaksi trans = new Transaksi(
                    rs.getInt("id_transaksi"),
                    rs.getInt("id_tiket"),
                    rs.getString("nama_tiket"),
                    rs.getInt("jumlah_beli"),
                    rs.getDouble("total_harga"),
                    rs.getDouble("bayar"),
                    rs.getDouble("kembalian"),
                    rs.getString("tanggal_transaksi")
                );
                list.add(trans);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}