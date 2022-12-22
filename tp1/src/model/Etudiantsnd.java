package model;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetAddress;

public class Etudiantsnd extends Thread {
	DatagramSocket Sc;

	public Etudiantsnd(DatagramSocket sc) {

		this.Sc = sc;
	}

	int portserv = 5028;

	public Etudiantsnd() {

	}

	public void run() {

		BufferedReader inn = new BufferedReader(new InputStreamReader(System.in));

		try {
			while (true) {
				String Server = "127.0.0.1";
				InetAddress ipserver = InetAddress.getByName(Server);

				String msg = inn.readLine();
				DatagramPacket p = new DatagramPacket(msg.getBytes(), msg.length(), ipserver, 5852);
				Sc.send(p);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
