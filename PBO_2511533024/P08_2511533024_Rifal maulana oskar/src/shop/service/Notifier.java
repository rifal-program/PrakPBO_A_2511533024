package shop.service;

import java.util.List;
import shop.model.Order;
import shop.notify.NotificationChannel;
import shop.notify.SmsChannel;
import shop.util.Rupiah;

public class Notifier {
    private final List<NotificationChannel> channels;

    public Notifier() {
        this(List.of(new SmsChannel()));
    }

    public Notifier(List<NotificationChannel> channels) {
        this.channels = List.copyOf(channels);
    }

    public void notifyPaid(Order order, long amount) {
        String msg = "pesanan " + order.getOrderId() + " sudah dibayar (" + Rupiah.format(amount) + ")";
        String recipient = order.getAddress().getRecipient();
        for (NotificationChannel channel : channels) {
            channel.send(recipient, msg);
        }
    }
}
