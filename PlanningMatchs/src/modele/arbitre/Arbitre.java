
package modele.arbitre;



public abstract class Arbitre extends modele.Personne {

	private String categorie;
	private int nbMatchsSimples;
	private int nbMatchsDoubles;

        public Arbitre(String categorie, int nbMatchsSimples, int nbMatchsDoubles, int id, String nom, String prenom, String mail, int sexe, String nationalite) {
            super(id, nom, prenom, mail, sexe, nationalite);
            this.categorie = categorie;
            this.nbMatchsSimples = nbMatchsSimples;
            this.nbMatchsDoubles = nbMatchsDoubles;
        }

	public void getNbMatchsSimples() {
		
	}

	public void getNbMatchsDoubles() {
		
	}

	public String getCategorie() {
            return null;
	}

	public void setCategorie() {
		
	}

	public boolean peutArbitrer() {
            if (nbMatchsDoubles >= 2) 
                return false;
            if (nbMatchsSimples >= 2)
                return false;
            return true;
	}
        
        public boolean acheter(long dollars) {
            if (dollars > 10000000)
                return true;
            else
                return false;
        }

}