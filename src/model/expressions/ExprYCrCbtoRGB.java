package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprYCrCbtoRGB extends Expression {

    Expression myOperand1;

    public ExprYCrCbtoRGB() {
        super(Pattern.compile("\\((yCrCbtoRGB)"));
    }

    public ExprYCrCbtoRGB(Expression operand1) {
        super(Pattern.compile("\\((yCrCbtoRGB)"));
        myOperand1 = operand1;
    }

    public RGBColor evaluate(double x, double y, double currentTime) {
        RGBColor eval = myOperand1.evaluate(x, y, currentTime);
        return new RGBColor(eval.getRed() + eval.getBlue() * 1.4022,
                eval.getRed() + eval.getGreen() * -0.3456 + eval.getBlue()
                        * -0.7145, eval.getRed() + eval.getGreen() * 1.7710);
    }

    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operandList = super.parseOperands(ps);
        return new ExprYCrCbtoRGB(operandList.get(0));
    }

    public String toString() {
        return "(yCrCbtoRGB " + myOperand1.toString() + ")";
    }

}
