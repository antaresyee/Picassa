package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;

import model.RGBColor;
import model.Parser.ParserState;

public class ExprMin extends Expression {
    ArrayList<Expression> myOperands;
    static Pattern myPattern = Pattern.compile("\\((min)");

    public ExprMin() {
        super(myPattern);
    }

    public ExprMin(ArrayList<Expression> operands) {
        super(myPattern);
        myOperands = operands;
    }

    @Override
    public RGBColor evaluate(double x, double y, double currentTime) {
        ArrayList<Double> r = new ArrayList<Double>();
        ArrayList<Double> g = new ArrayList<Double>();
        ArrayList<Double> b = new ArrayList<Double>();

        for (Expression e : myOperands) {
            RGBColor RGBc = e.evaluate(x, y, currentTime);
            r.add(RGBc.getRed());
            g.add(RGBc.getGreen());
            b.add(RGBc.getBlue());
        }
        return new RGBColor(minimum(r), minimum(g), minimum(b));
    }

    @Override
    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operands = super.parseOperands(ps);
        return new ExprMin(operands);
    }

    public Double minimum(ArrayList<Double> list) {
        Double minD = list.get(0);
        for (int i = 1; i < list.size(); ++i) {
            if (list.get(i) < minD) {
                minD = list.get(i);
            }
        }
        return minD;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(min");
        for (Expression e : myOperands) {
            sb.append(e.toString());
        }
        sb.append(")");
        return sb.toString();
    }

}
