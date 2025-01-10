import java.util.*;

public class Program {

	/**
	 * Association Type = Variable
	 */
	private HashMap<String, Variable> variables;
	/**
	 * Association Type = Assignment
	 */
	private ArrayList<Assignment> assignements;

	public Program() {
		this.variables = new HashMap<>();
		this.assignements = new ArrayList<>();
	}

	/**
	 * 
	 * @param v
	 * @param e
	 */
	public void addAssignment(Variable v, Expression e) {
		this.assignements.add(new Assignment(v, e));
	}

	/**
	 * 
	 * @param n
	 */
	public Variable getVariable(String n) {
		Variable var = this.variables.get(n);
		if (var != null) return var;
		Variable v = new Variable(n);
		this.variables.put(n, v);
		return v;
	}

	public void run() {
		for (Assignment a : assignements) {
			a.execute();
		}

		for (Map.Entry<String, Variable> entry : this.variables.entrySet()) {
			String key = entry.getKey();
			Variable v = entry.getValue();
			System.out.println(key + " = " + v.getValue());
		}
	}

	public String toString() {
		String s = "";
		s += this.assignements;
		return s;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Program p = new Program();
		// x = 2
		p.addAssignment( p.getVariable( "x" ), new Number( 2 ));
		// y = x
		p.addAssignment( p.getVariable( "y" ), p.getVariable( "x" ));
		// x = -5.26
		p.addAssignment( p.getVariable( "x" ), new Number( -5.26 ));
		// z = x + y
		p.addAssignment(p.getVariable("z"), new Plus(p.getVariable("x"), p.getVariable("y")));
		// a = z / 2
		p.addAssignment(p.getVariable("a"), new Divide(p.getVariable("z"), new Number(2)));
		// b = z * 2
		p.addAssignment(p.getVariable("b"), new Multiply(p.getVariable("a"), new Number(2)));
		// c = b - 1.3
		p.addAssignment(p.getVariable("b"), new Minus(p.getVariable("b"), new Number(1.3)));

		// Affiche les instructions
		System.out.println( p.toString() );
		// Une ligne blanche
		System.out.println();
		// Execute chaque instruction l'une apres l'autre, puis affiche les variables et leurs valeurs
		// Resultat attendu (pas obligatoirement dans cet ordre) :
		// x = -5.26
		// y = 2.0
		p.run(); 
	}

}