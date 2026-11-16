/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistempenyewaanalatpancing;

/**
 *
 * @author Hype AMD
 */
import java.io.*;
import java.util.*;

public class TokoPancing {

    public static final String FILE_DATA = "data_toko.dat";

    private String namaToko;
    private GudangAlat gudang;
    private ArrayList<Alatpancing> daftarAlat;
    private HashMap<String, Anggota> dataAnggota;

    public TokoPancing(String namaToko) {
        this.namaToko = namaToko;
        this.gudang = new GudangAlat("Gudang Utama", 8);
        this.daftarAlat = new ArrayList<>();
        this.dataAnggota = new HashMap<>();
    }

    public void bukaToko() {
        System.out.println("=========================================");
        System.out.println("Toko " + namaToko + " resmi dibuka!");
        gudang.bukaGudang(daftarAlat.size());
        System.out.println("Jumlah anggota terdaftar        : " + dataAnggota.size());
        System.out.println("=========================================");
    }

    public void tambahAlat(Alatpancing alat) throws DataTidakValidException {
        if (!gudang.masihMuat(daftarAlat.size())) {
            throw new DataTidakValidException("Gudang penuh! Kapasitas maksimal " + gudang.getKapasitas() + " alat.");
        }
        daftarAlat.add(alat);
        Alatpancing.catatAlatBaru();
    }

    public boolean kodeSudahAda(String kode) {
        for (int i = 0; i < daftarAlat.size(); i++) {
            if (daftarAlat.get(i).getKode().equalsIgnoreCase(kode.trim())) {
                return true;
            }
        }
        return false;
    }

    public Alatpancing cariByKode(String kode) throws AlatTidakDitemukanException {
        for (int i = 0; i < daftarAlat.size(); i++) {
            if (daftarAlat.get(i).getKode().equalsIgnoreCase(kode.trim())) {
                return daftarAlat.get(i);
            }
        }
        throw new AlatTidakDitemukanException("Alat dengan kode \"" + kode.trim() + "\" tidak ditemukan!");
    }

    public ArrayList<Alatpancing> cariAlat(String kataKunci)
            throws AlatTidakDitemukanException, DataTidakValidException {
        if (kataKunci == null || kataKunci.trim().equals("")) {
            throw new DataTidakValidException("Kata kunci pencarian tidak boleh kosong!");
        }

        String kunci = kataKunci.trim().toLowerCase();
        ArrayList<Alatpancing> hasil = new ArrayList<>();

        for (int i = 0; i < daftarAlat.size(); i++) {
            Alatpancing alat = daftarAlat.get(i);
            boolean cocokKode = alat.getKode().toLowerCase().contains(kunci);
            boolean cocokNama = alat.getNama().toLowerCase().contains(kunci);
            boolean cocokJenis = alat.getJenis().toLowerCase().contains(kunci);
            if (cocokKode || cocokNama || cocokJenis) {
                hasil.add(alat);
            }
        }

        if (hasil.isEmpty()) {
            throw new AlatTidakDitemukanException("Tidak ada alat dengan kata kunci \"" + kataKunci.trim() + "\"!");
        }
        return hasil;
    }

    public Alatpancing getAlat(int index) {
        return daftarAlat.get(index);
    }

    public int getJumlahAlat() {
        return daftarAlat.size();
    }

    public void lihatSemuaAlat() {
        if (daftarAlat.isEmpty()) {
            System.out.println("Belum ada data alat pancing.");
            return;
        }
        tampilkanDaftar(daftarAlat);
        System.out.println("Jumlah alat di daftar     : " + daftarAlat.size());
        System.out.println("Total alat tercatat       : " + Alatpancing.getTotalAlat());
        System.out.println("Kapasitas gudang          : " + daftarAlat.size() + " / " + gudang.getKapasitas());
    }

    public void lihatPilihanAlat() {
        for (int i = 0; i < daftarAlat.size(); i++) {
            Alatpancing alat = daftarAlat.get(i);
            System.out.printf("%d. %-6s %-24s %-10s Rp%-8.0f stok: %d%n",
                    i + 1, alat.getKode(), alat.getNama(), alat.getJenis(), alat.getHargaPerHari(), alat.getStok());
        }
    }

    public void tampilkanDaftar(ArrayList<Alatpancing> daftar) {
        System.out.println("=========================================================================");
        System.out.printf("%-8s %-12s %-24s %-12s %-5s%n", "KODE", "JENIS", "NAMA", "HARGA/HARI", "STOK");
        System.out.println("=========================================================================");
        for (int i = 0; i < daftar.size(); i++) {
            Alatpancing alat = daftar.get(i);
            alat.tampilkanInfo();
            cetakKemampuan(alat);
        }
        System.out.println("=========================================================================");
    }

