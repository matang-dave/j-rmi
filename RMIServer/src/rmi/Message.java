package rmi;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author matang
 */

interface Message extends Remote {
    int login(String username,String password) throws IOException,RemoteException;
	int register(String username,String password) throws IOException,RemoteException;
    }



