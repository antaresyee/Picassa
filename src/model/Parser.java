package model;

import java.util.ArrayList;

import model.expressions.ExprAbs;
import model.expressions.ExprAtan;
import model.expressions.ExprAvg;
import model.expressions.ExprCeil;
import model.expressions.ExprClamp;
import model.expressions.ExprCol;
import model.expressions.ExprCos;
import model.expressions.ExprDiv;
import model.expressions.ExprExp;
import model.expressions.ExprFloor;
import model.expressions.ExprIf;
import model.expressions.ExprLet;
import model.expressions.ExprLog;
import model.expressions.ExprMax;
import model.expressions.ExprMin;
import model.expressions.ExprMinus;
import model.expressions.ExprMod;
import model.expressions.ExprMul;
import model.expressions.ExprNeg;
import model.expressions.ExprNum;
import model.expressions.ExprPerlinBW;
import model.expressions.ExprPerlinColor;
import model.expressions.ExprPlus;
import model.expressions.ExprProd;
import model.expressions.ExprRandom;
import model.expressions.ExprRgbToYCrCb;
import model.expressions.ExprSin;
import model.expressions.ExprSum;
import model.expressions.ExprT;
import model.expressions.ExprTan;
import model.expressions.ExprTemp;
import model.expressions.ExprWrap;
import model.expressions.ExprX;
import model.expressions.ExprY;
import model.expressions.ExprYCrCbtoRGB;
import model.expressions.Expression;

/**
 * Parses a string into an expression tree based on rules for arithmetic.
 * 
 * Due to the nature of the language being parsed, a recursive descent parser is
 * used http://en.wikipedia.org/wiki/Recursive_descent_parser
 * 
 * @author Robert C. Duvall, Antares Yee
 */
public class Parser {
    static ArrayList<Expression> allExpressions;

    public class ParserState {
        // A ParserState object holds the state of Parser
        private String myInput;
        private int myCurrentPosition;
        private Parser myParser;

        public ParserState(String input, Parser p) {
            myInput = input;
            myCurrentPosition = 0;
            myParser = p;
        }

        public int getCurrentPosition() {
            return myCurrentPosition;
        }

        public void setPosition(int i) {
            myCurrentPosition = i;
        }

        public String getInput() {
            return myInput;
        }

        // Utility Functions
        public void skipWhiteSpace() {
            while (notAtEndOfString()
                    && Character.isWhitespace(currentCharacter())) {
                myCurrentPosition++;
            }
        }

        public int currentCharacter() {
            return myInput.charAt(myCurrentPosition);
        }

        public boolean notAtEndOfString() {
            return myCurrentPosition < myInput.length();
        }

        // TODO: GET THE FOLLOWING 2 METHODS TO WORK.
        public void setExprTemp(ExprTemp et) {
            myParser.addExpr(et);
        }

        public void removeExpr(String commandName) {
            myParser.removeExpr(commandName);
        }
    }

    public Parser() {
        allExpressions = new ArrayList<Expression>();
        allExpressions.add(new ExprNum());
        allExpressions.add(new ExprX());
        allExpressions.add(new ExprY());
        allExpressions.add(new ExprPlus());
        allExpressions.add(new ExprMinus());
        allExpressions.add(new ExprMul());
        allExpressions.add(new ExprDiv());
        allExpressions.add(new ExprExp());
        allExpressions.add(new ExprMod());
        allExpressions.add(new ExprNeg());
        allExpressions.add(new ExprCol());
        allExpressions.add(new ExprRandom());
        allExpressions.add(new ExprFloor());
        allExpressions.add(new ExprCeil());
        allExpressions.add(new ExprAbs());
        allExpressions.add(new ExprSin());
        allExpressions.add(new ExprCos());
        allExpressions.add(new ExprTan());
        allExpressions.add(new ExprAtan());
        allExpressions.add(new ExprLog());
        allExpressions.add(new ExprClamp());
        allExpressions.add(new ExprWrap());
        allExpressions.add(new ExprRgbToYCrCb());
        allExpressions.add(new ExprYCrCbtoRGB());
        allExpressions.add(new ExprPerlinColor());
        allExpressions.add(new ExprPerlinBW());
        allExpressions.add(new ExprLet());
        allExpressions.add(new ExprSum());
        allExpressions.add(new ExprProd());
        allExpressions.add(new ExprAvg());
        allExpressions.add(new ExprMin());
        allExpressions.add(new ExprMax());
        allExpressions.add(new ExprIf());
        allExpressions.add(new ExprT());
    }

    public void addExpr(ExprTemp et) {
        allExpressions.add(et);
    }

    public void removeExpr(String commandName) {
        Expression toRemove = null;
        for (Expression e : allExpressions) {
            if (e.isExpression(commandName)) {
                toRemove = e;
                break;
            }
        }
        allExpressions.remove(toRemove);
    }

    /**
     * Converts given string into expression tree.
     * 
     * @param input
     *            expression given in the language to be parsed
     * @return expression tree representing the given formula
     */
    public Expression makeExpression(String input) {
        // ps stores the state of the parser
        ParserState ps = new ParserState(input, this);

        Expression result = parseInput(ps);
        ps.skipWhiteSpace();
        if (ps.notAtEndOfString()) {
            throw new ParserException(
                    "Unexpected characters at end of the string: "
                            + ps.myInput.substring(ps.myCurrentPosition),
                    ParserException.Type.EXTRA_CHARACTERS);
        }
        return result;
    }

    /**
     * Calls isExpression() for each, then calls appropriate parseExpression()
     */
    public static Expression parseInput(ParserState ps) {
        ps.skipWhiteSpace();
        Expression parsed = null;
        for (Expression e : allExpressions) {
            if (e.isExpression(ps)) {
                parsed = e.parseExpression(ps);
                break;
            }
        }
        return parsed;
    }
}
