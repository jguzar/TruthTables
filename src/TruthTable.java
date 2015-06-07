import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RegExp: (\([-]?([A-Z]|[a-z]){1}[&|>]{1}[-]?([A-Z]|[a-z]){1}\))
 * */
public class TruthTable {

	public String formula;
	private char[] form;;
	private Character[] aux_connectors = { '&', '|', '>', '-' };
	private Character[] aux_symbols = { '(', ')' };
	private final ArrayList<Character> valid_connectors = new ArrayList<Character>();
	private final ArrayList<Character> symbols = new ArrayList<Character>();
	private ArrayList<Character> variables = new ArrayList<Character>();
	private HashMap<String, ArrayList> result = new HashMap<String, ArrayList>();

	public TruthTable(String formula) {
		this.formula = formula.trim();
		this.form = this.formula.toCharArray();

		System.out.println(this.formula);
		System.out.println(this.form);

		Pattern p = Pattern
				.compile("(\\([-]?([A-Z]|[a-z]){1}[&|>]{1}[-]?([A-Z]|[a-z]){1}\\))");
		Matcher m = p.matcher(this.formula);

		int count = 0;
		while (m.find()) {
			count++;
			
			System.out.println("----------------------------");
			System.out.println("Match number " + count);
			System.out.println("Found value: " + m.group(0));
			int start = m.start();
			int end = m.end();
			System.out.println("start(): " + start);
			System.out.println(this.form[start]);
			System.out.println("end(): " + end);
			System.out.println(this.form[end-1]);
		}

		for (int i = 0; i < this.aux_connectors.length; i++)
			this.valid_connectors.add(this.aux_connectors[i]);
		for (int i = 0; i < this.aux_symbols.length; i++)
			this.symbols.add(this.aux_symbols[i]);
		this.getVariables();
		this.processFormula();
	}

	private void processFormula() {

	}

	private void getVariables() {
		ArrayList<ArrayList<Integer>> result_temp = new ArrayList<ArrayList<Integer>>();
		
		for (int i = 0; i < this.form.length; i++) {
			if (!this.valid_connectors.contains(this.form[i])
					&& !this.symbols.contains(this.form[i])
					&& !this.variables.contains(this.form[i])){
				this.variables.add(this.form[i]);
				//this.result.put(String.valueOf(this.form[i]), new ArrayList<Integer>());
				result_temp.add(new ArrayList<Integer>());
			}
		}
		
		int[] values = new int[this.variables.size()];
		for(int i = 0;i<values.length;i++) values[i] = 1;
		
		String tmp_size = ""+this.variables.size();
		Double tmp_double_size = Double.parseDouble(tmp_size);
		int total = (int) Math.pow(2.0, tmp_double_size);
		System.out.println("Total: "+total);
		
		for(int i = 0;i<total;i++){
			Double k =Double.parseDouble(String.valueOf(this.variables.size()-1));
			for(int j = 0;j<this.variables.size();j++){
				result_temp.get(j).add(values[j]);
				
				int p = (int) Math.pow(2.0, k);
				int div = Integer.parseInt(new String(""+p));
				
				if((i+1)%div==0){
					if(values[j]==1)
						values[j]--;
				}else{
					if(values[j]==0)
						values[j]++;
				}
				k--;
			}
		}

		System.out.println(this.variables);
	}

	public void calculateTable() {

	}

	public void printTable() {

	}
}
