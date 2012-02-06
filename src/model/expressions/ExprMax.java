package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;

import model.RGBColor;
import model.Parser.ParserState;

public class ExprMax extends Expression {

ArrayList<Expression> myOperands;
    
    public ExprMax() {
        super(Pattern.compile("\\((max)"));
    }

    public ExprMax(ArrayList<Expression> operands) {
        super(Pattern.compile("\\((max)"));
        myOperands = operands;
    }
    
    @Override
    public RGBColor evaluate(double x, double y, double currentTime) {
        ArrayList<Double> r = new ArrayList<Double>();
        ArrayList<Double> g = new ArrayList<Double>();
        ArrayList<Double> b = new ArrayList<Double>();
        
        for (Expression e : myOperands) {
            RGBColor RGBc = e.evaluate(x,y, currentTime);
            r.add(RGBc.getRed());
            g.add(RGBc.getGreen());
            b.add(RGBc.getBlue());
        }       
        return new RGBColor(maximum(r),maximum(g),maximum(b));
    }

    @Override
    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operands = super.parseOperands(ps);
        return new ExprMax(operands);
    }

    public Double maximum(ArrayList<Double> list) {
        Double maxD = list.get(0);
        for (int i=1; i<list.size(); ++i) {
            if (list.get(i) > maxD) {
                maxD = list.get(i);
            }
        }
        return maxD;
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
