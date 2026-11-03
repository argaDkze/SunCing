/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistempenyewaanalatpancing;

/**
 *
 * @author Hype AMD
 */
// SUBCLASS 4 
public class Umpan extends Alatpancing implements Dapatdinilai {

    private String jenisUmpan;
    private int beratGram;
    private int totalRating;
    private int jumlahRating;

    public Umpan(String kode, String nama, double hargaPerHari, int stok,
                 String jenisUmpan, int beratGram) throws DataTidakValidException {
        super(kode, nama, hargaPerHari, stok);
        setJenisUmpan(jenisUmpan);
        setBeratGram(beratGram);
    }

    public String getJenisUmpan() {
        return jenisUmpan;
    }

    public int getBeratGram() {
        return beratGram;
    }

    public void setJenisUmpan(String jenisUmpan) throws DataTidakValidException {
        if (jenisUmpan == null || jenisUmpan.trim().equals("")) {
            throw new DataTidakValidException("Jenis umpan tidak boleh kosong!");
        }
        this.jenisUmpan = jenisUmpan.trim();
    }

    public void setBeratGram(int beratGram) throws DataTidakValidException {
        if (beratGram <= 0) {
            throw new DataTidakValidException("Berat umpan harus lebih dari 0!");
        }
        this.beratGram = beratGram;
    }

    @Override
    public String getJenis() {
        return "Umpan";
    }

    @Override
    public double hitungBiayaSewa(int hari) {
        double total = super.hitungBiayaSewa(hari) + 1000;
        if (beratGram >= 100) {
            total = total - (total * 0.05);
        }
        return total;
    }

    @Override
    public double hitungDendaKeterlambatan(int hariTerlambat) {
        return 0;
    }

    @Override
    public void beriRating(int rating) throws DataTidakValidException {
        if (rating < RATING_MIN || rating > RATING_MAX) {
            throw new DataTidakValidException("Rating harus antara " + RATING_MIN + " - " + RATING_MAX + "!");
        }
        totalRating = totalRating + rating;
        jumlahRating++;
        System.out.println("Rating " + rating + " untuk " + getNama() + " tersimpan.");
    }

    @Override
    public double getRataRating() {
        if (jumlahRating == 0) {
            return 0;
        }
        return (double) totalRating / jumlahRating;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("         -> Jenis Umpan: " + jenisUmpan + " | Berat: " + beratGram + " gram");
        if (jumlahRating == 0) {
            System.out.println("         -> Rating: belum ada");
        } else {
            System.out.printf("         -> Rating: %.1f (%d ulasan)%n", getRataRating(), jumlahRating);
        }
    }
}