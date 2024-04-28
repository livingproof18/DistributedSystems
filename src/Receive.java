import com.rabbitmq.client.*;

public class Receive {
    // private final static String QUEUE_NAME = "hello";
    private final static String QUEUE_NAME = "ads_queue";

    public interface MessageHandler {
        void handleMessage(String message);
    }

    public static void main(String[] argv) throws Exception {
        // receiveAds();
    }

    public static void receiveAds(MessageHandler handler) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
                Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                handler.handleMessage(message);
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
            });
        }
    }

}
