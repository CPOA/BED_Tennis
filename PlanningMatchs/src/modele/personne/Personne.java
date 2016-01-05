package modele.personne;

public abstract class Personne {

	private int m_id;
	private String m_nom;
	private String m_prenom;
	private String m_adresseMail;
	private Sexe m_sexe;
	private String m_nationalite;
        
        public Personne(int id, String nom, String prenom, String mail, Sexe sexe, String nationalite) {
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

	public void setPrenom(String prenom) {
            this.m_prenom = prenom;
	}

	public String getAdresseMail() {
            return m_adresseMail;
	}

	public void setAdresseMail(String nouvelleAdresse) {
            m_adresseMail = nouvelleAdresse;
	}

	public Sexe getSexe() {
            return m_sexe;
	}

	public String getNationalite() {
            return m_nationalite;
	}
        
        public boolean aMemeNationalite(String nat) {
            return this.getNationalite().equalsIgnoreCase(nat);
        }
        
        public boolean aMemeNationalite(Personne p) {
            // on compare les minuscules pour éviter les problèmes de casse
            return this.aMemeNationalite(p.getNationalite());
        }
        
        @Override
        public String toString() {
            return m_prenom + " " + m_nom;
        }
        
        public String description() {
            return m_prenom + " " + m_nom + " (" + typePersonne() + ", " + getNationalite() + ", " + m_sexe + ")";
        }
        
        
        public abstract String typePersonne();
}