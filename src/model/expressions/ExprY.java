package model.expressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Parser;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprY extends Expression {

    public ExprY() {
        // update myRegexPattern pattern of super
        super(Pattern.compile("y"));
    }

    public ExprY(Expression operand1, Expression operand2) {
        super(Pattern.compile("y"));
    }

    public RGBColor evaluate(double x, double y) {
        return new RGBColor((double) y);
    }

    public Expression parseExpression(ParserState ps) {
        Matcher varMatcher = super.myRegexPattern.matcher(ps.getInput());
        varMatcher.find(ps.getCurrentPosition());
        ps.setPosition(varMatcher.end());
        return new ExprY();
    }

    /**
     * Returns string representation of expression
     */
    public String toString() {
        return "y";
    }
}