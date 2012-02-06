package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser.ParserState;
import model.util.PerlinNoise;

public class ExprPerlinBW extends Expression {
    Expression myOperand1;
    Expression myOperand2;
    static Pattern myPattern = Pattern.compile("\\((perlinBW)");

    public ExprPerlinBW() {
        super(myPattern);
    }

    public ExprPerlinBW(Expression operand1, Expression operand2) {
        super(myPattern);
        myOperand1 = operand1;
        myOperand2 = operand2;
    }

    public RGBColor evaluate(double x, double y, double currentTime) {
        RGBColor left = myOperand1.evaluate(x, y, currentTime);
        RGBColor right = myOperand2.evaluate(x, y, currentTime);
        return greyNoise(left, right);
    }

    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operandList = super.parseOperands(ps);
        return new ExprPerlinBW(operandList.get(0), operandList.get(1));
    }

    /**
     * Returns string representation of expression
     */
    public String toString() {
        return "(perlinBW " + myOperand1.toString() + " "
                + myOperand2.toString() + ")";
    }

    /**
     * Use noise to produce a grey scale color.
     */
    public static RGBColor greyNoise(RGBColor left, RGBColor right) {
        return new RGBColor(PerlinNoise.noise(left.getRed() + right.getRed(),
                left.getGreen() + right.getGreen(),
                left.getBlue() + right.getBlue()));
    }
}
