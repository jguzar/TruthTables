import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RegExp: (\([-]?([A-Z]|[a-z]){1}[&|>]{1}[-]?([A-Z]|[a-z]){1}\))
 * */
public class TruthTable {

	private String formula;
	private Character[] aux_connectors = { '&', '|', '>', '-' };
	private Character[] aux_symbols = { '(', ')' };
	private final ArrayList<Character> valid_connectors = new ArrayList<Character>();
	private final ArrayList<Character> symbols = new ArrayList<Character>();
	private ArrayList<Character> variables = new ArrayList<Character>();
	private HashMap<String, ArrayList<Integer>> result = new HashMap<String, ArrayList<Integer>>();

	public TruthTable(String formula) {
		this.formula = formula.trim();

		System.out.print("La fórmula es: ");
		System.out.println(this.formula);

		for (int i = 0; i < this.aux_connectors.length; i++)
			this.valid_connectors.add(this.aux_connectors[i]);
		for (int i = 0; i < this.aux_symbols.length; i++)
			this.symbols.add(this.aux_symbols[i]);

		this.setVariables(this.formula);
		this.processFormula(this.formula);
	}

	private void processFormula(String formula) {
		char[] form = formula.toCharArray();

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
			System.out.println(form[start]);
			System.out.println("end(): " + end);
			System.out.println(form[end - 1]);
		}
	}

	private void setVariables(String formula) {
		char[] form = formula.toCharArray();
		for (int i = 0; i < form.length; i++) {
			if (!this.valid_connectors.contains(form[i])
					&& !this.symbols.contains(form[i])
					&& !this.variables.contains(form[i])) {
				this.variables.add(form[i]);
				this.result.put(String.valueOf(form[i]),
						new ArrayList<Integer>());
			}
		}

		int[] values = new int[this.variables.size()];
		for (int i = 0; i < values.length; i++)
			values[i] = 1;

		String tmp_size = "" + this.variables.size();
		Double tmp_double_size = Double.parseDouble(tmp_size);
		int total = (int) Math.pow(2.0, tmp_double_size);

		for (int i = 0; i < total; i++) {
			Double k = Double
					.parseDouble(String.valueOf(this.variables.size() - 1));
			for (int j = 0; j < this.variables.size(); j++) {

				this.result.get(Character.toString(this.variables.get(j))).add(
						values[j]);

				int p = (int) Math.pow(2.0, k);
				int div = Integer.parseInt(new String("" + p));

				if ((i + 1) % div == 0) {
					if (values[j] == 1)
						values[j] = 0;
					else if (values[j] == 0)
						values[j] = 1;
				}
				k--;
			}
		}

		System.out.print("Las variables son: ");
		System.out.println(this.variables);
		System.out.println(this.result);
	}

	public void calculateTable() {

	}

	public void printTable() {
		StringBuilder header = new StringBuilder();
		StringBuilder body = new StringBuilder();
		ArrayList<String> keys = new ArrayList<String>();

		Iterator it = result.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();

			header.append(pair.getKey());
			header.append("\t");

			keys.add((String) pair.getKey());
		}
		
		for(int i=0;i<result.get(keys.get(0)).size();i++){
			
			for(int j=0;j<keys.size();j++){
				body.append(result.get(keys.get(j)).get(i));
				body.append("\t");
			}
			body.append("\n");
		}

		System.out.println("TABLA DE VERDAD:");
		System.out.println(header);
		System.out.println(body);
	}
}
