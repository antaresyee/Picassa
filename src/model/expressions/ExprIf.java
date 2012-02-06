package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;

import model.RGBColor;
import model.Parser.ParserState;

public class ExprIf extends Expression {
    Expression myOperand1;
    Expression myOperand2;
    Expression myOperand3;

    public ExprIf() {
        super(Pattern.compile("\\((if)"));
    }

    public ExprIf(Expression operand1, Expression operand2, Expression operand3) {
        super(Pattern.compile("\\((if)"));
        myOperand1 = operand1;
        myOperand2 = operand2;
        myOperand3 = operand3;
    }

    public RGBColor evaluate(double x, double y, double currentTime) {
        RGBColor o1 = myOperand1.evaluate(x, y, currentTime);
        RGBColor o2 = myOperand2.evaluate(x, y, currentTime);
        RGBColor o3 = myOperand3.evaluate(x, y, currentTime);

        if ((o1.getRed() + o1.getGreen() + o1.getBlue()) / 3.0 > 0)
            return o2;
        return o3;
    }

    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operandList = super.parseOperands(ps);
        return new ExprIf(operandList.get(0), operandList.get(1),
                operandList.get(2));
    }

    /**
     * Returns string representation of expression
     */
    public String toString() {
        return "(if " + myOperand1.toString() + " " + myOperand2.toString()
                + " " + myOperand3.toString() + ")";
    }
}
