package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprSin extends Expression {
    Expression myOperand1;
    static Pattern myPattern = Pattern.compile("\\((sin)");

    public ExprSin() {
        super(myPattern);
    }

    public ExprSin(Expression operand1) {
        super(myPattern);
        myOperand1 = operand1;
    }

    public RGBColor evaluate(double x, double y, double currentTime) {
        RGBColor eval = myOperand1.evaluate(x, y, currentTime);
        return new RGBColor(Math.sin(eval.getRed()),
                Math.sin((eval.getBlue())), Math.sin((eval.getGreen())));
    }

    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operandList = super.parseOperands(ps);
        return new ExprSin(operandList.get(0));
    }

    public String toString() {
        return "(sin " + myOperand1.toString() + ")";
    }
}
