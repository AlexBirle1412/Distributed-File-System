
package commandexecutor;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RMICommandExecutor extends Remote {
    
    String execute(String command) throws RemoteException; 
    
}
