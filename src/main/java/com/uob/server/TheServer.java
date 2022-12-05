package com.uob.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.uob.rmiinterface.TheInterface;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * RMI method
 * @author Nimshi
 *
 */
public class TheServer {
public static Connection newcon;
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		System.out.println("Attempting to start the Hello Server..."); 
		try {
			TheInterface impl = new TheImplementation();
			Registry reg = LocateRegistry.createRegistry(1099);
			reg.rebind("HelloService2021",impl);

			System.out.println("Service started. Game Engine is started!");

		} catch (RemoteException e) {
			System.out.println("An error occured: "+e.toString()); 
			System.out.println("There is a problem in starting the server");
		} 
                /*
                *Create the connection with the database when the server starts. In server side. Then do not need to create connection each and every time a client 
                *need to interact with the database. Hence wil not the error that database exist. It is initialized as static
                *because it sholud be used in TheImplementetion class. 
                */
                newcon= new Connection();
                

	
        }

}


