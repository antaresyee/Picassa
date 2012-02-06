package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprWrap extends Expression {

    Expression myOperand1;

    public ExprWrap() {
        super(Pattern.compile("\\((wrap)"));
    }

    public ExprWrap(Expression operand1) {
        super(Pattern.compile("\\((wrap)"));
        myOperand1 = operand1;
    }

    public RGBColor evaluate(double x, double y, double currentTime) {
        RGBColor eval = myOperand1.evaluate(x, y, currentTime);
        return new RGBColor(wrap(eval.getRed()), wrap((eval.getBlue())),
                wrap((eval.getGreen())));
    }

    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operandList = super.parseOperands(ps);
        return new ExprWrap(operandList.get(0));
    }

    public String toString() {
        return "(wrap " + myOperand1.toString() + ")";
    }

    /**
     * Wraps a double around [-1,1]. (i.e., 1.5 -> -0.5). Algorithm: Nums > 1:
     * If i-1 >= 2, return -1 + ((i-1) % 2). Else return -1 + i. Nums < 1:
     * return -1 * answer from above.
     */
    public double wrap(double i) {
        if (i >= -1 && i <= 1)
            return i;

        // if positive
        if (i > 0) {
            if (i - 1 >= 2)
                return -1 + ((i - 1) % 2);
            return -1 + (i - 1);
        }

        // if negative
        if (-i - 1 >= 2)
            return -(-1 + ((-i - 1) % 2));
        return -(-1 + (-i - 1));
    }

}
