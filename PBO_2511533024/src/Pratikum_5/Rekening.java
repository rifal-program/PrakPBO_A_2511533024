package Pratikum_1;

import java.util.ArrayList;
import Pratikum_2.Transaksi;

public class Rekening {
    private String nomorRekening;
    private String namaPemilik;
    protected double saldo;
    private String pin;
    protected int salahPinCount = 0;
    protected boolean terblokir = false;

    protected ArrayList<Transaksi> riwayatTransaksi;
    
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

    // Getter untuk atribut private
    public String getNomorRekening() {
        return nomorRekening;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public boolean otentikasi(String pinInput) {
        if (terblokir) {
            return false;
        }

        if (this.pin.equals(pinInput)) {
            salahPinCount = 0;
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

    public boolean tarikTunai(double nominal) {
        if (terblokir) {
            System.out.println("Transaksi Gagal: Akun anda terblokir");
            return false;
        }

        if (nominal < 10000) {
            System.out.println("Transaksi Gagal: Minimal nominal penarikan 10.000");
            return false;
        } else if (nominal > saldo) {
            System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp" + saldo);
            return false;
        } else {
            saldo -= nominal;

            String idTrx = "TRX-T-" + System.currentTimeMillis();
            Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
            riwayatTransaksi.add(trxBaru);

            System.out.println("Tarik tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
            return true; // Transaksi sukses
        }
    }

    // Tugas: Transfer antar rekening (parameter polimorfik: Tabungan atau Giro)
    public void transfer(Rekening tujuan, double nominal, String pinInput) {
        // Pertama: otentikasi PIN, jika gagal batalkan
        if (!otentikasi(pinInput)) {
            return;
        }

        // Kedua: tarik tunai (jika this adalah Giro, otomatis memakai aturan overdraft)
        boolean berhasil = this.tarikTunai(nominal);

        // Ketiga: jika penarikan sukses, setor ke rekening tujuan
        if (berhasil) {
            tujuan.setorTunai(nominal);
            System.out.println("Transfer Rp" + nominal + " ke rekening " + tujuan.getNomorRekening() + " (" + tujuan.getNamaPemilik() + ") selesai diproses.");
        }
    }

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
    
    public void cekInformasi() {
        System.out.println("--- INFO REKENING ---");
        System.out.println("No. Rekening : " + nomorRekening);
        System.out.println("Nama Pemilik : " + namaPemilik);
        System.out.println("Saldo Akhir  : Rp" + saldo);
        System.out.println("Status Akun  : " + (terblokir ? "TERBLOKIR" : "Aktif"));
        System.out.println("---------------------");
    }
}