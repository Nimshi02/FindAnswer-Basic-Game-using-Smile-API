package com.uob.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.uob.rmiinterface.TheInterface;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Implements the Service.
 *
 * @author Marc Conrad
 *
 */
public class TheImplementation extends UnicastRemoteObject implements TheInterface {

    protected TheImplementation() throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
    }
    Game newGame;
    private static final long serialVersionUID = -3763231206310448L;

    /*
        *This method is to call the API and get the URL. The question and the answer off the URL call 
        will be saved in Game class and it will be called by this method.
     */
    public URL getGame() throws RemoteException, IOException {

        URL url = new URL("https://www.sanfoh.com/uob/smile/api.php");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        newGame = mapper.readValue(responseStream, Game.class);
        return newGame.getQuestion();

    }

    /*
        * This method will get the solution to the URL
     */
    public int GetSolution() throws RemoteException {
        return newGame.getSolution();
    }

    /*
        This method will check the user enterd answer
     */
    public boolean ChekAnswer(int answer) throws RemoteException {
        if (answer == newGame.getSolution()) {
            return true;
        }
        return false;
    }

    /*
        This method will check ther users answer in third round.
     */
    public boolean check(int question1, int question2, int answer1, int answer2, int answer3, int answer4) throws RemoteException {
        int check;
        check = question1 * question2;
        if (check == answer1) {
            check = question1 + question2;
            if (check == answer2) {
                check = question1 / question2;
                if(question2==0&& answer3==0)
                {
                     check = question1 - question2;
                    if (check == answer4) {
                        return true;
                    }
                }
                if (Math.round(check) == answer3) {
                    check = question1 - question2;
                    if (check == answer4) {
                        return true;
                    }
                }
            }
        }
        return false;

    }
    /*
    *Create the database connection and save the data to the database.
    */
    
public void saveuser(String username,String email,String password) throws RemoteException, IOException, InterruptedException, ExecutionException, Exception
{
    Connection newcon=new Connection();
    newcon.saveDoc(username, email, password);
}
/*
*Create a database connection and check if the retrived user details are equeal to the users given credentials
*/
public boolean checkUser(String email,String password) throws Exception,RemoteException
{
    Connection newcon= new Connection();
    Boolean ans=newcon.retriveDoc(email, password);
    return ans;
}
}
