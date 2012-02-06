package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprCeil extends Expression {
    Expression myOperand1;

    public ExprCeil() {
        super(Pattern.compile("\\((ceil)"));
    }

    public ExprCeil(Expression operand1) {
        super(Pattern.compile("\\((ceil)"));
        myOperand1 = operand1;
    }

    public RGBColor evaluate(double x, double y, double currentTime) {
        RGBColor eval = myOperand1.evaluate(x, y, currentTime);
        return new RGBColor(ceilDouble(eval.getRed()),
                ceilDouble(eval.getBlue()), ceilDouble(eval.getGreen()));
    }

    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operandList = super.parseOperands(ps);
        return new ExprCeil(operandList.get(0));
    }

    public String toString() {
        return "(ceil " + myOperand1.toString() + ")";
    }

    public double ceilDouble(double d) {
        return Math.ceil(d);
    }

}