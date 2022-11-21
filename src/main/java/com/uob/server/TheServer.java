package com.uob.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.uob.rmiinterface.TheInterface;

/**
 * Basic RMI Server
 * @author Marc Conrad
 *
 */
public class TheServer {

	public static void main(String[] args) {
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

	
        }
}


