public class Number extends Expression {

	private double value;

	/**
	 * 
	 * @param v
	 */
	public Number(double v) {
		this.value = v;
	}

	public double getValue() {
		return this.value;
	}

	public String toString() {
		return String.valueOf(this.value);
	}

}