package model;

import java.net.InetAddress;

public class Etudiant {
	private String nom;
	private String login;
	private int niveau;
	private  InetAddress adr;
	private int port;
	private boolean etat;

	public Etudiant () {

	}
	public Etudiant(String nom, String login, int niveau, InetAddress adr, int port, boolean etat) {
		super();
		this.nom = nom;
		this.login = login;
		this.niveau = niveau;
		this.adr = adr;
		this.port = port;
		this.etat = etat;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public int getNiveau() {
		return niveau;
	}
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	public InetAddress getAdr() {
		return adr;
	}
	public void setAdr(InetAddress adr) {
		this.adr = adr;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public boolean isEtat() {
		return etat;
	}
	public void setEtat(boolean etat) {
		this.etat = etat;
	}
	
}