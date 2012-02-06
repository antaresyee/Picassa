package model.expressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Model;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprT extends Expression {

    public ExprT() {
        super(Pattern.compile("t"));
    }

    public RGBColor evaluate(double x, double y, double currentTime) {
        return new RGBColor(currentTime);
    }

    public Expression parseExpression(ParserState ps) {
        Matcher varMatcher = super.myRegexPattern.matcher(ps.getInput());
        varMatcher.find(ps.getCurrentPosition());
        ps.setPosition(varMatcher.end());
        return new ExprT();
    }

    /**
     * Returns string representation of expression
     */
    public String toString() {
        return "t";
    }

}
