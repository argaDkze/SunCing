/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistempenyewaanalatpancing;

/**
 *
 * @author Hype AMD
 */
// SUPERCLASS
// SUPERCLASS (induk)
public class Alatpancing {

    // Encapsulation: semua field private
    private String kode;
    private String nama;
    private double hargaPerHari;
    private int stok;

    // Static: pencatat total objek yang berhasil dibuat
    private static int totalAlat = 0;

    // Constructor (memakai this)
    public Alatpancing(String kode, String nama, double hargaPerHari, int stok) {
        this.kode = "-";
        this.nama = "-";
        this.hargaPerHari = 0;
        this.stok = 0;
        // panggil setter supaya data ikut divalidasi
        setKode(kode);
        setNama(nama);
        setHargaPerHari(hargaPerHari);
        setStok(stok);
        totalAlat++;
    }

    // Getter
    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public double getHargaPerHari() {
        return hargaPerHari;
    }

    public int getStok() {
        return stok;
    }

    // setter
    public void setKode(String kode) {
        if (kode == null || kode.trim().equals("")) {
            System.out.println("Kode tidak boleh kosong!");
        } else {
            this.kode = kode.trim().toUpperCase();
        }
    }

    public void setNama(String nama) {
        if (nama == null || nama.trim().equals("")) {
            System.out.println("Nama alat tidak boleh kosong!");
        } else {
            this.nama = nama.trim();
        }
    }

    public void setHargaPerHari(double hargaPerHari) {
        if (hargaPerHari <= 0) {
            System.out.println("Harga sewa harus lebih dari 0!");
        } else {
            this.hargaPerHari = hargaPerHari;
        }
    }

    public void setStok(int stok) {
        if (stok < 0) {
            System.out.println("Stok tidak boleh negatif!");
        } else {
            this.stok = stok;
        }
    }

    // Static method
    public static int getTotalAlat() {
        return totalAlat;
    }

    // Jenis alat (akan di-override oleh subclass)
    public String getJenis() {
        return "Alat Pancing";
    }

    // Method OVERLOADING: hitungBiayaSewa (nama sama, parameter beda)
    public double hitungBiayaSewa(int hari) {
        return hargaPerHari * hari;
    }

    public double hitungBiayaSewa(int hari, boolean member) {
        double total = hitungBiayaSewa(hari);
        if (member) {
            total = total - (total * 0.10); // diskon member 10%
        }
        return total;
    }

    // Menampilkan satu baris data (akan di-override oleh subclass)
    public void tampilkanInfo() {
        System.out.printf("%-8s %-12s %-24s Rp%-10.0f %-5d%n",
                kode, getJenis(), nama, hargaPerHari, stok);
    }
}