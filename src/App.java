import java.util.Scanner;
import java.util.Date;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello, Welcome to IT in the Valley information system!");
        System.out.println("What can I help with? (type 'marketing' to proceed)");
        String department = scanner.nextLine().trim().toLowerCase();

        while (!department.equals("exit")) {
            if (department.equals("marketing")) {
                System.out.println("Choose 'single', 'batch' for RMI, or 'async' for RabbitMQ:");
                String adType = scanner.nextLine().trim().toLowerCase();

                switch (adType) {
                    case "single":
                        sendSingleAdvertToEditor();
                        break;
                    case "batch":
                        sendCollectionAdvertToEditor();
                        break;
                    case "async":
                        MarketingClient client = new MarketingClient();
                        // Assuming you have a method to create an ad or get it from somewhere
                        Advertisement ad = new Advertisement(102, "Async Ad Example", "AsyncCorp", new Date(),
                                "Half-page", "Science", 46);
                        client.sendAd(ad);
                        break;
                    case "exit":
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid input. Please enter 'single', 'batch', or 'async'.");
                        break;
                }
            } else {
                System.out.println("Invalid department. Try again or type 'exit' to leave.");
            }
            department = scanner.nextLine().trim().toLowerCase();
        }
        scanner.close();
    }

    // Run this method to send a single advertisement to the editor
    public static void sendSingleAdvertToEditor() {
        MarketingClient marketingClient = new MarketingClient();
        marketingClient.sendSingleAdDetails();
        System.out.println("Single advertisement sent to the editor.");

    }

    // Run this method to send multiple advertisements to the editor
    public static void sendCollectionAdvertToEditor() {
        MarketingClient marketingClient = new MarketingClient();
        marketingClient.sendCollectionAdvertToEditor();
        System.out.println("Collection of advertisements sent to the editor.");
    }
}
