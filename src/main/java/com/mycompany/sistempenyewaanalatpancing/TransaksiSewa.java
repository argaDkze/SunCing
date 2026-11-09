/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistempenyewaanalatpancing;

/**
 *
 * @author Hype AMD
 */
public class TransaksiSewa {

    private static int nomorTransaksi = 0;

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
    }
}
