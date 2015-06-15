import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String formula;

		if (args.length == 1) {
			formula = args[0];

			TruthTable table = new TruthTable(formula);
			table.printTable();
		} else {
			ArrayList<HashMap<String, ArrayList<Integer>>> results = new ArrayList<HashMap<String, ArrayList<Integer>>>();

			for (int i = 0; i < args.length; i++) {
				formula = args[i];

				TruthTable table = new TruthTable(formula);
				table.printTable();

				results.add(table.getResult());

				// System.out.println(results);
			}

			ArrayList<String> vars = new ArrayList<String>();

			/**/

			HashMap<String, ArrayList<Integer>> conclusion = results
					.get(results.size() - 1);

			Iterator it = conclusion.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();

				if (pair.getKey().toString().length() == 1) {
					vars.add(pair.getKey().toString());
				}
			}

			System.out.println(vars);
			System.out.println(conclusion);
			
			for(int i = 0;i<conclusion.get(vars.get(0)).size();i++){ // por cada resultado de la conclusion
				if(conclusion.get(args[args.length-1]).get(i) == 1){
					for(int j = 0;j < args.length-1;j++){ // por cada premisa
						HashMap<String, ArrayList<Integer>> premisa = results.get(j);
						for(int k = 0;k< premisa.size();k++){
							
							//if(){
								
							//}
							
							System.out.println(premisa);
							System.out.println();
						}
					}
				}else{
					
				}
			}

			/**/
			
			int max_vars = 0;

			for (int i = 0; i < (results.size()-1); i++) {
				//results.get(i).get(args[i]);
				System.out.println(results.get(i).get(args[i]));
				
				if(max_vars < results.get(i).size()-1){
					max_vars = results.get(i).size()-1;
				}
				
				System.out.println(max_vars);
				//for(){
					
				//}
			}

		}

	}

}
