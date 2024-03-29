package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprExp extends Expression {
    Expression myOperand1;
    Expression myOperand2;
    static Pattern myPattern = Pattern.compile("\\((\\^)|\\((exp)");

    public ExprExp() {
        super(myPattern);
    }

    public ExprExp(Expression operand1, Expression operand2) {
        super(myPattern);
        myOperand1 = operand1;
        myOperand2 = operand2;
    }

    public RGBColor evaluate(double x, double y, double currentTime) {
        RGBColor left = myOperand1.evaluate(x, y, currentTime);
        RGBColor right = myOperand2.evaluate(x, y, currentTime);
        return new RGBColor(Math.pow(left.getRed(), right.getRed()), Math.pow(
                left.getGreen(), right.getGreen()), Math.pow(left.getBlue(),
                right.getBlue()));
    }

    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operandList = super.parseOperands(ps);
        return new ExprExp(operandList.get(0), operandList.get(1));
    }

    /**
     * Returns string representation of expression
     */
    public String toString() {
        return "(exp " + myOperand1.toString() + " " + myOperand2.toString()
                + ")";
    }
}