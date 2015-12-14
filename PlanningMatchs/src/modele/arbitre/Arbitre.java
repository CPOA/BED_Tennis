
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

	public void peutArbitrer() {
		
	}

}