import java.util.Date;

/**
 * Represents an advertisement in the "IT in the Valley" magazine system.
 * Each advertisement captures details required for both the editorial
 * and accounting purposes.
 */
public class Advertisement implements java.io.Serializable {
    private int adId; // Unique identifier for the advertisement.
    private String details; // The content of the advertisement.
    private String owner; // The advertiser responsible for the advertisement.
    private Date dueDate; // The publication date for the advertisement.
    private String status; // Current status of the advertisement, e.g., "Pending", "Approved".
    private String size; // Size of the advertisement, which could be dimensions or page percentage.
    private String category; // The section or category in the magazine where the ad should appear.
    private boolean isArchived; // Indicates if the advertisement is archived.
    private int issueNumber; // The issue number of the magazine in which this ad is placed.
    private boolean isBilled; // Tracks whether the advertiser has been invoiced for this ad.

    public Advertisement(int adId, String details, String owner, Date dueDate, String size, String category,
            int issueNumber) {
        this.adId = adId;
        this.details = details;
        this.owner = owner;
        this.dueDate = dueDate;
        this.status = "Pending";
        this.size = size;
        this.category = category;
        this.isArchived = false;
        this.issueNumber = issueNumber;
        this.isBilled = false;
    }

    // Getters and setters
    public int getAdId() {
        return adId;
    }

    public String getDetails() {
        return details;
    }

    public String getOwner() {
        return owner;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean isArchived) {
        this.isArchived = isArchived;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public boolean isBilled() {
        return isBilled;
    }

    public void setBilled(boolean isBilled) {
        this.isBilled = isBilled;
    }
}
