package modele.court;

public abstract class Court {

	private int id;
	private int nom;
	private int adresse;
	private int capacité;
        
        public Court(int id, int nom, int adresse, int capacité) {
            this.id = id;
            this.nom = nom;
            this.adresse = adresse;
            this.capacité = capacité;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAdresse() {
            return adresse;
        }

        public void setAdresse(int adresse) {
            this.adresse = adresse;
        }

        public int getCapacité() {
            return capacité;
        }

        public void setCapacité(int capacité) {
            this.capacité = capacité;
        }



}