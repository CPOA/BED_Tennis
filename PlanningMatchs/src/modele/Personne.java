package modele;

public abstract class Personne {

	private int m_id;
	private String m_nom;
	private String m_prenom;
	private String m_adresseMail;
	private int m_sexe; // 1 = homme, 2 = femme
	private String m_nationalite;
        
        public Personne(int id, String nom, String prenom, String mail, int sexe, String nationalite) {
            m_id = id;
            m_nom = nom;
            m_prenom = prenom;
            m_adresseMail = mail;
            m_sexe = sexe;
            m_nationalite = nationalite;
        }

	public int getId() {
            return m_id;
        }

	public String getNom() {
            return m_nom;
	}

	public void setNom(String nom) {
            m_nom = nom;
	}

	public String getPrenom() {
            return m_prenom;
	}

	public void setPrenom() {
		
	}

	public String getAdresseMail() {
            return m_adresseMail;
	}

	public void setAdressMail() {
		
	}

	public void getSexe() {
		
	}

	public String getNationalite() {
            return m_nationalite;
	}
        
        public boolean aMemeNationalite(Personne p) {
            return this.m_nationalite.equals(p.getNationalite());
        }

}