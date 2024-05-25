import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

public class MarketingClient {
    MarketingClient() {
    }

    public static void main(String[] args) {
        System.out.println("Connected to MarketingClient");

    }

    public void sendSingleAdDetails() {
        try {
            System.out.println("Sending a single advertisement to the editor...".concat("\n"));
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            MarketingEditorInterface stub = (MarketingEditorInterface) registry.lookup("Editor");

            // Create a new advertisement
            Advertisement newAd = new Advertisement(
                    101, "Tech Innovations 2024: Discover the Future", "TechCorp", new Date(),
                    "Full-page", "Technology", 45);
            String response = stub.submitAdDetails(newAd);
            System.out.println("Response: " + response);

            // Update the status of an advertisement
            response = stub.updateAdStatus(101, "Approved");
            System.out.println("Response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void sendCollectionAdvertToEditor() {
        try {
            System.out.println("Sending multiple advertisements to the editor...".concat("\n"));
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            MarketingEditorInterface stub = (MarketingEditorInterface) registry.lookup("Editor");

            // Submit multiple advertisements
            for (int i = 1; i <= 10; i++) {
                Advertisement newAd = new Advertisement(
                        100 + i, "Tech Innovations 2024: Article " + i, "Company " + i, new Date(),
                        i % 2 == 0 ? "Half-page" : "Full-page", i % 2 == 0 ? "Technology" : "Innovation", 45);
                String response = stub.submitAdDetails(newAd);
                System.out.println("Response: " + response);

                // Update the status of each advertisement
                response = stub.updateAdStatus(100 + i, "Submitted");
                System.out.println("Response: " + response);
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    //// RabbitMQ
    public void sendAd(Advertisement ad) {
        try {
            String message = adToJson(ad);
            System.out.println("Messager sending to Consumer...".concat("\n"));
            Send.sendAd(message);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    private String adToJson(Advertisement ad) {
        return String.format(
                "{\"id\": \"%d\", \"details\": \"%s\", \"owner\": \"%s\", \"dueDate\": \"%s\", \"size\": \"%s\", \"category\": \"%s\", \"issueNumber\": \"%d\"}",
                ad.getAdId(), ad.getDetails(), ad.getOwner(), ad.getDueDate().toString(), ad.getSize(),
                ad.getCategory(), ad.getIssueNumber());
    }
}
