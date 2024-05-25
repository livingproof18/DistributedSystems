import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {
    // private final static String QUEUE_NAME = "hello";
    private final static String QUEUE_NAME = "ads_queue";

    public static void main(String[] argv) throws Exception {

    }

    public static void sendAd(String message) throws Exception {
        System.out.println("Sending an advertisement to the editor...".concat("\n"));
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
                Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
