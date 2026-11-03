/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistempenyewaanalatpancing;

/**
 *
 * @author Hype AMD
 */
public abstract class Alatpancing {

    private String kode;
    private String nama;
    private double hargaPerHari;
    private int stok;

    private static int totalAlat = 0;

    public Alatpancing(String kode, String nama, double hargaPerHari, int stok) throws DataTidakValidException {
        setKode(kode);
        setNama(nama);
        setHargaPerHari(hargaPerHari);
        setStok(stok);
    }

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

    public void setKode(String kode) throws DataTidakValidException {
        if (kode == null || kode.trim().equals("")) {
            throw new DataTidakValidException("Kode tidak boleh kosong!");
        }
        this.kode = kode.trim().toUpperCase();
    }

    public void setNama(String nama) throws DataTidakValidException {
        if (nama == null || nama.trim().equals("")) {
            throw new DataTidakValidException("Nama alat tidak boleh kosong!");
        }
        this.nama = nama.trim();
    }

    public void setHargaPerHari(double hargaPerHari) throws DataTidakValidException {
        if (hargaPerHari <= 0) {
            throw new DataTidakValidException("Harga sewa harus lebih dari 0!");
        }
        this.hargaPerHari = hargaPerHari;
    }

    public void setStok(int stok) throws DataTidakValidException {
        if (stok < 0) {
            throw new DataTidakValidException("Stok tidak boleh negatif!");
        }
        this.stok = stok;
    }

    public boolean kurangiStok() {
        if (stok > 0) {
            stok--;
            return true;
        }
        return false;
    }

    public static void catatAlatBaru() {
        totalAlat++;
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

    public abstract double hitungDendaKeterlambatan(int hariTerlambat);
}