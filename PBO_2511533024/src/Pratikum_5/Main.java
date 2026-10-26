package Pratikum_1;

import java.util.ArrayList;
import java.util.Scanner;
import Pratikum_4.RekeningTabungan;
import Pratikum_4.RekeningGiro;

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
                System.out.println("\n[Akun Aktif saat ini: " + akunAktif.getNamaPemilik() + " (" + akunAktif.getNomorRekening() + ")" + status + "]");
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
            System.out.println("7. Simulasi Akhir Bulan (Khusus Tabungan)");
            System.out.println("8. Transfer ke Rekening Lain");
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

                    System.out.print("Masukkan PIN (6 digit): ");
                    String pinBaru = input.nextLine();

                    // Tugas 1: Opsi Produk Multi-Produk
                    System.out.println("Pilih Produk: 1. Tabungan Umum | 2. Giro Bisnis");
                    System.out.print("Pilihan Produk: ");
                    int jenisProduk = input.nextInt();
                    input.nextLine(); // Membersihkan buffer

                    Rekening akunBaru = null;

                    if (jenisProduk == 1) {
                        System.out.print("Masukkan Suku Bunga (%): ");
                        double sukuBunga = input.nextDouble();
                        input.nextLine(); // Membersihkan buffer
                        
                        akunBaru = new RekeningTabungan(no, nama, saldo, pinBaru, sukuBunga);
                    } else if (jenisProduk == 2) {
                        System.out.print("Masukkan Batas Overdraft: ");
                        double batasOverdraft = input.nextDouble();
                        input.nextLine(); // Membersihkan buffer
                        
                        akunBaru = new RekeningGiro(no, nama, saldo, pinBaru, batasOverdraft);
                    } else {
                        System.out.println("Pilihan produk tidak valid!");
                        break;
                    }

                    if (saldo >= 50000) {
                        // Upcasting: Subclass disimpan sebagai Rekening
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
                        input.nextLine(); // Membersihkan buffer
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
                            input.nextLine(); // Membersihkan buffer
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
                        if (rkn.getNomorRekening().equals(cariNo)) {
                            akunAktif = rkn;
                            System.out.println("Berhasil beralih ke akun atas nama: " + rkn.getNamaPemilik());
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

                // Tugas 2: Simulasi Akhir Bulan
                case 7:
                    if (akunAktif == null) {
                        System.out.println("Error: Belum ada akun aktif yang dipilih!");
                    } else if (akunAktif instanceof RekeningTabungan) {
                        // Downcasting ke RekeningTabungan
                        RekeningTabungan tabungan = (RekeningTabungan) akunAktif;
                        tabungan.tambahBungaAkhirBulan();
                    } else {
                        System.out.println("Gagal: Fitur bunga akhir bulan hanya berlaku untuk Rekening Tabungan.");
                    }
                    break;

                // Tugas: Transfer Antar Rekening
                case 8:
                    if (akunAktif == null) {
                        System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
                    } else if (akunAktif.isTerblokir()) {
                        System.out.println("Akses Ditolak: akun anda terblokir");
                    } else {
                        System.out.print("Masukkan Nomor Rekening Tujuan: ");
                        String noTujuan = input.nextLine();

                        Rekening akunTujuan = null;
                        for (Rekening rkn : daftarRekening) {
                            if (rkn.getNomorRekening().equals(noTujuan)) {
                                akunTujuan = rkn;
                                break;
                            }
                        }

                        if (akunTujuan == null) {
                            System.out.println("Error: Nomor rekening tujuan tidak ditemukan di dalam sistem!");
                        } else if (akunTujuan == akunAktif) {
                            System.out.println("Error: Tidak dapat transfer ke rekening sendiri!");
                        } else {
                            System.out.print("Masukkan nominal transfer: ");
                            double nominalTransfer = input.nextDouble();
                            input.nextLine(); // Membersihkan buffer
                            System.out.print("Masukkan PIN: ");
                            String pinTransfer = input.nextLine();

                            // Polimorfisme: akunTujuan bisa Tabungan atau Giro
                            akunAktif.transfer(akunTujuan, nominalTransfer, pinTransfer);
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