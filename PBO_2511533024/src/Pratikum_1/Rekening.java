package Pratikum_1;

public class Rekening {
    String nomorRekening;
    String namaPemilik;
    double saldo;

    // Constructor utama untuk membuat objek Rekening
    public Rekening(String nomor, String nama, double saldoAwal) {
        // Validasi langsung saldo awal saat pembuatan rekening
        if (saldoAwal < 50000) {
            System.out.println("Gagal: Nominal setor rekening awal harus minimal 50.000!");
            this.saldo = 0; // Set saldo ke 0 karena tidak memenuhi syarat pembukaan rekening
        } else {
            this.nomorRekening = nomor;
            this.namaPemilik = nama;
            this.saldo = saldoAwal;
            System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat dengan saldo Rp" + saldo);
        }
    }

    // FITUR UPDATE: Setor Tunai (Minimal 10.000 tanpa titik desimal)
    public void setorTunai(double nominal) {
        if (nominal < 10000) {
            System.out.println("Transaksi Gagal: Minimal nominal penyetoran 10.000");
        } else {
            saldo += nominal;
            System.out.println("Setor tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
        }
    }

    // FITUR UPDATE: Tarik Tunai (Sesuai instruksi soal praktikum sebelumnya)
    public void tarikTunai(double nominal) {
        if (nominal < 10000) {
            System.out.println("Transaksi Gagal: Minimal nominal penarikan 10.000");
        } else if (nominal > saldo) {
            System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp" + saldo);
        } else {
            saldo -= nominal;
            System.out.println("Tarik tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
        }
    }

    // Menampilkan Informasi Rekening
    public void cekInformasi() {
        System.out.println("--- INFO REKENING ---");
        System.out.println("No. Rekening : " + nomorRekening);
        System.out.println("Nama Pemilik : " + namaPemilik);
        System.out.println("Saldo Akhir  : Rp" + saldo);
        System.out.println("---------------------");
    }
}
