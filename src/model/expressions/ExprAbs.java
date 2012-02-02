package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;

import model.Parser;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprAbs extends Expression {
    Expression myOperand1;

    public ExprAbs() {
        super(Pattern.compile("\\((abs)"));
    }

    public ExprAbs(Expression operand1) {
        super(Pattern.compile("\\((abs)"));
        myOperand1 = operand1;
    }

    public RGBColor evaluate(double x, double y) {
        RGBColor eval = myOperand1.evaluate(x, y);
        return new RGBColor(Math.abs(eval.getRed()),
                Math.abs((eval.getBlue())), Math.abs((eval.getGreen())));
    }

    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operandList = super.parseOperands(ps);
        return new ExprAbs(operandList.get(0));
    }

    public String toString() {
        return "(abs " + myOperand1.toString() + ")";
    }

}
