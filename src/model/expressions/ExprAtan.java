package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprAtan extends Expression {
    Expression myOperand1;
    static Pattern myPattern = Pattern.compile("\\((atan)");

    public ExprAtan() {
        super(myPattern);
    }

    public ExprAtan(Expression operand1) {
        super(myPattern);
        myOperand1 = operand1;
    }

    public RGBColor evaluate(double x, double y, double currentTime) {
        RGBColor eval = myOperand1.evaluate(x, y, currentTime);
        return new RGBColor(Math.atan(eval.getRed()),
                Math.atan((eval.getBlue())), Math.atan((eval.getGreen())));
    }

    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operandList = super.parseOperands(ps);
        return new ExprAtan(operandList.get(0));
    }

    public String toString() {
        return "(atan " + myOperand1.toString() + ")";
    }

}
