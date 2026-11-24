package shop.tantangan;

import shop.discount.NoDiscount;
import shop.model.Address;
import shop.model.Order;
import shop.model.PhysicalProduct;
import shop.service.Bill;
import shop.service.OrderCalculator;
import shop.shipping.RegularRate;
import shop.shipping.SameDayRate;

public class TantanganA {
    public static void main(String[] args) {
        System.out.println("=== Demo Tantangan A: Same Day Shipping ===");
        PhysicalProduct barang2kg = new PhysicalProduct("BRG-2K", "Paket 2kg", 100_000, 2000);
        PhysicalProduct barang5kg = new PhysicalProduct("BRG-5K", "Paket 5kg", 200_000, 5000);

        Order order2kg = new Order("ORD-SD-1", new Address("Ali", "Jakarta")).add(barang2kg, 1);
        Order order5kg = new Order("ORD-SD-2", new Address("Budi", "Jakarta")).add(barang5kg, 1);

        OrderCalculator regularCalc = new OrderCalculator(new RegularRate(8000));
        OrderCalculator sameDayCalc = new OrderCalculator(new SameDayRate());

        Bill reg1 = regularCalc.calculate(order2kg, new NoDiscount());
        Bill sd1 = sameDayCalc.calculate(order2kg, new NoDiscount());
        System.out.println("Pesanan 2 kg:");
        System.out.println("  Ongkir Regular (Rp8.000/kg) : Rp" + reg1.shipping());
        System.out.println("  Ongkir Same Day (<= 3kg)    : Rp" + sd1.shipping());

        Bill reg2 = regularCalc.calculate(order5kg, new NoDiscount());
        Bill sd2 = sameDayCalc.calculate(order5kg, new NoDiscount());
        System.out.println("Pesanan 5 kg:");
        System.out.println("  Ongkir Regular (Rp8.000/kg) : Rp" + reg2.shipping());
        System.out.println("  Ongkir Same Day (3kg + 2kg) : Rp" + sd2.shipping());
    }
}
