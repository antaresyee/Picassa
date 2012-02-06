package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser.ParserState;
import model.util.PerlinNoise;

public class ExprPerlinColor extends Expression {
    Expression myOperand1;
    Expression myOperand2;
    static Pattern myPattern = Pattern.compile("\\((perlinColor)");

    public ExprPerlinColor() {
        super(myPattern);
    }

    public ExprPerlinColor(Expression operand1, Expression operand2) {
        super(myPattern);
        myOperand1 = operand1;
        myOperand2 = operand2;
    }

    public RGBColor evaluate(double x, double y, double currentTime) {
        RGBColor left = myOperand1.evaluate(x, y, currentTime);
        RGBColor right = myOperand2.evaluate(x, y, currentTime);
        return colorNoise(left, right);
    }

    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operandList = super.parseOperands(ps);
        return new ExprPerlinColor(operandList.get(0), operandList.get(1));
    }

    /**
     * Returns string representation of expression
     */
    public String toString() {
        return "(perlinColor " + myOperand1.toString() + " "
                + myOperand2.toString() + ")";
    }

    /**
     * Use noise to produce a color.
     */
    public static RGBColor colorNoise(RGBColor left, RGBColor right) {
        return new RGBColor(PerlinNoise.noise(left.getRed() + 0.3,
                right.getRed() + 0.3, 0), PerlinNoise.noise(
                left.getGreen() - 0.8, right.getGreen() - 0.8, 0),
                PerlinNoise.noise(left.getBlue() + 0.1, right.getBlue() + 0.1,
                        0));
    }
}
