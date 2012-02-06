package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;

import model.RGBColor;
import model.Parser.ParserState;

public class ExprSum extends Expression {
    ArrayList<Expression> myOperands;
    static Pattern myPattern = Pattern.compile("\\((sum)");

    public ExprSum() {
        super(myPattern);
    }

    public ExprSum(ArrayList<Expression> operands) {
        super(myPattern);
        myOperands = operands;
    }

    @Override
    public RGBColor evaluate(double x, double y, double currentTime) {
        Double r = 0.0;
        Double g = 0.0;
        Double b = 0.0;

        for (Expression e : myOperands) {
            RGBColor RGBc = e.evaluate(x, y, currentTime);
            r += RGBc.getRed();
            g += RGBc.getGreen();
            b += RGBc.getBlue();
        }
        return new RGBColor(r, g, b);
    }

    @Override
    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operands = super.parseOperands(ps);
        return new ExprSum(operands);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(sum");
        for (Expression e : myOperands) {
            sb.append(e.toString());
        }
        sb.append(")");
        return sb.toString();
    }

}
