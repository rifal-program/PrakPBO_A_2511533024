package shop.shipping;

public class SameDayRate implements ShippingRate {
    @Override
    public long costFor(int grams) {
        if (grams <= 0) {
            return 0;
        }
        if (grams <= 3000) {
            return 25_000;
        }
        long extraKg = (grams - 3000 + 999) / 1000;
        return 25_000 + extraKg * 10_000;
    }
}
