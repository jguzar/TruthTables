import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String formula = "";

		if (args.length == 1) {
			formula = args[0];

			TruthTable table = new TruthTable(formula);
			table.printTable();
		} else if (args.length > 1){
			HashMap<String, ArrayList<Integer>> result = new HashMap<String, ArrayList<Integer>>();

			formula += "(";
			for (int i = 0; i < args.length-1; i++) {
				if(args[i].length()>1){
					if(args[i].length() == 2 && args[i].charAt(0) == '∼'){
						formula += args[i];
					}else{
						formula += "("+args[i]+")";
					}
				}else{
					formula += args[i];
				}
				
				if(i<args.length-2){
					formula+="∧";
				}
			}
			String conclusion = args[args.length-1];
			
			if(conclusion.length()>1){
				if(conclusion.length() == 2 && conclusion.charAt(0) == '∼'){
					formula+=")→"+conclusion;
				}else{
					formula+=")→("+conclusion+")";
				}
			}else{
				formula+=")→"+conclusion;
			}
			
			TruthTable table = new TruthTable(formula);
			table.printTable();

			result = table.getResult();

			//System.out.println(result);
			
			ArrayList<String> vars = new ArrayList<String>();

			Iterator it = result.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();

				if (pair.getKey().toString().length() == 1) {
					vars.add(pair.getKey().toString());
				}
			}
			
			//System.out.println(vars);
			StringBuilder r_str = new StringBuilder();
			
			Boolean invalid = false;
			
			for(int i = 0;i<result.get(formula).size();i++){
				if(result.get(formula).get(i) == 0){
					invalid = true;
					r_str.append("Argumento no válido para ");
					for(int j=0;j<vars.size();j++){
						r_str.append(vars.get(j)).append(" = ").append(result.get(vars.get(j)).get(i)).append(" ");
					}
					r_str.append("\n");
				}
			}
			
			if(!invalid){
				r_str.append("Argumento válido para todos los casos.\n");
			}
			
			System.out.println(r_str);

		}

	}

}
