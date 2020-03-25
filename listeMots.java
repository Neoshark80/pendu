import java.util.ArrayList;
import java.util.List;

public class listeMots {

	List<String> listeMots = new ArrayList<String>();

	public listeMots() {
		listeMots.add("trouver");
		listeMots.add("confiance");
		listeMots.add("cassette");
		listeMots.add("miettes");
		listeMots.add("portance");
		listeMots.add("peinture");
		listeMots.add("casque");
		listeMots.add("ordinateur");
		listeMots.add("raisonnable");
		listeMots.add("couplage");
		listeMots.add("audible");
		listeMots.add("parapluie");
		listeMots.add("extermination");
		listeMots.add("malettes");
		listeMots.add("hermaphrodite");
		listeMots.add("anticonstitution");
		listeMots.add("voiture");
		listeMots.add("paradoxe");

	}

	public String get() {
		int indexAleatoire = 0 + (int) (Math.random() * 8);
		return this.listeMots.get(indexAleatoire);
	}
}