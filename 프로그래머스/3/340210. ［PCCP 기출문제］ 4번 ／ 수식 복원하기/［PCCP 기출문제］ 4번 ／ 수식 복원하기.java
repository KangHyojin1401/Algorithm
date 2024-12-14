import java.util.*;

class Solution {
    // boolean[] base = new boolean[10]; //진법
    
    public String[] solution(String[] expressions) {
        // StringBuilder sb = new StringBuilder();
        
        String[] answer = new String[expressions.length];
        int answerIdx = 0;
        
        String[] Xs = new String[expressions.length]; //답이 X인 표현식들
        int XsIdx = 0;
        
        int maxNum = -1;
        int exactBase = -1; //특정되는 진수
        
        for (int i = 0; i < expressions.length; i++) {
            //표현식 분리
            String[] expression = expressions[i].split(" ");
            int op1 = Integer.parseInt(expression[0]);
            int op2 = Integer.parseInt(expression[2]);
            int result;
            if (expression[4].equals("X")) {
                result = -1;
                Xs[XsIdx++] = expressions[i];
            } else {
                result = Integer.parseInt(expression[4]);
            }
            
            
            //가장 큰 숫자 찾아서 가능한 진법의 범위 줄이기
            maxNum = Math.max(maxNum, findMaxNumber(op1));
            maxNum = Math.max(maxNum, findMaxNumber(op2));
            maxNum = Math.max(maxNum, findMaxNumber(result));

            //식이 틀렸으면 진법 유추할 가능성 생김
            if (result != -1 && !isCalcRight(op1, expression[1], op2, result)) {
                if (expression[1].equals("+")) {
                    //일의 자리
                    if ((op1 % 10 + op2 % 10) % 10 != result % 10) { //일의 자리 더한 게 다르면 1 올려줌
                        exactBase = (op1 % 10 + op2 % 10) % 10 - result % 10;
                    } else {
                        //십의 자리
                        if ((op1 / 10 + op2 / 10) % 10 != (result / 10) % 10) { 
                            exactBase = (op1 / 10 + op2 / 10) % 10 - (result / 10) % 10;
                        } 
                    }
                } else { // - 일 때
                    //일의 자리
                    if ((op1 % 10 + 10) - op2 % 10 != result % 10) { //일의 자리 뺀 게 다르면
                        exactBase = -(op1 % 10) + (op2 % 10) + (result % 10);
                    } else {
                        //십의 자리
                        if ((op1 / 10 + op2 / 10) % 10 != (result / 10) % 10) { 
                            exactBase = -(op1 / 10) + (op2 / 10) + (result / 10) % 10;
                        } 
                    }
                }
            }
        }
        
        // System.out.println(Arrays.toString(Xs));
        
        
        if (maxNum == 8) {
            exactBase = 9;
        }
        for (int i = 0; i < XsIdx; i++) {
            //표현식 분리
            String[] expression = Xs[i].split(" ");
            int op1 = Integer.parseInt(expression[0]);
            int op2 = Integer.parseInt(expression[2]);
            
        
            if (exactBase == -1) {
                int prevResult = -1;
                int curResult = 0;
                boolean isBreaked = false;
                    
                // base 배열 돌리기
                int base;
                for (base = maxNum + 1; base < 10; base++) {
                    curResult = calcExpression(op1, expression[1], op2, base);
                    
                    if (prevResult != -1 && prevResult != curResult) { //이전 진법에서의 결과랑 현재 진법에서의 결과가 다르면 ?로 하고 다음 표현식으로 넘어감
                        answer[answerIdx++] = new StringBuilder()
                            .append(op1).append(" ")
                            .append(expression[1]).append(" ")
                            .append(op2).append(" ")
                            .append(expression[3]).append(" ?").toString();
                        
                        isBreaked = true;
                        break;
                    }
                    
                    prevResult = curResult;
                }
                
                if (!isBreaked) { //모두 같은 결과
                    answer[answerIdx++] = new StringBuilder()
                        .append(op1).append(" ")
                        .append(expression[1]).append(" ")
                        .append(op2).append(" ")
                        .append(expression[3]).append(" ")
                        .append(curResult).toString();
                }
            } else { //바로 계산
                int result = calcExpression(op1, expression[1], op2, exactBase);
                answer[answerIdx++] = new StringBuilder()
                    .append(op1).append(" ")
                    .append(expression[1]).append(" ")
                    .append(op2).append(" ")
                    .append(expression[3]).append(" ")
                    .append(result).toString();
            }
        }
        
        return Arrays.copyOfRange(answer, 0, answerIdx);
    }
    
    public boolean isCalcRight(int op1, String operator, int op2, int result) {
        if (operator.equals("+")) {
            return op1 + op2 == result;
        } else {
            return op1 - op2 == result;
        }
    }
    
    public int findMaxNumber(int n) {
        if (n < 10) {
            return n;
        } 
        
        return Math.max(n / 10, n % 10);
    }
    
    public int calcExpression(int op1, String operator, int op2, int base) {
        System.out.println("base: " + base);
        
        int result = 0;
        
        if (operator.equals("+")) {
            int temp = 0;
            
            //일의 자릿수끼리 더한 숫자가 진법보다 크거나 같으면
            if (op1 % 10 + op2 % 10 >= base) {
                result += op1 % 10 + op2 % 10 - base;
                temp = 1;
            } else {
                result += op1 % 10 + op2 % 10;
            }
            
            //십의 자릿수끼리 더한 숫자가 진법보다 크거나 같으면
            if (op1 / 10 + op2 / 10 + temp >= base) {
                result += (op1 / 10 + op2 / 10 + temp - base) * 10;
                temp = 1;
            } else {
                result += (op1 / 10 + op2 / 10 + temp) * 10;
                temp = 0;
            }
            
            if (temp == 1) {
                result += 100;
            }
        } else {
            int temp = 0;
            
            //op2의 일의 자리 숫자가 op1의 일의 자리 숫자보다 크면
            if (op1 % 10 < op2 % 10) {
                result += op1 % 10 - op2 % 10 + base;
                temp = 1;
            } else {
                result += op1 % 10 - op2 % 10;
            }
            
            if (temp == 1) {
                result += (op1 / 10 - temp - op2 / 10) * 10;
            } else {
                result += (op1 / 10 - op2 / 10) * 10;
            }
        }
         
        return result;
    }
}