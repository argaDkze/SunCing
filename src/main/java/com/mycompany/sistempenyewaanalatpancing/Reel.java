/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistempenyewaanalatpancing;

/**
 *
 * @author Hype AMD
 */
// SUBCLASS 2
public class Reel extends Alatpancing implements Dapatdisewa, Dapatdinilai {

    private String tipeReel;
    private double gearRatio;
    private int totalRating;
    private int jumlahRating;

    public Reel(String kode, String nama, double hargaPerHari, int stok,
                String tipeReel, double gearRatio) throws DataTidakValidException {
        super(kode, nama, hargaPerHari, stok);
        setTipeReel(tipeReel);
        setGearRatio(gearRatio);
    }

    public String getTipeReel() {
        return tipeReel;
    }

    public double getGearRatio() {
        return gearRatio;
    }

    public void setTipeReel(String tipeReel) throws DataTidakValidException {
        if (tipeReel == null || tipeReel.trim().equals("")) {
            throw new DataTidakValidException("Tipe reel tidak boleh kosong!");
        }
        this.tipeReel = tipeReel.trim();
    }

    public void setGearRatio(double gearRatio) throws DataTidakValidException {
        if (gearRatio < 3 || gearRatio > 10) {
            throw new DataTidakValidException("Gear ratio harus antara 3 - 10!");
        }
        this.gearRatio = gearRatio;
    }

    @Override
    public String getJenis() {
        return "Reel";
    }

    @Override
    public double hitungBiayaSewa(int hari) {
        return super.hitungBiayaSewa(hari) + 3000;
    }

    @Override
    public double hitungDendaKeterlambatan(int hariTerlambat) {
        return getHargaPerHari() * 0.4 * hariTerlambat;
    }

    @Override
    public void prosesSewaFisik() {
        if (kurangiStok()) {
            System.out.println("Reel " + getNama() + " dicek (drag dan putaran handle), lalu diserahkan ke penyewa.");
            System.out.println("Sisa stok: " + getStok());
        } else {
            System.out.println("Stok " + getNama() + " habis, tidak bisa disewa!");
        }
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
        System.out.println("         -> Tipe: " + tipeReel + " | Gear Ratio: " + gearRatio);
        if (jumlahRating == 0) {
            System.out.println("         -> Rating: belum ada");
        } else {
            System.out.printf("         -> Rating: %.1f (%d ulasan)%n", getRataRating(), jumlahRating);
        }
    }
}