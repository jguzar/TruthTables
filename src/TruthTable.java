import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RegExp1: (\(?[-]?([A-Z]|[a-z]){1}[&|>]{1}[-]?([A-Z]|[a-z]){1}\)?)
 * RegExp2: ([-]{1}([A-Z]|[a-z]){1})
 * */
public class TruthTable {

	private String regexp1 = "([-]{1}([A-Z]|[a-z]){1})";
	private String regexp2 = "(\\(?[-]?([A-Z]|[a-z]){1}[&|>]{1}[-]?([A-Z]|[a-z]){1}\\)?)";
	private String formula;
	private Character[] aux_connectors = { '&', '|', '>', '-' };
	private Character[] aux_symbols = { '(', ')' };
	private final ArrayList<Character> valid_connectors = new ArrayList<Character>();
	private final ArrayList<Character> symbols = new ArrayList<Character>();
	private ArrayList<Character> variables = new ArrayList<Character>();
	private HashMap<String, ArrayList<Integer>> result = new HashMap<String, ArrayList<Integer>>();
	private Character current_char = 'a';
	private HashMap<String,String> aux_vars = new HashMap<String, String>();

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
		//char[] form = formula.toCharArray();
		if(formula.length() > 1){
		Pattern p;
		Matcher m;
		//int count;
		StringBuffer sb = new StringBuffer();

		p = Pattern.compile(this.regexp1);
		m = p.matcher(formula);

		//count = 0;

		while (m.find()) {
			//count++;
			sb = new StringBuffer();

//			System.out.println("----------------------------");
//			System.out.println("Match number " + count);
//			System.out.println("Found value: " + m.group(0));
//			int start = m.start();
//			int end = m.end();
//			System.out.println("start(): " + start);
//			System.out.println(form[start]);
//			System.out.println("end(): " + end);
//			System.out.println(form[end - 1]);
			
			String value_founded = m.group(0);
			System.out.println(value_founded);
			String[] value_founded_arr = value_founded.split("-");
			System.out.println(value_founded_arr);
			
			ArrayList<Integer> group_result = new ArrayList<Integer>();
			
			ArrayList<Integer> element1 = null;
			
			if(this.result.containsKey(value_founded_arr[1])){
				element1 = this.result.get(value_founded_arr[1]);
			}else{
				element1 = this.result.get(aux_vars.get(value_founded_arr[1]));
			}
			
			for(int i=0;i<element1.size();i++){
				group_result.add(this.not(element1.get(i)));
			}
			
			this.result.put(m.group(0),group_result);
			
			this.aux_vars.put(String.valueOf(this.current_char), m.group(0));
			
			m.appendReplacement(sb, Matcher.quoteReplacement(String.valueOf(this.current_char)));
			m.appendTail(sb);
			this.current_char++;
			formula = sb.toString();
			m = p.matcher(formula);
		}
		
		//formula = sb.toString();
		//form = formula.toCharArray();

		
		p = Pattern
				.compile(this.regexp2);
		m = p.matcher(formula);

		//count = 0;
		while (m.find()) {
			sb = new StringBuffer();
			//count++;

//			System.out.println("----------------------------");
//			System.out.println("Match number " + count);
//			System.out.println("Found value: " + m.group(0));
//			int start = m.start();
//			int end = m.end();
//			System.out.println("start(): " + start);
//			System.out.println(form[start]);
//			System.out.println("end(): " + end);
//			System.out.println(form[end - 1]);
			
			String value_founded = m.group(0);
			String value_founded_clean = value_founded;
			value_founded_clean = value_founded_clean.replace("(", "").replace(")", "");
			System.out.println(value_founded);
			Character char_operation = ' ';
			
			String[] value_founded_arr = {};
			for(int i=0;i<this.aux_connectors.length;i++){
				if(value_founded_clean.contains(String.valueOf(this.aux_connectors[i]))){
					char_operation = this.aux_connectors[i];
					value_founded_arr = value_founded_clean.split(String.valueOf(this.aux_connectors[i]));
					System.out.println(value_founded_arr);
					break;
				}
			}
			
			ArrayList<Integer> group_result = new ArrayList<Integer>();
			
			ArrayList<Integer> element1 = null;
			ArrayList<Integer> element2 = null;
			
			if(this.result.containsKey(value_founded_arr[0])){
				element1 = this.result.get(value_founded_arr[0]);
			}else{
				element1 = this.result.get(aux_vars.get(value_founded_arr[0]));
			}
			
			if(this.result.containsKey(value_founded_arr[1])){
				element2 = this.result.get(value_founded_arr[1]);
			}else{
				element2 = this.result.get(aux_vars.get(value_founded_arr[1]));
			}
			
			for(int i=0;i<element1.size();i++){
				int val = 0;

				if(char_operation == '&'){
					val = element1.get(i) & element2.get(i);
				}else if(char_operation == '|'){
					val = element1.get(i) | element2.get(i);
				}else if(char_operation == '>'){
					val = this.conditional(element1.get(i), element2.get(i));
				}
				
				group_result.add(val);
			}
			
			this.result.put(m.group(0),group_result);
			
			this.aux_vars.put(String.valueOf(this.current_char), m.group(0));
			
			m.appendReplacement(sb, Matcher.quoteReplacement(String.valueOf(this.current_char)));
			m.appendTail(sb);
			this.current_char++;
			formula = sb.toString();
			m = p.matcher(formula);
		}
		
		
		
		this.processFormula(formula);
		//form = formula.toCharArray();
		
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
	
	private int not(int x){
		if(x==0) x=1; else if(x==1) x=0;
		return x;
	}
	
	private int conditional(int x, int y){
		int r = 0;
		if(((x == 1) && (y == 1)) || ((x == 0) && (y == 1)) || ((x == 0) && (y == 0))){
			r = 1;
		}else if((x == 1) && (y == 0)){
			r = 0;
		}
		return r;
	}
	
	
}
