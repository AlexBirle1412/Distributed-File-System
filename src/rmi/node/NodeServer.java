package rmi.node;

import commandexecutor.CLIExecutor;
import commandexecutor.RMICommandExecutor;
import fileexplorer.FileExplorerImpl;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class NodeServer {

    public static void main(String args[]) throws RemoteException, MalformedURLException {
        if (args.length < 1) {
            throw new IllegalArgumentException("Missing arguments");
        }
        String host = args[0];
        String port = args[1];

        LocateRegistry.createRegistry(Integer.parseInt(port));
        RMICommandExecutor commandExecutor = new CLIExecutor(new FileExplorerImpl());
        String address = "rmi://" + host + ":" + port + "/fileExplorer";
        Naming.rebind(address, commandExecutor);
        System.out.println("Successfuly Started on address: " + address);
    }
}
