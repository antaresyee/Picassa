package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprMod extends Expression {
    Expression myOperand1;
    Expression myOperand2;
    static Pattern myPattern = Pattern.compile("\\((%)|\\((mod)");

    public ExprMod() {
        super(myPattern);
    }

    public ExprMod(Expression operand1, Expression operand2) {
        super(myPattern);
        myOperand1 = operand1;
        myOperand2 = operand2;
    }

    public RGBColor evaluate(double x, double y, double currentTime) {
        RGBColor left = myOperand1.evaluate(x, y, currentTime);
        RGBColor right = myOperand2.evaluate(x, y, currentTime);
        return new RGBColor(left.getRed() % right.getRed(), left.getGreen()
                % right.getGreen(), left.getBlue() % right.getBlue());
    }

    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operandList = super.parseOperands(ps);
        return new ExprMod(operandList.get(0), operandList.get(1));
    }

    /**
     * Returns string representation of expression
     */
    public String toString() {
        return "(mod " + myOperand1.toString() + " " + myOperand2.toString()
                + ")";
    }
}