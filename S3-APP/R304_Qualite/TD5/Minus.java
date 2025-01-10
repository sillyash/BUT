public class Minus extends BinaryOperator
{
	/**
	 * @param left
	 * @param right
	 */
	public Minus(Expression left, Expression right) {
		super(left, right);
	}

	/**
	 * @return 
	 */
	@Override
	public String getOperator() {
		return "-";
	}

	/**
	 * @return 
	 */
	@Override
	public double getValue() {
		return this.leftOperand.getValue() - this.rightOperand.getValue();
	}
}