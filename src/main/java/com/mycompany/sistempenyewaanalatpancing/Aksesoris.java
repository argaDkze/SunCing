/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistempenyewaanalatpancing;

/**
 *
 * @author Hype AMD
 */
public class Aksesoris extends Alatpancing implements Dapatdisewa {

    private String jenisAksesoris;
    private int jumlahItem;

    public Aksesoris(String kode, String nama, double hargaPerHari, int stok,
                     String jenisAksesoris, int jumlahItem) throws DataTidakValidException {
        super(kode, nama, hargaPerHari, stok);
        this.jenisAksesoris = "-";
        this.jumlahItem = 0;
        setJenisAksesoris(jenisAksesoris);
        setJumlahItem(jumlahItem);
    }

    public String getJenisAksesoris() {
        return jenisAksesoris;
    }

    public int getJumlahItem() {
        return jumlahItem;
    }

    public void setJenisAksesoris(String jenisAksesoris) throws DataTidakValidException {
        if (jenisAksesoris == null || jenisAksesoris.trim().equals("")) {
            throw new DataTidakValidException("Jenis aksesoris tidak boleh kosong!");
        }
        this.jenisAksesoris = jenisAksesoris.trim();
    }

    public void setJumlahItem(int jumlahItem) throws DataTidakValidException {
        if (jumlahItem <= 0) {
            throw new DataTidakValidException("Jumlah item harus lebih dari 0!");
        }
        this.jumlahItem = jumlahItem;
    }

    @Override
    public String getJenis() {
        return "Aksesoris";
    }

    @Override
    public double hitungBiayaSewa(int hari) {
        double total = super.hitungBiayaSewa(hari);
        if (hari >= 3) {
            total = total - (total * 0.10);
        }
        return total;
    }

    @Override
    public double hitungDendaKeterlambatan(int hariTerlambat) {
        return getHargaPerHari() * 0.25 * hariTerlambat;
    }

    @Override
    public void prosesSewaFisik() {
        if (kurangiStok()) {
            System.out.println("Aksesoris " + getNama() + " dihitung ulang isinya (" + jumlahItem
                    + " item), lalu diserahkan ke penyewa.");
            System.out.println("Sisa stok: " + getStok());
        } else {
            System.out.println("Stok " + getNama() + " habis, tidak bisa disewa!");
        }
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("         -> Jenis: " + jenisAksesoris + " | Isi: " + jumlahItem + " item");
    }
}