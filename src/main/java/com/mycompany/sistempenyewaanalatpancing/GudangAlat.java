/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistempenyewaanalatpancing;

/**
 *
 * @author Hype AMD
 */
public class GudangAlat {

    private String nama;
    private int kapasitas;

    public GudangAlat(String nama, int kapasitas) {
        this.nama = nama;
        this.kapasitas = kapasitas;
    }

    public String getNama() {
        return nama;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public boolean masihMuat(int jumlahTerisi) {
        return jumlahTerisi < kapasitas;
    }

    public void bukaGudang(int jumlahTerisi) {
        System.out.println("Komponen internal (Composition) : " + nama);
        System.out.println("Kapasitas gudang                : " + kapasitas + " alat");
        System.out.println("Terisi                          : " + jumlahTerisi + " alat");
        System.out.println("Sisa ruang                      : " + (kapasitas - jumlahTerisi) + " alat");
    }
}
