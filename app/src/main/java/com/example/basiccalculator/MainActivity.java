package com.example.basiccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String inputQuery;
    TextView inputView;
    TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputQuery = "";
        inputView = (TextView) findViewById(R.id.textViewInput);
        resultView = (TextView) findViewById(R.id.textViewOutput);

        addEventListenersToInputButtons();
    }

    @Override
    public void onClick(View view) {
        char keyPressed = ((Button)view).getText().toString().charAt(0);

        inputQuery += keyPressed;
        inputView.setText(inputQuery);
    }

    public void printResult(View view) {
        String result;
        try {
            result = eval(inputQuery).toString();
        }
        catch (Exception e) {
            result = "Invalid Input";
        }
        
        resultView.setText(result);
    }
    
    public void clearInput(View view) {
        inputQuery = "";
        inputView.setText(inputQuery);
    }

    private static Float eval(String query) {
        Stack<Float> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int queryLength = query.length();
        for (int i = 0; i < queryLength; i++) {
            char symbol = query.charAt(i);

            if (symbol >= '0' && symbol <= '9') {
                String number = "";
                while (i < queryLength && !isOperator(symbol)) {
                    number += query.charAt(i);
                    i++;
                    if (i < queryLength)
                        symbol = query.charAt(i);
                }
                operands.push(Float.parseFloat(number));
                i--;    // adjust i for next loop update so that operator is read next
            }
            else if (isOperator(symbol)) {
                if (operators.empty()) {
                    operators.push(symbol);
                }
                else if ((symbol == '*' || symbol == '/'))
                {
                    char last_operator = operators.peek();

                    if (last_operator == '*') {
                        float num2 = operands.pop();
                        float num1 = operands.pop();
                        operands.push(num1 * num2);
                        operators.pop();
                    }
                    else if (last_operator == '/') {
                        float num2 = operands.pop();
                        float num1 = operands.pop();
                        operands.push(num1 / num2);
                        operators.pop();
                    }

                    operators.push(symbol);
                }
                else if (symbol == '+' || symbol == '-') {
                    char last_operator = operators.peek();

                    if (last_operator == '+') {
                        float num2 = operands.pop();
                        float num1 = operands.pop();
                        operands.push(num1 + num2);
                        operators.pop();
                    }
                    else if (last_operator == '-') {
                        float num2 = operands.pop();
                        float num1 = operands.pop();
                        operands.push(num1 - num2);
                        operators.pop();
                    }
                    else if (last_operator == '/') {
                        float num2 = operands.pop();
                        float num1 = operands.pop();
                        operands.push(num1 / num2);
                        operators.pop();
                    }
                    else if (last_operator == '*') {
                        float num2 = operands.pop();
                        float num1 = operands.pop();
                        operands.push(num1 * num2);
                        operators.pop();
                    }

                    operators.push(symbol);
                }
            }
            else {
                throw new ArithmeticException("Invalid Input");
            }
        }

        while (!operators.empty()) {
            float num2 = operands.pop();
            float num1 = operands.pop();
            char operator = operators.pop();

            switch (operator) {
                case '+':
                    operands.push(num1 + num2);
                    break;
                case '-':
                    operands.push(num1 - num2);
                    break;
                case '/':
                    operands.push(num1 / num2);
                    break;
                case '*':
                    operands.push(num1 * num2);
                    break;
            }
        }

        return operands.empty() ? 0.0f : operands.pop();
    }

    private static boolean isOperator(char ch) {
        return (ch == '+') || (ch == '-') || (ch == '/') || (ch == '*');
    }

    private void addEventListenersToInputButtons() {
        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.buttonDecimalPoint).setOnClickListener(this);
        findViewById(R.id.buttonAdd).setOnClickListener(this);
        findViewById(R.id.buttonSubtract).setOnClickListener(this);
        findViewById(R.id.buttonDivide).setOnClickListener(this);
        findViewById(R.id.buttonMultiply).setOnClickListener(this);
    }
}