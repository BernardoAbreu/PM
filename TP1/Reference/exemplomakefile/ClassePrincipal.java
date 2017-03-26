import pacoteA.ClasseA;
import pacoteA.ClasseB;
import pacoteB.ClasseC;

public class ClassePrincipal {

	public static void main(String[] args) {
		
		ClasseA ca = new ClasseA();
		ca.metodoPacoteA();
		
		ClasseA cb = new ClasseB();
		cb.metodoPacoteA();
		
		ClasseC cc = new ClasseC();
		cc.metodoPacoteB();
		
	}
	
}
