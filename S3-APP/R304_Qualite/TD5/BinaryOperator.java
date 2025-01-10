public abstract class BinaryOperator extends Expression
{
	protected Expression leftOperand;
	protected Expression rightOperand;

	/**
	 *
	 * @param left
	 * @param right
	 */
	public BinaryOperator(Expression left, Expression right) {
		super();
		this.leftOperand = left;
		this.rightOperand = right;
	}

	public String toString() {
		String s = "";

		s += this.leftOperand.toString();
		s += " " + this.getOperator() + " ";
		s += this.rightOperand.toString();

		return s;
	}

	public abstract String getOperator();
}