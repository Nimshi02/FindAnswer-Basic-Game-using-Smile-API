package com.uob.server;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.uob.server.Player;
import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * This class is for connecting to the database and performing its operations.
 *
 * @author acer
 * @version 1.0.0
 */
public class Connection {   

    Firestore db;//Constructor for initialize the db
    String usesEmail;
    public Connection() throws IOException, InterruptedException, ExecutionException {

        
        
        FileInputStream serviceAccount = new FileInputStream("D:\\Eclips\\CIS_GAME\\src\\main\\java\\com\\uob\\rmiinterface\\ServiceAccount.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        FirebaseApp.initializeApp(options);

        db = FirestoreClient.getFirestore();
    }

    /*
     *This method is to save the data to the firestore database
     */
    public void saveDoc(String username,String email,String password) throws RemoteException,Exception {
        Player newUser=new Player(username,email,password);
        System.out.println(newUser.getUsername());
        DocumentReference docRef = db.collection("User").document(newUser.getEmail());
        Map<String, Object> data = new HashMap<>();
        data.put("Username", newUser.getUsername());
        data.put("Email", newUser.getEmail());
        data.put("Password", newUser.getPassword());

        ApiFuture<WriteResult> result = docRef.set(data);
        System.out.println("Update time : " + result.get().getUpdateTime());
    }
/*
    *Retrive the value from the database.
    */
    public boolean retriveDoc(String username,String password) throws Exception {
        ApiFuture<QuerySnapshot> query = db.collection("User").get();
// ...
// query.get() blocks on response
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            String dbUser=document.getId();
            
            String dbUserPassword=document.getString("Password");
            if(username.equals(dbUser)&& password.equals(dbUserPassword))
            {
                usesEmail=dbUser;
                return true;
                
            }
        }
        return false;

    }
    public void saveScore(String email,String score,String time) throws InterruptedException, ExecutionException
    {
        DocumentReference docRef = db.collection("User_Score").document(email);
        Map<String, Object> data = new HashMap<>();
        data.put("Email", email);
        data.put("Score", score);
        data.put("Time", time);

        ApiFuture<WriteResult> result = docRef.set(data);
        System.out.println("Update time : " + result.get().getUpdateTime());
    }
    public String[][] RetriveScore() throws InterruptedException, ExecutionException
    {
       ApiFuture<QuerySnapshot> query = db.collection("User_Score").orderBy("Score").limitToLast(5).get();
// ...
// query.get() blocks on response
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        int i=0;
        String[][] score=new String[5][2];
        for (QueryDocumentSnapshot document : documents) {
            String dbUser=document.getId();
            score[i][0]=document.getId();
            score[i][1]=document.getString("Score");
            i++;
        }
        return score;
        }
    public String[][] RetriveTime() throws InterruptedException, ExecutionException
    {
       ApiFuture<QuerySnapshot> query = db.collection("User_Score").orderBy("Time").get();
// ...
// query.get() blocks on response
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        int i=0;
        String[][] score=new String[5][2];
        for (QueryDocumentSnapshot document : documents) {
            String uscore=document.getString("Score");
            System.out.print(document.getString("Time"));
            System.out.println(uscore);
            int userscore=Integer.parseInt(uscore);
            if(userscore>=150){
                if(i<5){
            String dbUser=document.getId();
            score[i][0]=document.getId();
            score[i][1]=document.getString("Time");
            i++;}
                else
                {
                    return score;
                }
            }
        }
        return score;
        }
    public static void main(String[]args) throws InterruptedException, IOException, ExecutionException
    {
        Connection newcon=new Connection();
        //newcon.saveScore("kamalinie", "21", "21");
        String [][] score=new String[5][2];
        score=newcon.RetriveTime();
        for(int i=0;i<5;i++)
   {
        System.out.println(score[i][0]+": "+score[i][1]);
     }
        
    }
    public String getUsesEmail()
    {
        return usesEmail;
    }

}
