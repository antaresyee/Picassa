package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprAbs extends Expression {
    Expression myOperand1;
    static Pattern myPattern = Pattern.compile("\\((abs)");

    public ExprAbs() {
        super(myPattern);
    }

    public ExprAbs(Expression operand1) {
        super(myPattern);
        myOperand1 = operand1;
    }

    public RGBColor evaluate(double x, double y, double currentTime) {
        RGBColor eval = myOperand1.evaluate(x, y, currentTime);
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
