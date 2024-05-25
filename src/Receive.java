import com.rabbitmq.client.*;

public class Receive {
    // private final static String QUEUE_NAME = "hello";
    private final static String QUEUE_NAME = "ads_queue";

    public static void main(String[] argv) throws Exception {
        System.out.println("Receiving advertisements from the marketing department...");
        receiveAds();
    }

    public static void receiveAds() throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setAutomaticRecoveryEnabled(true); // Enable connection recovery
        factory.setNetworkRecoveryInterval(10000); // Set recovery interval to 10 seconds

        while (true) {
            try (Connection connection = factory.newConnection();
                    Channel channel = connection.createChannel()) {
                channel.queueDeclare(QUEUE_NAME, true, false, false, null); // Make the queue durable
                // System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String message = new String(delivery.getBody(), "UTF-8");
                    try {
                        Advertisement ad = EditorImpl.jsonToAd(message);
                        EditorImpl.processAd(ad);
                        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    } catch (Exception e) {
                        System.err.println("Failed to process message: " + e.getMessage());
                        channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, true);
                    }
                };
                channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> {
                });
            } catch (Exception e) {
                System.err.println("Connection to RabbitMQ failed. Attempting to reconnect...");
                try {
                    Thread.sleep(10000); // Wait before trying to reconnect
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        // Connection connection = factory.newConnection();
        // Channel channel = connection.createChannel();

        // channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        // DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        // String message = new String(delivery.getBody(), "UTF-8");
        // System.out.println(" [x] Received '" + message + "'");
        // try {
        // Advertisement ad = EditorImpl.jsonToAd(message);
        // EditorImpl.processAd(ad);
        // channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        // } catch (Exception e) {
        // System.err.println("Failed to process message: " + e.getMessage());
        // channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, true);
        // }
        // };
        // channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> {
        // });

    }

    //
    //
    //
    //
}
