package model.expressions;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Parser;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprCol extends Expression {
    Expression myOperand1;
    Expression myOperand2;
    Expression myOperand3;

    public ExprCol() {
        super(Pattern.compile("\\((color)"));
    }

    public ExprCol(Expression operand1, Expression operand2, Expression operand3) {
        super(Pattern.compile("\\((color)"));
        myOperand1 = operand1;
        myOperand2 = operand2;
        myOperand3 = operand3;
    }

    public RGBColor evaluate(double x, double y) {
        RGBColor red = myOperand1.evaluate(x, y);
        RGBColor green = myOperand2.evaluate(x, y);
        RGBColor blue = myOperand3.evaluate(x, y);
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