
package rmi.master;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface RMIClientNodeConnector extends Remote {

    String forwardCommand(int nodeNr, String command) throws RemoteException;

    int getNodeCount() throws RemoteException;



}
