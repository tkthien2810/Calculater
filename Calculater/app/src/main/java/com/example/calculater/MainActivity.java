package com.example.calculater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private StringBuilder inputStringBuilder;
    private String input;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);
        inputStringBuilder = new StringBuilder();

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        setButtonOnClickListeners(gridLayout);
    }

    private void setButtonOnClickListeners(GridLayout gridLayout) {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View child = gridLayout.getChildAt(i);

            if (child instanceof Button) {
                Button button = (Button) child;
                button.setOnClickListener(v -> {
                    onButtonClick(button.getText().toString());
                });
            }
        }
    }

    private void onButtonClick(String buttonText) {
        if (buttonText.equals("=")) {
            calculateResult();
        } else if (buttonText.equals("C")) {
            clearInput();
        } else {
            inputStringBuilder.append(buttonText);
            input = inputStringBuilder.toString();
            updateResultText();
        }
    }

    private void calculateResult() {
        try {
            double result = Solve(input.toString());
            resultTextView.setText(String.valueOf(result));
        } catch (Exception e) {
            resultTextView.setText("Error");
        }
    }

    private void clearInput() {
        inputStringBuilder.setLength(0);
        updateResultText();
    }

    private void updateResultText() {
        resultTextView.setText(inputStringBuilder.toString());
    }
    public double Solve(String s){
        if(s.split("\\*").length==2){
            String n[] = s.split("\\*");
            try{
                double mul = Double.parseDouble(n[0])*Double.parseDouble(n[1]);
                result = new String(mul + "");
            }catch (Exception e){

            }
        }
        if(s.split("\\/").length==2){
            String number[] = s.split("\\/");
            try{
                double div = Double.parseDouble(number[0])/Double.parseDouble(number[1]);
                result = new String(div + "");
            }catch (Exception e){

            }
        }
        if(s.split("\\+").length==2){
            String number[] = input.split("\\+");
            try{
                double sum = Double.parseDouble(number[0])+Double.parseDouble(number[1]);
                result = new String(sum + "");
            }catch (Exception e){

            }
        }
        if(s.split("\\-").length==2){
            String number[] = input.split("\\-");
            try{
                double sub = Double.parseDouble(number[0])-Double.parseDouble(number[1]);
                result = sub+"";
            }catch (Exception e){

            }
        }

        String n[] = s.split("\\.");
        if (n.length > 1){
            if (n[1].equals("0")){
                result = n[0];
            }
        }
        return Double.parseDouble(String.valueOf(result));

    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }


    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private boolean hasPrecedence(String op1, String op2) {
        return (op2.equals("+") || op2.equals("-")) && (op1.equals("*") || op1.equals("/"));
    }

    private double applyOperation(String operator, double b, double a) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }


}
