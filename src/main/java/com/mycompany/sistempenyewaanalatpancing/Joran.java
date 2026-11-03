/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistempenyewaanalatpancing;

/**
 *
 * @author Hype AMD
 */
// SUBCLASS 1
public class Joran extends Alatpancing implements Dapatdisewa, Dapatdinilai {

    private int panjangCm;
    private String bahan;
    private int totalRating;
    private int jumlahRating;

    public Joran(String kode, String nama, double hargaPerHari, int stok,
                 int panjangCm, String bahan) throws DataTidakValidException {
        super(kode, nama, hargaPerHari, stok);
        setPanjangCm(panjangCm);
        setBahan(bahan);
    }

    public int getPanjangCm() {
        return panjangCm;
    }

    public String getBahan() {
        return bahan;
    }

    public void setPanjangCm(int panjangCm) throws DataTidakValidException {
        if (panjangCm < 100 || panjangCm > 1000) {
            throw new DataTidakValidException("Panjang joran harus 100 - 1000 cm!");
        }
        this.panjangCm = panjangCm;
    }

    public void setBahan(String bahan) throws DataTidakValidException {
        if (bahan == null || bahan.trim().equals("")) {
            throw new DataTidakValidException("Bahan joran tidak boleh kosong!");
        }
        this.bahan = bahan.trim();
    }

    @Override
    public String getJenis() {
        return "Joran";
    }

    @Override
    public double hitungBiayaSewa(int hari) {
        return super.hitungBiayaSewa(hari) + 5000;
    }

    @Override
    public double hitungDendaKeterlambatan(int hariTerlambat) {
        return getHargaPerHari() * 0.5 * hariTerlambat;
    }

    @Override
    public void prosesSewaFisik() {
        if (kurangiStok()) {
            System.out.println("Joran " + getNama() + " dicek (ruas dan guide), lalu diserahkan ke penyewa.");
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
        System.out.println("         -> Panjang: " + panjangCm + " cm | Bahan: " + bahan);
        if (jumlahRating == 0) {
            System.out.println("         -> Rating: belum ada");
        } else {
            System.out.printf("         -> Rating: %.1f (%d ulasan)%n", getRataRating(), jumlahRating);
        }
    }
}