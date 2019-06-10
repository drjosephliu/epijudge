package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Deque;
import java.util.ArrayDeque;
public class EvaluateRpn {
    @EpiTest(testDataFile = "evaluate_rpn.tsv")

    public static int eval(String expression) {
        
        String[] expArray = expression.split(",");
        Deque<Integer> stack = new ArrayDeque<>();

        for (String exp : expArray) {

            if (isNumber(exp)) {
                stack.push(Integer.parseInt(exp));
            }
            else {
                Integer firstNum = stack.pop();
                Integer secondNum = stack.pop();
                switch (exp) {
                    case "+":
                        stack.push(firstNum + secondNum);
                        break;
                    case "-":
                        stack.push(secondNum - firstNum);
                        break;
                    case "*":
                        stack.push(firstNum * secondNum);
                        break;
                    case "/":
                        stack.push(secondNum / firstNum);
                        break;
                }
            }
        }

        return stack.pop();
    }
    // public static int eval(String expression) {
    //   // TODO - you fill in here.
    //   String[] expArray = expression.split(",");
    //   Deque<Integer> stack = new ArrayDeque<>();

    //   for (String exp : expArray) {
    //     if (isNumber(exp)) {
    //       stack.push(Integer.parseInt(exp));
    //     }
    //     else {
    //       Integer operand1 = stack.pop();
    //       Integer operand2 = stack.pop();
    //       Integer result = 0;

    //       if (exp.equals("+")) {
    //         result = operand2 + operand1;
    //       }
    //       else if (exp.equals("-")) {
    //         result = operand2 - operand1;
    //       }
    //       else if (exp.equals("*")) {
    //         result = operand2 * operand1;
    //       }
    //       else if (exp.equals("/")) {
    //         result = operand2 / operand1;
    //       }
    //       // System.out.println(result);
    //       stack.push(result);
    //     }
    //   }
    //   return stack.pop();
    // }

    public static boolean isNumber(String strNum) {
        try {
            Integer.parseInt(strNum);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                .runFromAnnotations(args, "EvaluateRpn.java",
                    new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