    private void cetakKemampuan(Alatpancing alat) {
        String kemampuan = "";
        if (alat instanceof Dapatdisewa) {
            kemampuan = kemampuan + "[Dapat Disewa] ";
        }
        if (alat instanceof Dapatdinilai) {
            kemampuan = kemampuan + "[Dapat Dinilai] ";
        }
        System.out.println("         -> Kemampuan: " + kemampuan);
    }

    public void simpanDataKeFile() {
        try {
            FileOutputStream fileKeluar = new FileOutputStream(FILE_DATA);
            ObjectOutputStream mesinPembeku = new ObjectOutputStream(fileKeluar);

            mesinPembeku.writeObject(daftarAlat);
            mesinPembeku.writeObject(dataAnggota);
            mesinPembeku.writeInt(TransaksiSewa.getNomorTransaksi());

            mesinPembeku.close();
            System.out.println("Data berhasil disimpan ke " + FILE_DATA + " ("
                    + daftarAlat.size() + " alat, " + dataAnggota.size() + " anggota).");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public boolean muatDataDariFile() {
        File fileData = new File(FILE_DATA);
        if (!fileData.exists()) {
            System.out.println("File " + FILE_DATA + " belum ada. Memakai data awal.");
            return false;
        }
        try {
            FileInputStream fileMasuk = new FileInputStream(fileData);
            ObjectInputStream mesinPembangkit = new ObjectInputStream(fileMasuk);

            ArrayList<Alatpancing> alatTerbaca = (ArrayList<Alatpancing>) mesinPembangkit.readObject();
            HashMap<String, Anggota> anggotaTerbaca = (HashMap<String, Anggota>) mesinPembangkit.readObject();
            int nomorTrx = mesinPembangkit.readInt();

            mesinPembangkit.close();

            daftarAlat = alatTerbaca;
            dataAnggota = anggotaTerbaca;
            Alatpancing.setTotalAlat(daftarAlat.size());
            TransaksiSewa.setNomorTransaksi(nomorTrx);

            System.out.println("Data berhasil dimuat dari " + FILE_DATA + " ("
                    + daftarAlat.size() + " alat, " + dataAnggota.size() + " anggota).");
            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Gagal memuat data: " + e.getMessage());
            return false;
        }
    }
    
    public void bacaLogTransaksi() {
        File fileLog = new File(TransaksiSewa.FILE_LOG);
        try {
            Scanner mesinBaca = new Scanner(fileLog);
            System.out.println("=========== ISI " + TransaksiSewa.FILE_LOG + " ===========");
            int jumlahBaris = 0;
            while (mesinBaca.hasNextLine()) {
                System.out.println(mesinBaca.nextLine());
                jumlahBaris++;
            }
            mesinBaca.close();
            System.out.println("=========================================");
            System.out.println("Total " + jumlahBaris + " catatan.");
        } catch (FileNotFoundException e) {
            System.out.println("Log transaksi belum ada (belum ada transaksi sewa).");
        }
    }

    public void tambahAnggota(Anggota anggota) throws DataTidakValidException {
        if (dataAnggota.containsKey(anggota.getId())) {
            throw new DataTidakValidException("ID anggota " + anggota.getId() + " sudah terdaftar!");
        }
        dataAnggota.put(anggota.getId(), anggota);
    }

    public boolean anggotaAda(String id) {
        return dataAnggota.containsKey(id.trim().toUpperCase());
    }

    public Anggota getAnggota(String id) {
        return dataAnggota.get(id.trim().toUpperCase());
    }

    public void cariAnggota(String id) {
        if (anggotaAda(id)) {
            Anggota anggota = getAnggota(id);
            System.out.println("Anggota ditemukan:");
            System.out.println("ID    : " + anggota.getId());
            System.out.println("Nama  : " + anggota.getNama());
            System.out.println("No HP : " + anggota.getNoHp());
        } else {
            System.out.println("ID anggota tidak terdaftar.");
        }
    }

    public void lihatSemuaAnggota() {
        if (dataAnggota.isEmpty()) {
            System.out.println("Belum ada data anggota.");
            return;
        }
        System.out.println("Jumlah anggota: " + dataAnggota.size());
        System.out.println("=================================================");
        System.out.printf("%-8s %-24s %-15s%n", "ID", "NAMA", "NO HP");
        System.out.println("=================================================");
        for (String id : dataAnggota.keySet()) {
            dataAnggota.get(id).tampilkanInfo();
        }
        System.out.println("=================================================");
    }
}