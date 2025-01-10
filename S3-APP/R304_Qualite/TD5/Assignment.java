public class Assignment {

	private Variable variable;
	private Expression expression;

	/**
	 * 
	 * @param v
	 * @param e
	 */
	public Assignment(Variable v, Expression e) {
		this.variable = v;
		this.expression = e;
	}

	public void execute() {
		this.variable.setValue(this.expression.getValue());
	}

	public String toString() {
		String s = "";
		if (this.variable != null) s += this.variable.getName();
		else s += "null";
		s += " <- ";
		s += this.expression;
		return s;
	}

}