package model;

import java.net.DatagramSocket;
import java.net.SocketException;

public class etudianttest {
	public static void main(String[] args)  {
	 try {
		DatagramSocket Sc=new  DatagramSocket(5028);
		Etudiantsnd send=new  Etudiantsnd (Sc);
		Etudiantrec rec=new Etudiantrec(Sc);
	 
		send.start();
	    rec.start();
	     
	}catch (SocketException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();}
	}}
