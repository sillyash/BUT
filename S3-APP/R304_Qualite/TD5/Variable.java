public class Variable extends Expression {

	private String name;
	private double value;

	/**
	 * 
	 * @param n
	 */
	public Variable(String n) {
		this.name = n;
	}

	/**
	 * 
	 * @param v
	 */
	public void setValue(double v) {
		this.value = v;
	}

	public double getValue() {
		return this.value;
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		String s = "";
		s += this.name;
		return s;
	}

}