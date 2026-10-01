package Pratikum_1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        ArrayList<Rekening> daftarRekening = new ArrayList<>();
        Rekening akunAktif = null; 
        boolean isRunning = true;

        System.out.println("=== SISTEM PERBANKAN MINI ===");

        while (isRunning) {
            if (akunAktif != null) {
                String status = akunAktif.isTerblokir() ? " [TERBLOKIR]" : "";
                System.out.println("\n[Akun Aktif saat ini: " + akunAktif.namaPemilik + " (" + akunAktif.nomorRekening + ")" + status + "]");
            } else {
                System.out.println("\n[Belum ada akun aktif yang dipilih]");
            }

            System.out.println("Menu Utama:");
            System.out.println("1. Buka Rekening Baru");
            System.out.println("2. Setor Tunai");
            System.out.println("3. Tarik Tunai");
            System.out.println("4. Cek Informasi Rekening");
            System.out.println("5. Ganti Akun (Bonus)");
            System.out.println("6. Cetak Mutasi (Riwayat)");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            int pilihan = input.nextInt();
            input.nextLine(); // Membersihkan buffer enter

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan No Rekening: ");
                    String no = input.nextLine();
                    System.out.print("Masukkan Nama Pemilik: ");
                    String nama = input.nextLine();
                    System.out.print("Masukkan Saldo Awal: ");
                    double saldo = input.nextDouble();
                    input.nextLine(); // Membersihkan buffer

                    // Minta input PIN (String angka 6 digit)
                    System.out.print("Masukkan PIN (6 digit): ");
                    String pinBaru = input.nextLine();

                    Rekening akunBaru = new Rekening(no, nama, saldo, pinBaru);
                    if (saldo >= 50000) {
                        daftarRekening.add(akunBaru);
                        akunAktif = akunBaru;
                    }
                    break;

                case 2:
                    if (akunAktif == null) {
                        System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
                    } else if (akunAktif.isTerblokir()) {
                        System.out.println("Akses Ditolak: akun anda terblokir");
                    } else {
                        System.out.print("Masukkan nominal setor: ");
                        double setor = input.nextDouble();
                        akunAktif.setorTunai(setor);
                    }
                    break;

                case 3:
                    if (akunAktif == null) {
                        System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
                    } else if (akunAktif.isTerblokir()) {
                        System.out.println("Akses Ditolak: akun anda terblokir");
                    } else {
                        System.out.print("Masukkan PIN: ");
                        String pinInput = input.nextLine();

                        if (akunAktif.otentikasi(pinInput)) {
                            System.out.print("Masukkan nominal penarikan: ");
                            double tarik = input.nextDouble();
                            akunAktif.tarikTunai(tarik);
                        }
                    }
                    break;

                case 4:
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum membuka rekening!");
                    } else {
                        akunAktif.cekInformasi();
                    }
                    break;

                case 5:
                    System.out.print("Masukkan nomor rekening yang dicari: ");
                    String cariNo = input.nextLine();
                    boolean ditemukan = false;

                    for (Rekening rkn : daftarRekening) {
                        if (rkn.nomorRekening.equals(cariNo)) {
                            akunAktif = rkn;
                            System.out.println("Berhasil beralih ke akun atas nama: " + rkn.namaPemilik);
                            ditemukan = true;
                            break;
                        }
                    }

                    if (!ditemukan) {
                        System.out.println("Error: Nomor rekening tidak ditemukan di dalam sistem!");
                    }
                    break;

                case 6:
                    if (akunAktif == null) {
                        System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
                    } else if (akunAktif.isTerblokir()) {
                        System.out.println("Akses Ditolak: akun anda terblokir");
                    } else {
                        System.out.print("Masukkan PIN: ");
                        String pinInput = input.nextLine();

                        if (akunAktif.otentikasi(pinInput)) {
                            akunAktif.cetakMutasi();
                        }
                    }
                    break;

                case 0:
                    isRunning = false;
                    System.out.println("Sistem ditutup. Terima kasih!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        input.close();
    }
}