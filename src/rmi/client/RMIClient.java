package rmi.client;

import rmi.master.RMIClientNodeConnector;
import commandexecutor.RMICommandExecutor;
import fileexplorer.FileExplorer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RMIClient {
    
 public static final String ANSI_RESET = "\u001B[0m";
public static final String ANSI_BLACK = "\u001B[30m";
public static final String ANSI_RED = "\u001B[31m";
public static final String ANSI_GREEN = "\u001B[32m";
public static final String ANSI_YELLOW = "\u001B[33m";
public static final String ANSI_BLUE = "\u001B[34m";
public static final String ANSI_PURPLE = "\u001B[35m";
public static final String ANSI_CYAN = "\u001B[36m";
public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) throws IOException, NotBoundException {
        
        String rmiName = "rmi://localhost:8999/master";

        System.out.println("Connecting to: " + rmiName);
        RMIClientNodeConnector c = (RMIClientNodeConnector) Naming.lookup(rmiName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.err.print("Select Node:");
        int nodeIndex = Integer.parseInt(reader.readLine());
        while(true){
            
            System.out.println(ANSI_GREEN + "COMMNAD!" + ANSI_RESET);
            String command=reader.readLine();
            
            if("stop".equals(command)){
             System.err.print("Select Node:");
             nodeIndex = Integer.parseInt(reader.readLine());
            }
            System.out.println(c.forwardCommand(nodeIndex, command));
        }

    }

}
