import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MarketingEditorInterface extends Remote {
    String submitAdDetails(Advertisement ad) throws RemoteException;

    String updateAdStatus(int adId, String status) throws RemoteException;
}
