package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprCos extends Expression {
    Expression myOperand1;
    static Pattern myPattern = Pattern.compile("\\((cos)");

    public ExprCos() {
        super(myPattern);
    }

    public ExprCos(Expression operand1) {
        super(myPattern);
        myOperand1 = operand1;
    }

    public RGBColor evaluate(double x, double y, double currentTime) {
        RGBColor eval = myOperand1.evaluate(x, y, currentTime);
        return new RGBColor(Math.cos(eval.getRed()),
                Math.cos((eval.getBlue())), Math.cos((eval.getGreen())));
    }

    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operandList = super.parseOperands(ps);
        return new ExprCos(operandList.get(0));
    }

    public String toString() {
        return "(cos " + myOperand1.toString() + ")";
    }

}
