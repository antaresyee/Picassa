package model.expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;

import model.Parser;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprFloor extends Expression {
    Expression myOperand1;

    public ExprFloor() {
        super(Pattern.compile("\\((floor)"));
    }

    public ExprFloor(Expression operand1) {
        super(Pattern.compile("\\((floor)"));
        myOperand1 = operand1;
    }

    public RGBColor evaluate(double x, double y) {
        RGBColor eval = myOperand1.evaluate(x, y);
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
