
package rmi.master;

import commandexecutor.RMICommandExecutor;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientNodeConnector extends UnicastRemoteObject implements RMIClientNodeConnector {

    
    //Use Map node1 node2 
    List<String> address;

    public ClientNodeConnector(List<String> address) throws RemoteException {
        this.address = address;
    }

    @Override
    public String forwardCommand(int nodeNr, String command) throws RemoteException {

        String rmiName = this.address.get(nodeNr);
        System.out.println("Connecting to: " + rmiName);
        RMICommandExecutor c = null;
        try {
            c = (RMICommandExecutor) Naming.lookup(rmiName);
            String result = c.execute(command);
            return result;

        } catch (NotBoundException ex) {
            Logger.getLogger(ClientNodeConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientNodeConnector.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Failed to connect to: " + rmiName;
    }

    @Override
    public int getNodeCount() throws RemoteException {
       return this.address.size();
    }

}
