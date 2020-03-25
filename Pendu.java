import java.util.Scanner;

public class Pendu {
	public static void main(String[] args) {
		pendu();
	}

	public static listeMots listeMots;

	public static void pendu() {
		int vieRestante = 7, verificationIndex, variableRigolote = 0, nbrLettreDuMot, lettreDejaTestéInt;
		// Ma variableRigolote sert à définir quand le joueur a gagné
		char play = 'o', lettreAConvertir, obelix, lettreATester, lettreATesterTLC, soloBoyChar, soloBoyCharTLC;
		String lettreATestéeSTR, motATrouver, soloBoyStr, saisiUtilisateur, motATrouver1;
		boolean areYouNoob, uneSeuleLettreFausse = false;
		StringBuilder lettreTestéeSB = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		listeMots = new listeMots();
		while (play == 'o') {
			do {
				System.out.println("Avez-vous un ami pour jouer avec vous ? (O/N)");
				soloBoyStr = sc.nextLine();
				soloBoyCharTLC = soloBoyStr.charAt(0);
				soloBoyChar = Character.toLowerCase(soloBoyCharTLC);
			} while (soloBoyChar != 'o' && soloBoyChar != 'n');
			do {
				if (soloBoyChar == 'n')
					motATrouver = listeMots.get();
				else {
					System.out.println("Demandez à votre ami de taper un mot que vous devrez trouver");
					motATrouver1 = sc.nextLine();
					motATrouver = motATrouver1.substring(0).toLowerCase();
					for (int j = 0; j < 500; j++)
						System.out.println();
				}
				nbrLettreDuMot = motATrouver.length();
				StringBuilder motVisible = new StringBuilder(nbrLettreDuMot);
				for (int i = 0; i < motATrouver.length(); i++) { // création d'une copie du mot à trouver sous forme
																	// d'asterix
					lettreAConvertir = motATrouver.charAt(i);
					obelix = convertirLettreEtoile(lettreAConvertir);
					motVisible.append(obelix);
				}
				progression(motVisible, vieRestante);
				while (vieRestante > 0 && variableRigolote != nbrLettreDuMot) {
					do { // protection contre la perte de points en cas d'utilisation de
							// chiffres/symboles
						System.out.println("Veuillez choisir une lettre:");
						saisiUtilisateur = sc.next();
						while (saisiUtilisateur.length() > 1) {
							System.out.println(
									"Veuillez ne saisir qu'une seule lettre à la fois.\n Merci de retaper une lettre");
							saisiUtilisateur = sc.next();
						}
						lettreATesterTLC = saisiUtilisateur.charAt(0);
						lettreATester = Character.toLowerCase(lettreATesterTLC);
						areYouNoob = youRnoob(lettreATester);
					} while (areYouNoob != false); // empèche le joueur d'utiliser d'autres caractère que des lettres
					verificationIndex = motATrouver.indexOf(lettreATester);
					if (verificationIndex == -1) { // cas de réponse fausse
						lettreATestéeSTR = String.valueOf(lettreATester);
						lettreDejaTestéInt = lettreTestéeSB.indexOf(lettreATestéeSTR);
						if (lettreDejaTestéInt != -1) {
							System.out.println("Vous avez déjà du remarquer que cette lettre n'est pas bonne ;)");
						} else {
							if (vieRestante == 7) {
								vieRestante -= 1;
								uneSeuleLettreFausse = true;
								progression(motVisible, vieRestante);
								System.out.println("La lettre " + lettreATester + " n'est pas dans le mot à trouver");
								lettreTestéeSB.append(lettreATester + "; ");
							} else {
								lettreDejaTeste(lettreTestéeSB, uneSeuleLettreFausse);
							}
						}

					} else { // cas de bonne réponse
						do {
							variableRigolote += 1;
							motVisible.deleteCharAt(verificationIndex);
							motVisible.insert(verificationIndex, lettreATester);
							verificationIndex = motATrouver.indexOf(lettreATester, verificationIndex + 1);
						} while (verificationIndex != -1);
						{
							progression(motVisible, vieRestante);
							if (vieRestante != 7) {
								lettreDejaTeste(lettreTestéeSB, uneSeuleLettreFausse);
							}

						}
					}
				}

			} while (variableRigolote != nbrLettreDuMot && vieRestante != 0);
			if (variableRigolote == nbrLettreDuMot) {
				variableRigolote = 0;
				System.out.println("Bravo, vous avez gagné!!");
				play = retry(sc);
			}
			if (vieRestante == 0) {
				vieRestante = 7;
				System.out.println("Quel dommage, c'est un cuisant échec !!");
				play = retry(sc);
			}

		}
		while (play != 'o' && play != 'n')
			play = retry(sc);
		if (play == 'n') {
			System.out.println("Au revoir");
		}
	}

