/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.master;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class MasterClass {

    public static void main(String[] args) throws NotBoundException, IOException {
        List<String> addressList=new ArrayList<>();
        addressList.add("rmi://localhost:9000/fileExplorer");
        addressList.add("rmi://localhost:9001/fileExplorer");

        try {

            LocateRegistry.createRegistry(8999);
            RMIClientNodeConnector commandExecutor = new ClientNodeConnector(addressList);
            Naming.rebind("rmi://localhost:8999/master", commandExecutor);
            System.out.println("MasterClass ruleaza ok");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
