package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprCol extends Expression {
    Expression myOperand1;
    Expression myOperand2;
    Expression myOperand3;
    static Pattern myPattern = Pattern.compile("\\((color)");

    public ExprCol() {
        super(myPattern);
    }

    public ExprCol(Expression operand1, Expression operand2, Expression operand3) {
        super(myPattern);
        myOperand1 = operand1;
        myOperand2 = operand2;
        myOperand3 = operand3;
    }

    public RGBColor evaluate(double x, double y, double currentTime) {
        RGBColor red = myOperand1.evaluate(x, y, currentTime);
        RGBColor green = myOperand2.evaluate(x, y, currentTime);
        RGBColor blue = myOperand3.evaluate(x, y, currentTime);
        return new RGBColor(red.getRed(), green.getGreen(), blue.getBlue());
    }

    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operandList = super.parseOperands(ps);
        return new ExprCol(operandList.get(0), operandList.get(1),
                operandList.get(2));
    }

    /**
     * Returns string representation of expression
     */
    public String toString() {
        return "(plus " + myOperand1.toString() + " " + myOperand2.toString()
                + " " + myOperand3.toString() + ")";
    }
}