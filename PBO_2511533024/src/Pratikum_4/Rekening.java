package Pratikum_1;

import java.util.ArrayList;
import Pratikum_2.Transaksi;

public class Rekening {
    String nomorRekening;
    String namaPemilik;
    double saldo;
    String pin; // Atribut PIN
    int salahPinCount = 0; // Lacak batas salah PIN
    boolean terblokir = false; // Status pemblokiran akun

    // Implementasi asosiasi 
    ArrayList<Transaksi> riwayatTransaksi;
    
    // Constructor utama dengan parameter PIN
    public Rekening(String nomor, String nama, double saldoAwal, String pin) {
        if (saldoAwal < 50000) {
            System.out.println("Gagal: Nominal setor rekening awal harus minimal 50.000!");
            this.saldo = 0;
        } else {
            this.nomorRekening = nomor;
            this.namaPemilik = nama;
            this.saldo = saldoAwal;
            this.pin = pin;
            
            this.riwayatTransaksi = new ArrayList<>();
            
            System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat dengan saldo Rp" + saldo);
        }
    }

    // Fungsi Otentikasi PIN
    public boolean otentikasi(String pinInput) {
        if (terblokir) {
            return false;
        }

        if (this.pin.equals(pinInput)) {
            salahPinCount = 0; // Reset percobaan jika PIN benar
            return true;
        } else {
            salahPinCount++;
            if (salahPinCount >= 3) {
                terblokir = true;
                System.out.println("Akun anda terblokir");
            } else {
                System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
            }
            return false;
        }
    }

    public boolean isTerblokir() {
        return terblokir;
    }

    // FITUR: Setor Tunai
    public void setorTunai(double nominal) {
        if (terblokir) {
            System.out.println("Transaksi Gagal: Akun anda terblokir");
            return;
        }

        if (nominal < 10000) {
            String idTrx = "TRX-S-" + System.currentTimeMillis();   
            Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
            riwayatTransaksi.add(trxBaru);
            
            System.out.println("Transaksi Gagal: Minimal nominal penyetoran 10.000");
        } else {
            saldo += nominal;
            
            String idTrx = "TRX-S-" + System.currentTimeMillis();
            Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
            riwayatTransaksi.add(trxBaru);

            System.out.println("Setor tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
        }
    }

    // FITUR: Tarik Tunai
    public void tarikTunai(double nominal) {
        if (terblokir) {
            System.out.println("Transaksi Gagal: Akun anda terblokir");
            return;
        }

        if (nominal < 10000) {
            System.out.println("Transaksi Gagal: Minimal nominal penarikan 10.000");
        } else if (nominal > saldo) {
            System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp" + saldo);
        } else {
            saldo -= nominal;

            String idTrx = "TRX-T-" + System.currentTimeMillis();
            Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
            riwayatTransaksi.add(trxBaru);

            System.out.println("Tarik tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
        }
    }

    // FITUR: Cetak Mutasi Rekening
    public void cetakMutasi() {
        if (terblokir) {
            System.out.println("Akses Ditolak: Akun anda terblokir");
            return;
        }

        System.out.println("\n=== RIWAYAT MUTASI REKENING ===");
        if (riwayatTransaksi == null || riwayatTransaksi.isEmpty()) {
            System.out.println("Belum ada transaksi pada rekening ini");
        } else {
            int totalTransaksi = riwayatTransaksi.size();
            int startIndex = 0;
            if (totalTransaksi > 3) {
                startIndex = totalTransaksi - 3; 
            }

            for (int i = startIndex; i < totalTransaksi; i++) {
                riwayatTransaksi.get(i).cetakdetail();
            }
        }
        System.out.println("===============================");
    }
    
    // Menampilkan Informasi Rekening
    public void cekInformasi() {
        System.out.println("--- INFO REKENING ---");
        System.out.println("No. Rekening : " + nomorRekening);
        System.out.println("Nama Pemilik : " + namaPemilik);
        System.out.println("Saldo Akhir  : Rp" + saldo);
        System.out.println("Status Akun  : " + (terblokir ? "TERBLOKIR" : "Aktif"));
        System.out.println("---------------------");
    }
}