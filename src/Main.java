

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String formula = args[0];

		TruthTable table = new TruthTable(formula);
		table.calculateTable();
		table.printTable();
		
	}

}
