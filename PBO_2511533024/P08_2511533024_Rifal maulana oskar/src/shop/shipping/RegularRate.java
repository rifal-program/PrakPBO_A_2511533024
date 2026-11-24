package shop.shipping;

public class RegularRate implements ShippingRate {
    private final long ratePerKg;

    public RegularRate(long ratePerKg) {
        this.ratePerKg = ratePerKg;
    }

    @Override
    public long costFor(int grams) {
        return grams == 0 ? 0 : ((grams + 999) / 1000) * ratePerKg;
    }
}
