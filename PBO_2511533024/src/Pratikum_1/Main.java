package Pratikum_1;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // TANTANGAN MULTI-AKUN: Menggunakan ArrayList untuk menampung banyak rekening
        ArrayList<Rekening> daftarRekening = new ArrayList<>();
        Rekening akunAktif = null; 
        boolean isRunning = true;

        System.out.println("=== SISTEM PERBANKAN MINI ===");

        while (isRunning) {
            // Menampilkan informasi akun yang sedang digunakan saat ini
            if (akunAktif != null) {
                System.out.println("\n[Akun Aktif saat ini: " + akunAktif.namaPemilik + " (" + akunAktif.nomorRekening + ")]");
            } else {
                System.out.println("\n[Belum ada akun aktif yang dipilih]");
            }

            System.out.println("Menu Utama:");
            System.out.println("1. Buka Rekening Baru");
            System.out.println("2. Setor Tunai");
            System.out.println("3. Tarik Tunai");
            System.out.println("4. Cek Informasi Rekening");
            System.out.println("5. Ganti Akun (Bonus)");
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

                    // Membuat objek baru dan langsung memasukkannya ke dalam ArrayList
                    Rekening akunBaru = new Rekening(no, nama, saldo);
                    daftarRekening.add(akunBaru);
                    
                    // Otomatis menjadikan akun yang baru dibuat sebagai akun aktif
                    akunAktif = akunBaru;
                    break;

                case 2:
                    if (akunAktif == null) {
                        System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
                    } else {
                        System.out.print("Masukkan nominal setor: ");
                        double setor = input.nextDouble();
                        akunAktif.setorTunai(setor);
                    }
                    break;

                case 3:
                    // IMPLEMENTASI FITUR: Tarik Tunai
                    if (akunAktif == null) {
                        System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
                    } else {
                        System.out.print("Masukkan nominal penarikan: ");
                        double tarik = input.nextDouble();
                        akunAktif.tarikTunai(tarik); // Memanggil method tarikTunai yang baru dibuat
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
                    // BONUS: Fitur Ganti Akun
                    System.out.print("Masukkan nomor rekening yang dicari: ");
                    String cariNo = input.nextLine();
                    boolean ditemukan = false;

                    // Melakukan pencarian (looping) di dalam ArrayList
                    for (Rekening rkn : daftarRekening) {
                        if (rkn.nomorRekening.equals(cariNo)) {
                            akunAktif = rkn; // Mengganti akun aktif dengan objek yang cocok
                            System.out.println("Berhasil beralih ke akun atas nama: " + rkn.namaPemilik);
                            ditemukan = true;
                            break; // Hentikan pencarian jika sudah ketemu
                        }
                    }

                    if (!ditemukan) {
                        System.out.println("Error: Nomor rekening tidak ditemukan di dalam sistem!");
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
