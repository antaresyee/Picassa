package model.expressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprY extends Expression {
    static Pattern myPattern = Pattern.compile("y");
        
    public ExprY() {
        super(myPattern);
    }

    public RGBColor evaluate(double x, double y, double currentTime) {
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