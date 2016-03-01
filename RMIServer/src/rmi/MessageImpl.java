/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author matang
 */
 
public class MessageImpl extends UnicastRemoteObject implements Message {

    public MessageImpl() throws RemoteException {
        
    }

    //login user
    public int login(String userName, String password) throws RemoteException, IOException {

        BufferedReader br = new BufferedReader(new FileReader("db.txt"));
        String adminUserName = "admin";
        String line = null;

        line = br.readLine();

        while (line != null) {
            String[] explode = line.split(",");
            
            if (explode[0].equals(userName)) {  
                br.close();
                if (explode[1].equals(password)) {
                    if (explode[0].equals(adminUserName)) { // if user is admin get total registered user counts
                        return this.getTotalUserCount();
                    } else {
                        return 1;
                    }
                } else {
                    return 0;
                }
            }
            line = br.readLine();
        }
        return 0;
    }

    //register new user
    public int register(String userName, String password) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("db.txt"));
        StringBuilder sb = new StringBuilder();
        String line = null;

        line = br.readLine();

        while (line != null) {
            String[] explode = line.split(",");
            if (explode[0].equals(userName)) {  //username already exists
                br.close();
                return 0;
            }
            line = br.readLine();
        }

        //append user credentials to the file
        Writer output;
        output = new BufferedWriter(new FileWriter("db.txt", true)); 
        output.append(System.getProperty("line.separator"));
        output.append(userName + ',' + password);
        output.close();

        return 1;

    }


    // get total user counts in the file    
    private int getTotalUserCount() throws IOException {
        int count = 0;
        BufferedReader br = new BufferedReader(new FileReader("db.txt"));
        StringBuilder sb = new StringBuilder();
        String line = null;
        line = br.readLine();

        while (line != null) {
            line = br.readLine();
            count++;
        }
        br.close();
        return count;
    }
}
