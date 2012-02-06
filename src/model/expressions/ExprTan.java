package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;

import model.Parser;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprTan extends Expression {

    Expression myOperand1;

    public ExprTan() {
        super(Pattern.compile("\\((tan)"));
    }

    public ExprTan(Expression operand1) {
        super(Pattern.compile("\\((tan)"));
        myOperand1 = operand1;
    }

    public RGBColor evaluate(double x, double y, double currentTime) {
        RGBColor eval = myOperand1.evaluate(x, y, currentTime);
        return new RGBColor(Math.tan(eval.getRed()),
                Math.tan((eval.getBlue())), Math.tan((eval.getGreen())));
    }

    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operandList = super.parseOperands(ps);
        return new ExprTan(operandList.get(0));
    }

    public String toString() {
        return "(tan " + myOperand1.toString() + ")";
    }

}
