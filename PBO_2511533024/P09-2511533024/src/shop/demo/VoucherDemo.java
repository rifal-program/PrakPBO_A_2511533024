package shop.demo;

import shop.model.Voucher;
import shop.service.Rupiah;

/** Demo pembuatan Voucher sah dan tidak sah (Tantangan). */
public class VoucherDemo {
    public static void main(String[] args) {
        System.out.println("=== Demo Tantangan Voucher ===");

        // 1. Voucher Sah
        try {
            Voucher v1 = new Voucher.Builder("HEMAT20", 20)
                    .minimumPurchase(50_000)
                    .description("Diskon 20% pembelian kopi")
                    .build();
            System.out.println("Voucher sah berhasil dibuat: " + v1.getCode()
                    + " (Diskon " + v1.getPercent() + "%, Min. Belanja "
                    + Rupiah.format(v1.getMinimumPurchase()) + ", Ket: " + v1.getDescription() + ")");
        } catch (IllegalStateException e) {
            System.out.println("Gagal: " + e.getMessage());
        }

        // 2. Voucher Tidak Sah (persentase > 50)
        try {
            System.out.println("Mencoba membuat voucher dengan diskon 70%...");
            Voucher v2 = new Voucher.Builder("DISKONBESAR", 70)
                    .build();
            System.out.println("Voucher berhasil dibuat (seharusnya gagal): " + v2.getCode());
        } catch (IllegalStateException e) {
            System.out.println("Voucher tidak sah ditolak -> " + e.getMessage());
        }
    }
}
