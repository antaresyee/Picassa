package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprLog extends Expression {

    Expression myOperand1;

    public ExprLog() {
        super(Pattern.compile("\\((log)"));
    }

    public ExprLog(Expression operand1) {
        super(Pattern.compile("\\((log)"));
        myOperand1 = operand1;
    }

    public RGBColor evaluate(double x, double y, double currentTime) {
        RGBColor eval = myOperand1.evaluate(x, y, currentTime);
        return new RGBColor(Math.log(eval.getRed()),
                Math.log((eval.getBlue())), Math.log((eval.getGreen())));
    }

    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operandList = super.parseOperands(ps);
        return new ExprLog(operandList.get(0));
    }

    public String toString() {
        return "(log " + myOperand1.toString() + ")";
    }

}
