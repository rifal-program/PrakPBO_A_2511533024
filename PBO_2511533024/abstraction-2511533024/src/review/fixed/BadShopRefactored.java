package review.fixed;

import java.util.ArrayList;
import java.util.List;

/*
 * T3 - CODE REVIEW (REFACTORED)
 * Perbaikan masalah desain dari review.BadShop:
 * 1. Interface Segregation: Memecah StoreOperations menjadi PaymentMethod dan BalanceInquiry.
 *    GiftVoucher tidak lagi dipaksa mengimplementasikan ship() atau refund() yang melempar exception.
 * 2. Polymorphism over Conditionals: Mengganti switch-case string pada Courier dengan abstract class
 *    dan subclass konkret (ExpressCourier, RegularCourier), menyelesaikan bug ongkir 0 akibat typo "EXPRES".
 * 3. Immutability: Mengubah Address menjadi class final dengan field private final dan tanpa setter,
 *    sehingga perubahan alamat pada order kedua tidak mengubah alamat order pertama.
 * 4. Static Nested Class: Mengubah Cart.Item menjadi static class Item sehingga tidak menyimpan
 *    referensi tersembunyi ke objek Cart luar.
 * 5. Open-Closed Principle pada Discount: Interface Discount mendefinisikan abstract method apply(long price),
 *    menghilangkan ketergantungan buruk interface pada instanceof implementasinya.
 * 6. Tipe Data Uang: Menggunakan long untuk saldo mata uang Rupiah pada GiftVoucher, bukan double.
 */

// 1. Interface Segregation
interface PaymentMethod {
    boolean pay(long amount);
}

interface BalanceInquiry {
    long getRemainingBalance();
}

class GiftVoucher implements PaymentMethod, BalanceInquiry {
    private long balance = 100_000L;

    @Override
    public boolean pay(long amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    @Override
    public long getRemainingBalance() {
        return balance;
    }
}

// 2. Courier dengan Abstraction & Polymorphism
abstract class Courier {
    public abstract String getName();
    public abstract long ratePerKg();

    public long cost(int kilograms) {
        if (kilograms <= 0) {
            return 0;
        }
        return kilograms * ratePerKg();
    }
}

class RegularCourier extends Courier {
    @Override
    public String getName() {
        return "Reguler";
    }

    @Override
    public long ratePerKg() {
        return 10_000L;
    }
}

class ExpressCourier extends Courier {
    @Override
    public String getName() {
        return "Ekspres";
    }

    @Override
    public long ratePerKg() {
        return 18_000L;
    }
}

// 4. Cart dengan Static Nested Class Item
class Cart {
    private final List<Item> items = new ArrayList<>();

    public static class Item {
        private final String name;
        private final long price;
        private final int quantity;

        public Item(String name, long price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public long subtotal() {
            return price * quantity;
        }

        public String getName() {
            return name;
        }
    }

    public void add(Item item) {
        if (item != null) {
            items.add(item);
        }
    }

    public long total() {
        long total = 0;
        for (Item item : items) {
            total += item.subtotal();
        }
        return total;
    }
}

// 3. Address Immutable
final class Address {
    private final String street;
    private final String city;

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return street + ", " + city;
    }
}

class SimpleOrder {
    private final String id;
    private final Address address;

    public SimpleOrder(String id, Address address) {
        this.id = id;
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return id + " -> " + address;
    }
}

// 5. Interface Discount dengan Polimorfisme sejati
interface Discount {
    long apply(long price);
}

class MemberDiscount implements Discount {
    @Override
    public long apply(long price) {
        return price - price * 5 / 100;
    }
}

class FlashSaleDiscount implements Discount {
    @Override
    public long apply(long price) {
        return price - price * 30 / 100;
    }
}

public class BadShopRefactored {
    public static void main(String[] args) {
        Cart cart = new Cart();
        cart.add(new Cart.Item("Kopi Gayo", 85_000, 2));
        System.out.println("Total cart: " + cart.total());

        Courier courier = new ExpressCourier();
        System.out.println("Ongkir " + courier.getName() + " 2 kg: " + courier.cost(2));

        Address home = new Address("Jl. Sudirman 10", "Padang");
        Address secondAddress = new Address("Jl. Sudirman 10", "Bukittinggi");
        SimpleOrder first = new SimpleOrder("ORD-1", home);
        SimpleOrder second = new SimpleOrder("ORD-2", secondAddress);
        System.out.println(first);
        System.out.println(second);

        Discount flashSale = new FlashSaleDiscount();
        System.out.println("Harga flash sale: " + flashSale.apply(100_000));

        GiftVoucher voucher = new GiftVoucher();
        System.out.println(voucher.pay(30_000) + " | sisa Rp" + voucher.getRemainingBalance());
    }
}
