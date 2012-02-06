package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;

import model.RGBColor;
import model.Parser.ParserState;

public class ExprProd extends Expression {

ArrayList<Expression> myOperands;
    
    public ExprProd() {
        super(Pattern.compile("\\((product)"));
    }

    public ExprProd(ArrayList<Expression> operands) {
        super(Pattern.compile("\\((product)"));
        myOperands = operands;
    }
    
    @Override
    public RGBColor evaluate(double x, double y, double currentTime) {
        Double r = 1.0;
        Double g = 1.0;
        Double b = 1.0;
        
        for (Expression e : myOperands) {
            RGBColor RGBc = e.evaluate(x,y, currentTime);
            r *= RGBc.getRed();
            g *= RGBc.getGreen();
            b *= RGBc.getBlue();
        }       
        return new RGBColor(r,g,b);
    }

    @Override
    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operands = super.parseOperands(ps);
        return new ExprProd(operands);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(product");
        for (Expression e : myOperands) {
            sb.append(e.toString());
        }
        sb.append(")");
        return sb.toString();
    }

}
