package dao;

/**
 * Interface UserDao mendefinisikan metode untuk autentikasi pengguna.
 * Implementasi dari interface ini akan berinteraksi dengan database untuk memverifikasi kredensial pengguna.
 * @Author Frederico Wijaya
 * @version 1.2
*/ 

import model.User;

public interface UserDao {
    User login(String username, String password);
}