package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;

import model.RGBColor;
import model.Parser.ParserState;

public class ExprAvg extends Expression {

    ArrayList<Expression> myOperands;

    public ExprAvg() {
        super(Pattern.compile("\\((average)"));
    }

    public ExprAvg(ArrayList<Expression> operands) {
        super(Pattern.compile("\\((average)"));
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
        return new RGBColor(r / myOperands.size(), g / myOperands.size(), b
                / myOperands.size());
    }

    @Override
    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operands = super.parseOperands(ps);
        return new ExprAvg(operands);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(average");
        for (Expression e : myOperands) {
            sb.append(e.toString());
        }
        sb.append(")");
        return sb.toString();
    }

}
