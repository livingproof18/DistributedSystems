import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Greet the user and ask for their department
        System.out.println("Hello, Welcome to IT in the Valley information system!");
        System.out.println("What can I help with? (type 'marketing' to proceed)");
        String department = scanner.nextLine().trim().toLowerCase();

        boolean userInput = true;
        while (userInput) {
            // Check if the user input is for the marketing department
            if (department.equals("marketing")) {
                System.out.println(
                        "Would you like to send a 'single' ad or a 'batch' collection of advertisements to the editor?");
                String adType = scanner.nextLine().trim().toLowerCase();

                // Decide which function to call based on user input
                if (adType.equals("single")) {
                    sendSingleAdvertToEditor();
                } else if (adType.equals("batch")) {
                    sendCollectionAdvertToEditor();
                } else if (adType.equals("exit")) {
                    userInput = false;
                    System.out.println("Goodbye!");
                } else {
                    System.out.println("Invalid input. Please enter 'single' or 'batch'.");
                }

            } else {
                System.out.println("Sorry, I can only assist with marketing tasks right now.");
            }
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
