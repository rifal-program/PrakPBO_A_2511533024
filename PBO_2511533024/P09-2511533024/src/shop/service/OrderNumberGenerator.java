package shop.service;

public class OrderNumberGenerator {
    // 1. Field static untuk menyimpan satu-satunya instance
    private static OrderNumberGenerator instance;

    private int counter = 1000;

    // 2. Constructor private agar tidak bisa di-new dari kelas lain
    private OrderNumberGenerator() {
    }

    // 3. Method public static untuk mengambil instance yang sama
    public static OrderNumberGenerator getInstance() {
        if (instance == null) {
            instance = new OrderNumberGenerator();
        }
        return instance;
    }

    public String next() {
        counter++;
        return "INV-" + counter;
    }
}