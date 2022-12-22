package model;

public class message {
	private Etudiant e;
	private String contenu;
	public message() {};
	
	public message(Etudiant e, String contenu) {
		super();
		this.e = e;
		this.contenu = contenu;
	}
	public Etudiant getE() {
		return e;
	}
	public void setE(Etudiant e) {
		this.e = e;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	
}
	
	

   
