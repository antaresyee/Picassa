package model.expressions;

import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Parser;
import model.ParserException;
import model.RGBColor;
import model.Parser.ParserState;

/**
 * An Expression represents a mathematical expression as a tree.
 * 
 * In this format, the internal nodes represent mathematical functions and the
 * leaves represent constant values.
 * 
 * @author Robert C. Duvall, Antares Yee
 */
public abstract class Expression {
    public Pattern myRegexPattern;

    /**
     * Create expression
     */
    public Expression(Pattern regexPattern) {
        myRegexPattern = regexPattern;
    }

    /**
     * Returns value of expression
     */
    public abstract RGBColor evaluate(double x, double y, double currentTime);

    /**
     * Returns true if expression is an expression of a certain subclass.
     */
    public boolean isExpression(ParserState ps) {
        Matcher expMatcher = myRegexPattern.matcher(ps.getInput().substring(
                ps.getCurrentPosition()));
        return expMatcher.lookingAt();
    }

    public boolean isExpression(String commandName) {
        Matcher expMatcher = myRegexPattern.matcher(commandName);
        return expMatcher.lookingAt();
    }

    /**
     * Parses input; algorithm is specific to each subclass.
     */
    public abstract Expression parseExpression(ParserState ps);

    /**
     * This method called from each subclass' parseExpression() and returns a
     * list of operands.
     */
    public ArrayList<Expression> parseOperands(ParserState ps) {
        // match commandName and update current position
        Matcher expMatcher = myRegexPattern.matcher(ps.getInput());
        expMatcher.find(ps.getCurrentPosition());
        ps.setPosition(expMatcher.end());
        ps.skipWhiteSpace();

        // parse operands recursively
        ArrayList<Expression> operandlist = new ArrayList<Expression>();
        while (ps.currentCharacter() != ')') {
            operandlist.add(Parser.parseInput(ps));
            ps.skipWhiteSpace();
        }
        if (ps.currentCharacter() == ')') {
            ps.setPosition(ps.getCurrentPosition() + 1);
            return operandlist;
        } else {
            throw new ParserException("Expected close paren, instead found "
                    + ps.getInput().substring(ps.getCurrentPosition()));
        }

    }

    /**
     * Returns string representation of expression
     */
    public abstract String toString();
}
