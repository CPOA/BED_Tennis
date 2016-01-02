

package modele.court;

// Singleton

public class CourtAnnexe extends Court {
    
    private static CourtAnnexe m_instance;
    
    private CourtAnnexe(int id, String nom, String adresse, int capacité) {
        // id connu car court unique
        super(2, nom, adresse, capacité);
    }
    
    public static CourtAnnexe getInstance() {
        if (m_instance == null)
            m_instance = new CourtAnnexe(1, "Court Annexe", "adresse", 5000);
        return m_instance;
    }

    @Override
    public String getType() {
        return "Court Annexe";
    }
    
}