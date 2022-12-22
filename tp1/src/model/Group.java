package model;

import java.util.ArrayList;
import java.util.List;

public class Group {
	private String titre;
	List <Etudiant> l1 =new ArrayList<Etudiant>();
	public List<Etudiant> getL1() {
		return l1;
	}
	public void setL1(List<Etudiant> l1) {
		this.l1 = l1;
	}
	public List<message> getL2() {
		return l2;
	}
	public void setL2(List<message> l2) {
		this.l2 = l2;
	}
	private List <message> l2=new ArrayList<message>();;
	public Group(String titre) {
		super();
		this.titre = titre;
		
	}
	public  Group() {
	super();}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	
	
	
	
	
}
