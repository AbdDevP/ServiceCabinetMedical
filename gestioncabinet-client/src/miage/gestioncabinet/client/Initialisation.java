package miage.gestioncabinet.client;
import miage.gestioncabinet.coreM.MedecinImpl;
import miage.gestioncabinet.coreM.PlanningService;

public class Initialisation {

	public static void main(String[] args) {
		
		MedecinImpl medecin1 = new MedecinImpl("Leroy", "Clara", "1", "008");
		PlanningService.medecindefaut = medecin1;
		PlanningTestClient.main(args);

	}

}
