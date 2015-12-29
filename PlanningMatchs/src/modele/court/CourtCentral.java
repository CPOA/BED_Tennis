package modele.court;

public class CourtCentral extends Court {
    
    private static CourtCentral m_instance;
    
    private CourtCentral(int id, String nom, String adresse, int capacité) {
        super(id, nom, adresse, capacité);
    }
    
    public static CourtCentral getInstance() {
        if (m_instance == null)
            m_instance = new CourtCentral(1, "Court Central", "adresse", 3000);
        return m_instance;
    }

    @Override
    public String getType() {
        return "Court Central";
    }
}