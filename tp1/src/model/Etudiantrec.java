package model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Etudiantrec extends Thread{
	DatagramSocket Sc;

	public  Etudiantrec(DatagramSocket Sc) {
		this.Sc=Sc;
	}
	public void run() {
		while(true) {
		 DatagramPacket pk = new DatagramPacket(new byte[1024],1024);
		 try {
		 Sc.receive(pk);
		 String msg = new String(pk.getData(),0,pk.getLength());
		 System.out.println(msg);
		 } catch (IOException e) {
				e.printStackTrace();
			}
		}
		}
	}



