package shop.notify;

public class WhatsAppChannel implements NotificationChannel {
    @Override
    public void send(String recipient, String message) {
        System.out.println("[WA] " + recipient + ": " + message);
    }
}
