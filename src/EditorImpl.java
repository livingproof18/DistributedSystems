import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Date;

public class EditorImpl extends UnicastRemoteObject implements MarketingEditorInterface {
    // private HashMap<Integer, Advertisement> ads;
    private static HashMap<Integer, Advertisement> ads = new HashMap<>();

    protected EditorImpl() throws RemoteException {
        super();
        ads = new HashMap<>();
    }

    @Override
    // Accepts advertisement details as input and returns a response string
    // indicating the result of the submission.
    public String submitAdDetails(Advertisement ad) throws RemoteException {
        ads.put(ad.getAdId(), ad);
        System.out.println("Received ad details: " + ad.getDetails() + " from " + ad.getOwner() +
                ", scheduled for issue " + ad.getIssueNumber() + " in " + ad.getCategory() +
                " category, size: " + ad.getSize());
        return "Ad details accepted and processed for " + ad.getOwner() + ", ad size: " + ad.getSize();
        // // Send Advert to the processing centre

    }

    @Override
    public String updateAdStatus(int adId, String status) throws RemoteException {
        if (ads.containsKey(adId)) {
            Advertisement ad = ads.get(adId);
            ad.setStatus(status);
            System.out.println("Ad status for ID " + adId + " updated to " + status);
            return "Ad status updated to " + status;
        }
        return "Ad not found";
    }

    // RabbitMQ
    public static void processAd(Advertisement ad) {
        ads.put(ad.getAdId(), ad);
        System.out.println("Processed ad: " + ad.getDetails() + " from " + ad.getOwner() +
                ", scheduled for issue " + ad.getIssueNumber() + " in " + ad.getCategory() +
                " category, size: " + ad.getSize());

        // Send Advert to the processing centre
    }

    public static Advertisement jsonToAd(String json) {
        // Simple JSON parsing for demonstration
        // Assuming json is in the format: {"id": "101", "details": "Tech Innovations
        // 2024: Discover the Future", ...}
        Map<String, String> map = Arrays.stream(json.replace("{", "").replace("}", "").split(","))
                .map(e -> e.split(":"))
                .collect(Collectors.toMap(a -> a[0].trim().replace("\"", ""), a -> a[1].trim().replace("\"", "")));

        return new Advertisement(
                Integer.parseInt(map.get("id")),
                map.get("details"),
                map.get("owner"),
                new Date(), // Parsing the date from String would require a formatter, simplified here
                map.get("size"),
                map.get("category"),
                Integer.parseInt(map.get("issueNumber")));
    }

    public static Advertisement jsonToAd2(String json) {
        Map<String, String> map = Arrays.stream(json.replace("{", "").replace("}", "").split(","))
                .map(e -> e.split(":"))
                .collect(Collectors.toMap(a -> a[0].trim().replace("\"", ""), a -> a[1].trim().replace("\"", "")));

        return new Advertisement(
                Integer.parseInt(map.get("id")),
                map.get("details"),
                map.get("owner"),
                new Date(), // Simplification; ideally use a real date parsing
                map.get("size"),
                map.get("category"),
                Integer.parseInt(map.get("issueNumber")));
    }
}