package shop.discount;

/** Aturan diskon yang bisa ditukar. (Nama "Strategy" akan dibahas di P15.) */
public interface DiscountStrategy {
    /** Besar potongan (rupiah) untuk subtotal tertentu. */
    long discountFor(long subtotal);
}
