package dao;

/**
 * Interface TiketDao mendefinisikan metode CRUD untuk entitas Tiket.
 * Implementasi dari interface ini akan berinteraksi dengan database untuk menyimpan, membaca, memperbarui, dan menghapus data tiket.
 * @Author Frederico Wijaya
 * @version 1.2
*/

import model.Tiket;
import java.util.List;

public interface TiketDao {
    void create(Tiket tiket);
    List<Tiket> readAll();
    void update(Tiket tiket);
    void delete(int idTiket);
    void updateStok(int idTiket, int stokBaru); // Digunakan saat transaksi mengurangi stok
}