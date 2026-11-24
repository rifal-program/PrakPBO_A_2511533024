package shop.tantangan;

import java.util.List;
import shop.model.Address;
import shop.model.Order;
import shop.notify.NotificationChannel;
import shop.notify.SmsChannel;
import shop.notify.WhatsAppChannel;
import shop.service.Notifier;

public class TantanganB {
    public static void main(String[] args) {
        System.out.println("=== Demo Tantangan B: Notifikasi Multi Saluran ===");
        List<NotificationChannel> channels = List.of(new SmsChannel(), new WhatsAppChannel());
        Notifier multiNotifier = new Notifier(channels);

        Order order = new Order("ORD-MULTI-1", new Address("Rifal", "Padang"));
        multiNotifier.notifyPaid(order, 150_000);
    }
}
