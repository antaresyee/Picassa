package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprClamp extends Expression {
    Expression myOperand1;
    static Pattern myPattern = Pattern.compile("\\((clamp)");

    public ExprClamp() {
        super(myPattern);
    }

    public ExprClamp(Expression operand1) {
        super(myPattern);
        myOperand1 = operand1;
    }

    public RGBColor evaluate(double x, double y, double currentTime) {
        RGBColor eval = myOperand1.evaluate(x, y, currentTime);
        return new RGBColor(clamp(eval.getRed()), clamp((eval.getBlue())),
                clamp((eval.getGreen())));
    }

    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operandList = super.parseOperands(ps);
        return new ExprClamp(operandList.get(0));
    }

    public String toString() {
        return "(clamp " + myOperand1.toString() + ")";
    }

    /**
     * Clamps a value to [-1,1]
     */
    public double clamp(double i) {
        return Math.min(Math.max(i, -1), 1);
    }

}