	private static boolean youRnoob(char lettreATester) {
		if (lettreATester == 'a' || lettreATester == 'b' || lettreATester == 'c' || lettreATester == 'd'
				|| lettreATester == 'e' || lettreATester == 'f' || lettreATester == 'g' || lettreATester == 'h'
				|| lettreATester == 'i' || lettreATester == 'j' || lettreATester == 'k' || lettreATester == 'l'
				|| lettreATester == 'm' || lettreATester == 'n' || lettreATester == 'o' || lettreATester == 'p'
				|| lettreATester == 'q' || lettreATester == 'r' || lettreATester == 's' || lettreATester == 't'
				|| lettreATester == 'u' || lettreATester == 'v' || lettreATester == 'w' || lettreATester == 'x'
				|| lettreATester == 'y' || lettreATester == 'z') {
			return false;

		} else {
			return true;
		}
	}

	private static void lettreDejaTeste(StringBuilder lettreTestéToAddSB, boolean uneLettrefausse) {
		if (uneLettrefausse == false) {
			System.out.println("Vous avez déjà testé les lettres: " + lettreTestéToAddSB
					+ " qui ne sont pas dans le mot à trouver");
		} else {
			System.out.println("La lettre: " + lettreTestéToAddSB + " n'est pas dans le mot à trouver");
		}

	}

	private static void progression(StringBuilder motVisibleSB, int vieRestanteP) {
		System.out.println(
				"Progression actuelle:\n" + motVisibleSB + "\n\n Il vous reste: " + vieRestanteP + " tentatives\n");
	}

	private static char retry(Scanner sc) {
		char reessayer = ' ', reessayerSC = ' ';
		String retry = null;
		if (reessayerSC != 'o' && reessayerSC != 'n') {
			System.out.println("Voulez-vous ré-essayer ? (O/N)");
			sc.next();
			retry = sc.next();
			reessayer = retry.charAt(0);
			reessayerSC = Character.toLowerCase(reessayer);
		}
		return reessayerSC;
	}

	private static char convertirLettreEtoile(char lettreAConvertir) {
		String lettreEnAsterixS = null;
		char lettreEnAsterixC = ' ';
		switch (lettreAConvertir) {
		case 'a':
			lettreEnAsterixS = "a".replace("a", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 'b':
			lettreEnAsterixS = "b".replace("b", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 'c':
			lettreEnAsterixS = "c".replace("c", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 'd':
			lettreEnAsterixS = "d".replace("d", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 'e':
			lettreEnAsterixS = "e".replace("e", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 'f':
			lettreEnAsterixS = "f".replace("f", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 'g':
			lettreEnAsterixS = "g".replace("g", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 'h':
			lettreEnAsterixS = "h".replace("h", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 'i':
			lettreEnAsterixS = "i".replace("i", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 'j':
			lettreEnAsterixS = "j".replace("j", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 'k':
			lettreEnAsterixS = "k".replace("k", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 'l':
			lettreEnAsterixS = "l".replace("l", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 'm':
			lettreEnAsterixS = "m".replace("m", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 'n':
			lettreEnAsterixS = "n".replace("n", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 'o':
			lettreEnAsterixS = "o".replace("o", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 'p':
			lettreEnAsterixS = "p".replace("p", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 'q':
			lettreEnAsterixS = "q".replace("q", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 'r':
			lettreEnAsterixS = "r".replace("r", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 's':
			lettreEnAsterixS = "s".replace("s", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 't':
			lettreEnAsterixS = "t".replace("t", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 'u':
			lettreEnAsterixS = "u".replace("u", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 'v':
			lettreEnAsterixS = "v".replace("v", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 'w':
			lettreEnAsterixS = "w".replace("w", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 'x':
			lettreEnAsterixS = "x".replace("x", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 'y':
			lettreEnAsterixS = "y".replace("y", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		case 'z':
			lettreEnAsterixS = "z".replace("z", "*");
			lettreEnAsterixC = lettreEnAsterixS.charAt(0);
			break;
		default:
			System.out.println("Merci d'utiliser des caractères de l'alphabet latin");
		}
		return lettreEnAsterixC;
	}

}
