/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistempenyewaanalatpancing;

/**
 *
 * @author Hype AMD
 */
public class Penyewaanmanager {

    private Alatpancing[] daftarAlat = new Alatpancing[50];
    private int jumlah = 0;

    public int getJumlah() {
        return jumlah;
    }

    public boolean tambahAlat(Alatpancing alat) {
        if (jumlah >= daftarAlat.length) {
            System.out.println("Data penuh, tidak bisa menambah alat lagi!");
            return false;
        }
        daftarAlat[jumlah] = alat;
        jumlah++;
        return true;
    }

    public boolean kodeSudahAda(String kode) {
        for (int i = 0; i < jumlah; i++) {
            if (daftarAlat[i].getKode().equalsIgnoreCase(kode)) {
                return true;
            }
        }
        return false;
    }

    public Alatpancing cariByKode(String kode) {
        for (int i = 0; i < jumlah; i++) {
            if (daftarAlat[i].getKode().equalsIgnoreCase(kode)) {
                return daftarAlat[i];
            }
        }
        return null;
    }

    private void cetakHeader() {
        System.out.println("=========================================================================");
        System.out.printf("%-8s %-12s %-24s %-12s %-5s%n", "KODE", "JENIS", "NAMA", "HARGA/HARI", "STOK");
        System.out.println("=========================================================================");
    }

    // Menampilkan semua data (memanggil method hasil overriding)
    public void tampilkanSemua() {
        if (jumlah == 0) {
            System.out.println("Belum ada data alat pancing.");
            return;
        }
        cetakHeader();
        for (int i = 0; i < jumlah; i++) {
            daftarAlat[i].tampilkanInfo();
        }
        System.out.println("=========================================================================");
        System.out.println("Total alat tercatat (objek dibuat): " + Alatpancing.getTotalAlat());
    }


    public void cariAlat(String kataKunci) {
        boolean ketemu = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftarAlat[i].getNama().toLowerCase().contains(kataKunci.toLowerCase())) {
                if (!ketemu) {
                    cetakHeader();
                }
                daftarAlat[i].tampilkanInfo();
                ketemu = true;
            }
        }
        if (!ketemu) {
            System.out.println("Alat dengan nama \"" + kataKunci + "\" tidak ditemukan.");
        }
    }

    public void cariAlat(double hargaMax) {
        boolean ketemu = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftarAlat[i].getHargaPerHari() <= hargaMax) {
                if (!ketemu) {
                    cetakHeader();
                }
                daftarAlat[i].tampilkanInfo();
                ketemu = true;
            }
        }
        if (!ketemu) {
            System.out.println("Tidak ada alat dengan harga sewa <= Rp" + hargaMax);
        }
    }

    public void cariAlat(String jenis, double hargaMax) {
        boolean ketemu = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftarAlat[i].getJenis().equalsIgnoreCase(jenis)
                    && daftarAlat[i].getHargaPerHari() <= hargaMax) {
                if (!ketemu) {
                    cetakHeader();
                }
                daftarAlat[i].tampilkanInfo();
                ketemu = true;
            }
        }
        if (!ketemu) {
            System.out.println("Tidak ada " + jenis + " dengan harga sewa <= Rp" + hargaMax);
        }
    }
}