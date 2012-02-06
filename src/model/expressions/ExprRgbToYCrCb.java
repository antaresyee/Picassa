package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprRgbToYCrCb extends Expression {

    Expression myOperand1;

    public ExprRgbToYCrCb() {
        super(Pattern.compile("\\((rgbToYCrCb)"));
    }

    public ExprRgbToYCrCb(Expression operand1) {
        super(Pattern.compile("\\((rgbToYCrCb)"));
        myOperand1 = operand1;
    }

    public RGBColor evaluate(double x, double y, double currentTime) {
        RGBColor eval = myOperand1.evaluate(x, y, currentTime);
        return new RGBColor(eval.getRed() * 0.2989 + eval.getGreen() * 0.5866
                + eval.getBlue() * 0.1145, eval.getRed() * -0.1687
                + eval.getGreen() * -0.3312 + eval.getBlue() * 0.5,
                eval.getRed() * 0.5000 + eval.getGreen() * -0.4183
                        + eval.getBlue() * -0.0816);
    }

    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operandList = super.parseOperands(ps);
        return new ExprRgbToYCrCb(operandList.get(0));
    }

    public String toString() {
        return "(rgbToYCrCb " + myOperand1.toString() + ")";
    }

}
