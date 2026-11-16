/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistempenyewaanalatpancing;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Hype AMD
 */
public class TransaksiSewa {

    public static final String FILE_LOG = "log_transaksi.txt";

    private static int nomorTransaksi = 0;

    public static int getNomorTransaksi() {
        return nomorTransaksi;
    }

    public static void setNomorTransaksi(int nomor) {
        nomorTransaksi = nomor;
    }

    public static void catatLog(String isi) {
        try {
            FileWriter pipaTulis = new FileWriter(FILE_LOG, true);
            String waktu = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            pipaTulis.write("[" + waktu + "] " + isi + "\n");
            pipaTulis.close();
        } catch (IOException e) {
            System.out.println("Gagal menulis log: " + e.getMessage());
        }
    }

    public static void prosesSewa(Anggota penyewa, Alatpancing alat, int hari) throws DataTidakValidException {
        if (hari <= 0) {
            throw new DataTidakValidException("Lama sewa harus lebih dari 0 hari!");
        }
        if (!alat.kurangiStok()) {
            throw new DataTidakValidException("Stok " + alat.getNama() + " habis, transaksi dibatalkan!");
        }

        nomorTransaksi++;
        double total = alat.hitungBiayaSewa(hari, true);

        System.out.println("\n===== STRUK TRANSAKSI SEWA =====");
        System.out.printf("No. Transaksi : TRX-%03d%n", nomorTransaksi);
        System.out.println("Penyewa       : " + penyewa.getNama() + " (" + penyewa.getId() + ")");
        System.out.println("Alat          : " + alat.getNama() + " (" + alat.getJenis() + ")");
        System.out.println("Lama sewa     : " + hari + " hari");
        System.out.printf("Total biaya   : Rp%.0f (sudah diskon anggota 10%%)%n", total);
        System.out.println("Sisa stok     : " + alat.getStok());
        System.out.println("Status        : BERHASIL");
        System.out.println("================================");

        catatLog(String.format("TRX-%03d | %s (%s) | %s (%s) | %d hari | Rp%.0f",
                nomorTransaksi, penyewa.getNama(), penyewa.getId(),
                alat.getNama(), alat.getJenis(), hari, total));
        System.out.println("Log transaksi dicatat ke " + FILE_LOG);
    }
}
