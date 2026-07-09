package dao;

/**
 * Interface TransaksiDao mendefinisikan metode untuk menyimpan transaksi dan membaca data transaksi beserta nama tiket terkait.
 * Implementasi dari interface ini akan berinteraksi dengan database untuk menyimpan dan mengambil data transaksi.
 * @Author Frederico Wijaya
 * @version 1.2
*/

import model.Transaksi;
import java.util.List;

public interface TransaksiDao {
    void simpanTransaksi(Transaksi transaksi);
    List<Transaksi> readAllWithTiketName();
}