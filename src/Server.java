import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            EditorImpl obj = new EditorImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Editor", obj);
            System.out.println("Server ready - EditorImpl is registered and waiting for requests.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
