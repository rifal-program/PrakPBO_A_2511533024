package shop.service;

import shop.discount.DiscountStrategy;
import shop.model.Order;
import shop.model.Shippable;
import shop.shipping.RegularRate;
import shop.shipping.ShippingRate;

public class OrderCalculator {
    private final ShippingRate shippingRate;

    public OrderCalculator(long ratePerKg) {
        this(new RegularRate(ratePerKg));
    }

    public OrderCalculator(ShippingRate shippingRate) {
        this.shippingRate = shippingRate;
    }

    public Bill calculate(Order order, DiscountStrategy discountStrategy) {
        long subtotal = 0;
        int grams = 0;
        for (Order.Line line : order.lines()) {
            subtotal += line.total();
            if (line.product() instanceof Shippable item) {
                grams += item.getWeightGram() * line.qty();
            }
        }

        long discount = discountStrategy.discountFor(subtotal);
        long shipping = shippingRate.costFor(grams);
        long total = subtotal - discount + shipping;

        return new Bill(subtotal, discount, shipping, total);
    }
}
