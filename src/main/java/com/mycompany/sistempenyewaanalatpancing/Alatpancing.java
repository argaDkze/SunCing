/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistempenyewaanalatpancing;

/**
 *
 * @author Hype AMD
 */
// SUPERCLASS (induk)
public class Alatpancing {

    // Encapsulation
    private String kode;
    private String nama;
    private double hargaPerHari;
    private int stok;

    private static int totalAlat = 0;

    // Constructor
    public Alatpancing(String kode, String nama, double hargaPerHari, int stok) {
        this.kode = "-";
        this.nama = "-";
        this.hargaPerHari = 0;
        this.stok = 0;

        setKode(kode);
        setNama(nama);
        setHargaPerHari(hargaPerHari);
        setStok(stok);
        totalAlat++;
    }

    // Gettet
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

    // Setter
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

    public static int getTotalAlat() {
        return totalAlat;
    }

    public String getJenis() {
        return "Alat Pancing";
    }

    public double hitungBiayaSewa(int hari) {
        return hargaPerHari * hari;
    }

    public double hitungBiayaSewa(int hari, boolean member) {
        double total = hitungBiayaSewa(hari);
        if (member) {
            total = total - (total * 0.10);
        }
        return total;
    }

    public void tampilkanInfo() {
        System.out.printf("%-8s %-12s %-24s Rp%-10.0f %-5d%n",
                kode, getJenis(), nama, hargaPerHari, stok);
    }
}