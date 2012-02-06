package model.expressions;

import java.util.ArrayList;
import java.util.regex.Matcher;

import java.util.regex.Pattern;

import model.Parser;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprMinus extends Expression {
    Expression myOperand1;
    Expression myOperand2;

    public ExprMinus() {
        super(Pattern.compile("\\((-)|\\((minus)"));
    }

    public ExprMinus(Expression operand1, Expression operand2) {
        super(Pattern.compile("\\((-)|\\((minus)"));
        myOperand1 = operand1;
        myOperand2 = operand2;
    }

    public RGBColor evaluate(double x, double y, double currentTime) {
        RGBColor left = myOperand1.evaluate(x, y, currentTime);
        RGBColor right = myOperand2.evaluate(x, y, currentTime);
        return new RGBColor(left.getRed() - right.getRed(), left.getGreen()
                - right.getGreen(), left.getBlue() - right.getBlue());
    }

    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operandList = super.parseOperands(ps);
        return new ExprMinus(operandList.get(0), operandList.get(1));
    }

    /**
     * Returns string representation of expression
     */
    public String toString() {
        return "(minus " + myOperand1.toString() + " " + myOperand2.toString()
                + ")";
    }
}