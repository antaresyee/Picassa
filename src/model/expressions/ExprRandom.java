package model.expressions;

import java.util.Random;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprRandom extends Expression {

    public ExprRandom() {
        super(Pattern.compile("\\((random)"));
    }

    public RGBColor evaluate(double x, double y, double currentTime) {
        return new RGBColor(generateRandom(), generateRandom(),
                generateRandom());
    }

    public Expression parseExpression(ParserState ps) {
        super.parseOperands(ps);
        return new ExprRandom();
    }

    public String toString() {
        return "(random)";
    }

    /**
     * Generates random number between -1 and 1
     */
    public double generateRandom() {
        Random r = new Random();
        double d = r.nextDouble();

        // 50% chance of generating a negative
        if (r.nextDouble() <= .5)
            return -d;
        return d;
    }

}
