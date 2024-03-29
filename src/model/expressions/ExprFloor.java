package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprFloor extends Expression {
    Expression myOperand1;
    static Pattern myPattern = Pattern.compile("\\((floor)");

    public ExprFloor() {
        super(myPattern);
    }

    public ExprFloor(Expression operand1) {
        super(myPattern);
        myOperand1 = operand1;
    }

    public RGBColor evaluate(double x, double y, double currentTime) {
        RGBColor eval = myOperand1.evaluate(x, y, currentTime);
        return new RGBColor(floorDouble(eval.getRed()),
                floorDouble(eval.getBlue()), floorDouble(eval.getGreen()));
    }

    public Expression parseExpression(ParserState ps) {
        ArrayList<Expression> operandList = super.parseOperands(ps);
        return new ExprFloor(operandList.get(0));
    }

    public String toString() {
        return "(floor " + myOperand1.toString() + ")";
    }

    public double floorDouble(double d) {
        return Math.floor(d);
    }

}
