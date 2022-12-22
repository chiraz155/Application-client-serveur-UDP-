package model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Serverr {

	static List<Etudiant> list_etu_conn = new ArrayList<Etudiant>();
	static List<message> list_msg = new ArrayList<message>();

	static List<Group> list_grp=new ArrayList<Group>();
	public static void main(String[] args) throws IOException {
		Etudiant e1= new Etudiant("ali","ali",3,InetAddress.getLocalHost(),5000,true);
		Group g1= new Group("groupe1");
        g1.getL1().add(e1);
		int port = 5852;

		try {

			DatagramSocket sc = new DatagramSocket(5852);

			byte[] dataRcv = new byte[1024];
			byte[] dataSnd = new byte[1024];
			while (true) {
				DatagramPacket pkRcv = new DatagramPacket(dataRcv, dataRcv.length);

				sc.receive(pkRcv);

				String msg = new String(pkRcv.getData(),0,pkRcv.getLength());

				InetAddress Address = pkRcv.getAddress();
				int portetu = pkRcv.getPort();
				dataSnd = msg.getBytes();
				
				//DatagramPacket pkSend = new DatagramPacket(dataSnd, dataSnd.length, Address, portetu);
			    DatagramPacket pkSend = new DatagramPacket(dataSnd, dataSnd.length, InetAddress.getLocalHost(), portetu);
				if (msg.startsWith("##")) {

					String login = msg.substring(2);
					boolean existe = false;
					for (Etudiant e : list_etu_conn) {
						if ((e.getNom()).equals(login)) {
							existe = true;
						}
					}

	  

					if (existe == true) {
						String msg1 = (" connected");
						pkSend.setData(msg1.getBytes());
						pkSend.setLength(msg1.length());
						sc.send(pkSend);
					}
					else {
						Etudiant etu1= new Etudiant();
						etu1.setAdr(pkRcv.getAddress());
						etu1.setPort(pkRcv.getPort());
						etu1.setEtat(true);
						etu1.setNom(login);
						etu1.setLogin(login);
						Serverr.list_etu_conn.add(etu1);

						String msg2 = "You are connected now ";
						pkSend = new DatagramPacket(dataSnd, dataSnd.length, Address, portetu);
						pkSend.setData(msg2.getBytes());
						pkSend.setLength(msg2.length());
						sc.send(pkSend);
					}
				}
				if (msg.startsWith("#LIST_EDTS")) {

					
					
					for (Etudiant e3 : Serverr.list_etu_conn) {
						
						String msg3 = ("liste de etudiant connecté  " + e3.getNom()+"+"+e3.getAdr()+"+"+e3.getPort());
						DatagramPacket pk = new DatagramPacket(msg3.getBytes(), msg3.length(), pkRcv.getAddress(),pkRcv.getPort());

						sc.send(pk);
					}
				}
				else if (msg.equals("#HISTO")) {

					for (message m : list_msg) {
						
						String msg4 = ("liste de message " + m.getContenu());
						pkSend.setData(msg4.getBytes());
						pkSend.setLength(msg4.length());

						sc.send(pkSend);

					}
				}

			
			else if (msg.startsWith("@#")) {

					
					String T[] = msg.split("@#");
					String etureciver = T[1];
					
					Etudiant etrec = new Etudiant();
					etrec.setLogin(etureciver);
					boolean connected = false;

					for (Etudiant e : list_etu_conn) {

						if ((e.getAdr()).equals(Address)) {
							connected = true;
						
						     if ((e.getNom()).equals(etureciver)) {
							
							     DatagramPacket pk = new DatagramPacket(T[2].getBytes(), T[2].length(), e.getAdr(), e.getPort());
							     sc.send(pk);
							     message msg1=new message(etrec,T[2]);
							     list_msg.add(msg1);
							         
						     
						     }
							     else {
									 String send = " Not connected";
									 pkSend.setData(send.getBytes());
									 pkSend.setLength(send.length());

									 sc.send(pkSend);

						}

					
					
					  					
			

			}}}
					else if (msg.equals("#GROUPS")) {
						for (Group g : list_grp) {
							
							String msgg = ("liste des groupes privés " + g.getTitre());
							DatagramPacket pk = new DatagramPacket(msgg.getBytes(), msgg.length(), pkRcv.getAddress(),pkRcv.getPort());

							sc.send(pk);
						}
					}
			       else if (msg.startsWith("#GROUP")) {
				           String T1[] = msg.split("#");
				           String newtitle=T1[2];
			            	Group gg1=new Group (newtitle);
			            	Serverr.list_grp.add(gg1);
			       }
				
			    else if(msg.startsWith("#>")) {
				  String grouptitre=msg.substring(2);

				   for (Group g: list_grp ) {
				     if ((g.getTitre()).equals(grouptitre)) {
				        Etudiant etu2= new Etudiant();
				        etu2.setAdr(pkRcv.getAddress());
				        etu2.setPort(pkRcv.getPort());
				        etu2.setEtat(true);

				        g.getL1().add(etu2);
				        String Msg=" Ajouté avec success";
				        		 DatagramPacket pk1 = new DatagramPacket( Msg.getBytes(),  Msg.length(), pkRcv.getAddress(),pkRcv.getPort());

				     	sc.send(pk1);


			     	}
				   else {
				  String msggg="ce group n'existe plus";
				  DatagramPacket pk1 = new DatagramPacket(msggg.getBytes(), msggg.length(), pkRcv.getAddress(),pkRcv.getPort());

			     	sc.send(pk1);
				}
				   }}
			 else if(msg.startsWith("#ETDS")) {
				 String T2[]=msg.split("#");
				 String titregroup=T2[2];
				 for(Group g:list_grp) {
					 if( g.getTitre().equals(titregroup)){
						 for(Etudiant e11:g.getL1()) {
							 String msg3 = ("liste de etudiant connecté  " + e11.getNom()+"+"+e11.getAdr()+"+"+e11.getPort());
							 DatagramPacket pk = new DatagramPacket(msg3.getBytes(), msg3.length(), pkRcv.getAddress(),pkRcv.getPort());
							 sc.send(pk);    
						 }
					 }
					 else {
							String msgk="Groupe n'existe pas";
							  DatagramPacket pk1 = new DatagramPacket(msgk.getBytes(),msgk .length(), pkRcv.getAddress(),pkRcv.getPort());

								sc.send(pk1);
							
							
						 }
						 
				 }
				 
				 
			 }
			 else if(msg.startsWith("@>")) {
				 String T3[]=msg.split("@>");
				 String titregroup=T3[1];
				 String msgenv=T3[2];
				 for(Group g:list_grp) {
					 if(g.getTitre().equals(titregroup)) {
						 for(Etudiant e:g.getL1() ) {
							 
							 DatagramPacket pk = new DatagramPacket(msgenv.getBytes(), msgenv.length(), e.getAdr(), e.getPort());
								sc.send(pk);}}
							 
						 
						 
					 
					 else {
						String msgk="Groupe n'existe pas";
						  DatagramPacket pk1 = new DatagramPacket(msgk.getBytes(),msgk .length(), pkRcv.getAddress(),pkRcv.getPort());

							sc.send(pk1);
						
					 }	
					 }
					 
				 }
				 
				 
				 
				 
				 
			 
			
			
			
			
			
			}}catch (IOException e11) {
			e11.printStackTrace();
		}}}
	   
	    	
	    