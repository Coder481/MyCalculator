package com.logocial.mycalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.logocial.mycalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setUpButtons();
    }

    private void setUpButtons() {
        b.one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberToExpression("1");
            }
        });
        b.two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberToExpression("2");
            }
        });
        b.three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberToExpression("3");
            }
        });
        b.four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberToExpression("4");
            }
        });
        b.five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberToExpression("5");
            }
        });
        b.six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberToExpression("6");
            }
        });
        b.seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberToExpression("7");
            }
        });
        b.eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberToExpression("8");
            }
        });
        b.nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberToExpression("9");
            }
        });
        b.zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberToExpression("0");
            }
        });
        b.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String exp = b.expressionView.getText().toString();
                checkForResult(exp);
                if (exp.equals("")){
                    setNumberToExpression("+");
                    return;
                }
                if (exp.charAt(exp.length()-1) == '+' || exp.charAt(exp.length()-1) == '-' || exp.charAt(exp.length()-1) == '*' || exp.charAt(exp.length()-1) == '/'){
                    checkForSignRepetition("+",exp);
                }else {
                    setNumberToExpression("+");
                }
            }
        });
        b.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String exp = b.expressionView.getText().toString();
                checkForResult(exp);
                if (exp.equals("")){
                    setNumberToExpression("-");
                    return;
                }
                if (exp.charAt(exp.length()-1) == '+' || exp.charAt(exp.length()-1) == '-' || exp.charAt(exp.length()-1) == '*' || exp.charAt(exp.length()-1) == '/'){
                    checkForSignRepetition("-",exp);
                }
                else {
                    setNumberToExpression("-");
                }
            }
        });
        b.multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String exp = b.expressionView.getText().toString();
                checkForResult(exp);
                if (exp.equals("")){
                    setNumberToExpression("*");
                    return;
                }
                if (exp.charAt(exp.length()-1) == '+' || exp.charAt(exp.length()-1) == '-' || exp.charAt(exp.length()-1) == '*' || exp.charAt(exp.length()-1) == '/'){
                    checkForSignRepetition("*",exp);
                }
                else {
                    setNumberToExpression("*");
                }
            }
        });
        b.divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String exp = b.expressionView.getText().toString();
                checkForResult(exp);
                if (exp.equals("")){
                    setNumberToExpression("/");
                    return;
                }
                if (exp.charAt(exp.length()-1) == '+' || exp.charAt(exp.length()-1) == '-' || exp.charAt(exp.length()-1) == '*' || exp.charAt(exp.length()-1) == '/'){
                    checkForSignRepetition("/",exp);
                }else {
                    setNumberToExpression("/");
                }
            }
        });
        b.point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberToExpression(".");
            }
        });
        b.reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.expressionView.setText("");
            }
        });
        b.equalTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String exp = b.expressionView.getText().toString();
                if (!isTwoDigits(exp)){
                    Snackbar.make(findViewById(android.R.id.content),"Please enter expression",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                checkForResult(exp);
            }
        });
        b.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String exp = b.expressionView.getText().toString();
                backTheExp(exp);
            }
        });
    }

    private void checkForResult(String exp) {
        if (isTwoDigits(exp)){
            double res = getResult(exp);
            String format = String.format("%.2f",res);
            b.expressionView.setText(format+"");
        }
    }

    private double getResult(String exp) {
        if (exp.contains("+")){
            String[] arr = exp.split("\\+");
            double t1 = Double.parseDouble(arr[0]);
            double t2 = Double.parseDouble(arr[1]);
            return t1 + t2;
        }
        else if (exp.contains("-") && (!exp.startsWith("-"))){
            String[] arr = exp.split("-");
            double t1 = Double.parseDouble(arr[0]);
            double t2 = Double.parseDouble(arr[1]);
            return t1 - t2;

        }
        else if (noOfNegatives(exp) == 2){
            String subStr = exp.substring(1);
            String[] arr = subStr.split("-");
            double t1 = Double.parseDouble(arr[0]);
            double t2 = Double.parseDouble(arr[1]);
            return (t1 + t2)*(-1);
        }
        else if (exp.contains("*")){
            String[] arr = exp.split("\\*");
            double t1 = Double.parseDouble(arr[0]);
            double t2 = Double.parseDouble(arr[1]);
            return t1 * t2;

        }
        else if (exp.contains("/")){
            String[] arr = exp.split("/");
            double t1 = Double.parseDouble(arr[0]);
            double t2 = Double.parseDouble(arr[1]);
            return t1 / t2;

        }

        return 0;
    }

    private int noOfNegatives(String exp) {
        int count = 0;
        for (int i = 0 ; i<exp.length();i++){
            if (exp.charAt(i) == '-'){
                count += 1;
            }
        }
        return count;
    }

    private boolean isTwoDigits(String exp) {
        if (exp.contains("+")){
            String[] arr = exp.split("\\+");
            int length = getLength(arr);
            return length == 2;
        }
        else if (exp.contains("-") && (!exp.startsWith("-"))){
            String[] arr = exp.split("-");
            int length = getLength(arr);
            return length == 2;
        }
        else if (noOfNegatives(exp) == 2){
            String subStr = exp.substring(1);
            String[] arr = subStr.split("-");
            int length = getLength(arr);
            return length == 2;
        }
        else if (exp.contains("*")){
            String[] arr = exp.split("\\*");
            int length = getLength(arr);
            return length == 2;
        }
        else if (exp.contains("/")){
            String[] arr = exp.split("/");
            int length = getLength(arr);
            return length == 2;
        }
        return false;
    }

    private int getLength(String[] arr) {
        return arr.length;
    }

    private void setNumberToExpression(String s) {
        String number = b.expressionView.getText().toString();
        b.expressionView.setText(number+s+"");
        String exp = b.expressionView.getText().toString();
        if (exp.equals("+") ||  exp.equals("*") || exp.equals("/")){
            b.expressionView.setText("");
            Snackbar.make(findViewById(android.R.id.content),"Please enter correct expression",Snackbar.LENGTH_SHORT).show();
        }

    }

    private void checkForSignRepetition(String s, String exp) {
        StringBuilder stringBuilder = new StringBuilder(exp);
        stringBuilder.setCharAt(exp.length()-1,s.charAt(0));
        b.expressionView.setText(stringBuilder.toString());
    }

    private void backTheExp(String exp) {
        if (!exp.equals("")){
            if (exp.length() == 1){
                b.expressionView.setText("");
            }else {
                b.expressionView.setText(exp.substring(0,(exp.length()-1)));
            }
        }
    }
}