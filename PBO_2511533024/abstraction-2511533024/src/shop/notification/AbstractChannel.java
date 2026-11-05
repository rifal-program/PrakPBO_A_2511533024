package shop.notification;

public abstract class AbstractChannel implements NotificationChannel {
    private int sentCount = 0;
    private String lastDelivered;

    public int getSentCount() {
        return sentCount;
    }

    public String getLastDelivered() {
        return lastDelivered;
    }

    /**
     * Template method: alur pengiriman pesan bersifat final agar tidak diubah sembarangan oleh subclass.
     */
    @Override
    public final boolean send(CustomerContact contact, String message) {
        if (message == null || message.isBlank()) {
            return false;
        }
        if (contact == null) {
            return false;
        }

        String recipient = resolveRecipient(contact);
        if (recipient == null || recipient.isBlank()) {
            return false;
        }

        String preparedMessage = prepare(message);
        boolean delivered = deliver(recipient, preparedMessage);
        if (delivered) {
            sentCount++;
            lastDelivered = preparedMessage;
            return true;
        }
        return false;
    }

    /** Mencari dan memvalidasi alamat tujuan dari kontak pelanggan. */
    protected abstract String resolveRecipient(CustomerContact contact);

    /** Melakukan pengiriman aktual pesan ke alamat tujuan. */
    protected abstract boolean deliver(String recipient, String message);

    /** Menyiapkan atau memformat pesan (opsional di-override subclass). */
    protected String prepare(String message) {
        return message;
    }
}
