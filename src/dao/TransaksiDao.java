package dao;

import model.Transaksi;
import java.util.List;

public interface TransaksiDao {
    void simpanTransaksi(Transaksi transaksi);
    List<Transaksi> readAllWithTiketName();
}