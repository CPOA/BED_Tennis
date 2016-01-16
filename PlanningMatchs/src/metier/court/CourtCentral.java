package metier.court;

public class CourtCentral extends Court {
    
    private static CourtCentral m_instance;
    
    private CourtCentral(int id, String nom, String adresse, int capacité) {
        // id connu car court unique
        super(1, nom, adresse, capacité);
    }
    
    public static CourtCentral getInstance() {
        if (m_instance == null)
            m_instance = new CourtCentral(1, "Court Central", "adresse du court central", 5600);
        return m_instance;
    }

    @Override
    public String getType() {
        return "Court Central";
    }
}