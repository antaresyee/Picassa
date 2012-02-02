package model.expressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Parser;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprX extends Expression {
    // regular expression for x

    public ExprX() {
        super(Pattern.compile("x"));
    }

    public RGBColor evaluate(double x, double y) {
        return new RGBColor((double) x);
    }

    public Expression parseExpression(ParserState ps) {
        Matcher varMatcher = super.myRegexPattern.matcher(ps.getInput());
        varMatcher.find(ps.getCurrentPosition());
        ps.setPosition(varMatcher.end());
        return new ExprX();
    }

    /**
     * Returns string representation of expression
     */
    public String toString() {
        return "x";
    }
}