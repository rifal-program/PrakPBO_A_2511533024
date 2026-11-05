package shop.payment;

/** Contract for every payment method. */
public interface PaymentMethod {
    String getName();

    /** @return true if the payment succeeds */
    boolean pay(long amount);

    /** Biaya admin per metode pembayaran (T1). Bawaan 0 rupiah. */
    default long adminFee(long amount) {
        return 0;
    }
}
