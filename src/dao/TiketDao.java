package dao;

import model.Tiket;
import java.util.List;

public interface TiketDao {
    void create(Tiket tiket);
    List<Tiket> readAll();
    void update(Tiket tiket);
    void delete(int idTiket);
    void updateStok(int idTiket, int stokBaru); // Digunakan saat transaksi mengurangi stok
}