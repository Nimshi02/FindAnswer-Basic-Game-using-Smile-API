package com.uob.rmiinterface;

import java.io.IOException;
import java.net.URL;

/**
 * RMI interface for a basic service that return a greeting
 *
 * @author Marc Conrad
 *
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;

/**
 * RMI interface for a basic service that return a greeting
 *
 * @author Marc Conrad
 *
 */
public interface TheInterface extends Remote {

    /**
     * Says Hello
     *
     * @return
     * @throws RemoteException
     */
    public URL getGame() throws RemoteException, IOException;

    public int GetSolution() throws RemoteException;

    public boolean ChekAnswer(int answer) throws RemoteException;

    public boolean check(int question1, int question2, int answer1, int answer2, int answer3, int answer4) throws RemoteException;

    public void saveuser(String username, String email, String password) throws RemoteException, IOException, InterruptedException, ExecutionException, Exception;

    public boolean checkUser(String email, String password) throws Exception, RemoteException;

}
