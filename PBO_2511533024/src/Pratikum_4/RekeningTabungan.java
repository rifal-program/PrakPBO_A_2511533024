package Pratikum_4;

import Pratikum_1.Rekening;

public class RekeningTabungan extends Rekening {
    private double sukuBunga;

    public RekeningTabungan(String nomor, String nama, double saldoAwal, String pin, double sukuBunga) {
        super(nomor, nama, saldoAwal, pin);
        this.sukuBunga = sukuBunga;
    }

    public void tambahBungaAkhirBulan() {
        if (terblokir) {
            System.out.println("Gagal: Akun anda terblokir");
            return;
        }
        double bunga = saldo * (sukuBunga / 100);
        saldo += bunga;
        System.out.println("Bunga sebesar Rp" + bunga + " (" + sukuBunga + "%) telah ditambahkan. Saldo saat ini: Rp" + saldo);
    }

    public double getSukuBunga() {
        return sukuBunga;
    }
}