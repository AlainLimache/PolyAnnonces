package model;

public class Annonce {

	private int id;
	private float prix;
	private String description;
	private String categorie;
	private String adresse;
	private String ville;
	private int idUser;
	
	public Annonce(int argId, float argPrix, String argDescription, String argCategorie, String argAdresse, String argVille, int argIdUser ) {
		setId(argId);
		setPrix(argPrix);
		setDescription(argDescription);
		setCategorie(argCategorie);
		setAdresse(argAdresse);
		setVille(argVille);
		setIdUser(argIdUser);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description= description;
	}

	
	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
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
	
	
	public int getIdUser() {
		return idUser;
	}
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
}
