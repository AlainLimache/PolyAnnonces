package model;

public class Utilisateur {

	private int id;
	private String nom;
	private String prenom;
	private String mail;
	private String motDePasse;
	private String adresse;
	private String ville;
	
	public Utilisateur(int argId) {
		setId(argId);
	}
	
	public Utilisateur(int argId, String argNom, String argPrenom, String argMail, String argMotDePasse, String argAdresse, String argVille ) {
		setId(argId);
		setNom(argNom);
		setPrenom(argPrenom);
		setMail(argMail);
		setMotDePasse(argMotDePasse);
		setAdresse(argAdresse);
		setVille(argVille);
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}
	



}
