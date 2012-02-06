package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;

import model.Parser;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprNeg extends Expression {
    Expression myOperand1;

    public ExprNeg() {
        super(Pattern.compile("\\((!)|\\((neg)"));
    }

    public ExprNeg(Expression operand1) {
        super(Pattern.compile("\\((!)|\\((neg)"));
        myOperand1 = operand1;
    }

    public RGBColor evaluate(double x, double y, double currentTime) {
        RGBColor left = myOperand1.evaluate(x, y, currentTime);
        return new RGBColor(-left.getRed(), -left.getGreen(), -left.getBlue());
    }

    /**
     * Parses expression based on given ParserState.
     * 
     */
    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operandList = super.parseOperands(ps);
        return new ExprNeg(operandList.get(0));
    }

    /**
     * Returns string representation of expression
     */
    public String toString() {
        return "(neg " + myOperand1.toString() + ")";
    }
}