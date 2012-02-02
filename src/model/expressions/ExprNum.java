package model.expressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Parser;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprNum extends Expression {
    RGBColor myValue;

    public ExprNum() {
        super(Pattern.compile("(\\-?[0-9]+(\\.[0-9]+)?)|(\\.[0-9]+)"));
    }

    public ExprNum(RGBColor gray) {
        super(Pattern.compile("(\\-?[0-9]+(\\.[0-9]+)?)|(\\.[0-9]+)"));
        myValue = gray;

    }

    public RGBColor evaluate(double x, double y) {
        return myValue;
    }

    public Expression parseExpression(ParserState ps) {
        Matcher doubleMatcher = super.myRegexPattern.matcher(ps.getInput());
        doubleMatcher.find(ps.getCurrentPosition());
        String numberMatch = ps.getInput().substring(doubleMatcher.start(),
                doubleMatcher.end());
        ps.setPosition(doubleMatcher.end());

        // this represents the color gray of the given intensity
        double value = Double.parseDouble(numberMatch);
        RGBColor gray = new RGBColor(value);
        return new ExprNum(gray);

    }

    /**
     * Returns string representation of expression
     */
    public String toString() {
        return myValue.toString();
    }
}