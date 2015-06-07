

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String formula = args[0];
		
		System.out.print("La fórmula es: ");
		System.out.println(formula);
		
		TruthTable table = new TruthTable(formula);
		table.calculateTable();
		table.printTable();
		
	}

}
