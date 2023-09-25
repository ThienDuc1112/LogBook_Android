package com.example.logbook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    private EditText input;
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnAdd, btnSubtract,
    btnMultiply, btnDivide, btnEqual, btnReset;
    public String inputValue = "0";
    public boolean isNextOperation = false;
    public boolean isValid = true, isValidNumber = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(this,LogBook3.class);
        startActivity(i);
        initView();
        input.setText(inputValue);
        inputValue = "";
        input.setEnabled(false);
        input.setFocusable(false);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnSubtract.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
        btnReset.setOnClickListener(this);

    }

    public void initView() {
        input = findViewById(R.id.input_edit_text);
        btn0 = findViewById(R.id.button_0);
        btn1 = findViewById(R.id.button_1);
        btn2 = findViewById(R.id.button_2);
        btn3 = findViewById(R.id.button_3);
        btn4 = findViewById(R.id.button_4);
        btn5 = findViewById(R.id.button_5);
        btn6 = findViewById(R.id.button_6);
        btn7 = findViewById(R.id.button_7);
        btn8 = findViewById(R.id.button_8);
        btn9 = findViewById(R.id.button_9);
        btnAdd = findViewById(R.id.button_add);
        btnSubtract = findViewById(R.id.button_subtract);
        btnMultiply = findViewById(R.id.button_multiply);
        btnDivide = findViewById(R.id.button_divide);
        btnEqual = findViewById(R.id.button_equal);
        btnReset = findViewById(R.id.button_reset);

    }

    @Override
    public void onClick(View view) {
        if(isNextOperation){
            isNextOperation = false;
            inputValue = "";
        }
        if (view.getId() == R.id.button_0) {
            inputValue = inputValue.concat("0");
        } else if (view.getId() == R.id.button_1) {
            inputValue = inputValue.concat("1");
        } else if (view.getId() == R.id.button_2) {
            inputValue = inputValue.concat("2");
        } else if (view.getId() == R.id.button_3) {
            inputValue = inputValue.concat("3");
        } else if (view.getId() == R.id.button_4) {
            inputValue = inputValue.concat("4");
        } else if (view.getId() == R.id.button_5) {
            inputValue = inputValue.concat("5");
        } else if (view.getId() == R.id.button_6) {
            inputValue = inputValue.concat("6");
        } else if (view.getId() == R.id.button_7) {
            inputValue = inputValue.concat("7");
        } else if (view.getId() == R.id.button_8) {
            inputValue = inputValue.concat("8");
        } else if (view.getId() == R.id.button_9) {
            inputValue = inputValue.concat("9");
        } else if (view.getId() == R.id.button_add) {
            inputValue = inputValue.concat(" + ");
        } else if (view.getId() == R.id.button_subtract) {
            if(inputValue.length() == 0||inputValue.charAt(inputValue.length()-1) == ' '){
                inputValue = inputValue.concat("-");
            }else{
                inputValue = inputValue.concat(" - ");
            }
        } else if (view.getId() == R.id.button_multiply) {
            inputValue = inputValue.concat(" * ");
        } else if (view.getId() == R.id.button_divide) {
            inputValue = inputValue.concat(" / ");
        } else if (view.getId() == R.id.button_equal) {
            isNextOperation = true;
            calculateResult();
        } else if (view.getId() == R.id.button_reset) {
            inputValue = "0";
            isNextOperation = true;
        }
        input.setText(inputValue);
    }
    public void calculateResult(){
//        new AlertDialog.Builder(MainActivity.this)
//                .setTitle("Notification")
//                .setMessage("Error")
//                .show();
//        String regex = "\\b(-?\\d+)\\s*([+\\-*/])\\s*(-?\\d+)\\b";
//        Pattern pattern = Pattern.compile(regex);
//
//        Matcher matcher = pattern.matcher(inputValue);
//        if (!matcher.matches()) {
//            isValid = false;
//        }
        inputValue.trim();
        String[] parts = inputValue.split(" ");
        isValid = isValidOperation(parts);
        if(isValid ) {
            char operation = parts[1].charAt(0);
            double num1 = Double.parseDouble(parts[0]);
            double num2 = Double.parseDouble(parts[2]);

            if( num1 > Double.MAX_VALUE || num2 > Double.MAX_VALUE){
                isValidNumber = false;
            }
            double result = 0;
            switch(operation){
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = Math.round((num1 / num2)*1000)/1000.0;
                    break;
                default:
                    input.setText("Invalid");
                    isValid = false;
                    break;
            }
            isValidNumber = isValidateNumber(result);
            if(isValidNumber) {
                Log.e("errorrrrrrr","error");
                if(String.valueOf(result).contains("E")){
                    String[] arrString = String.valueOf(result).split("E");
                    inputValue = arrString[0] + "*10^" + arrString[1];
                }else{
                    String[] strResult = String.valueOf(result).split("\\.");
                    if (Integer.parseInt(strResult[1]) == 0) {
                        inputValue = strResult[0];
                    } else {
                        inputValue = String.valueOf(result);
                    }
                }
            }else{
                inputValue = "Invalid input";
            }
            input.setText(inputValue);
        }else{
            inputValue = "Invalid operation";
            input.setText(inputValue);
        }
    }

    private boolean isValidOperation(String[] parts){
        if(parts.length !=3){
            return false;
        }
        if(!isNumber(parts[0])||!isNumber(parts[2])){
            return false;
        }
        for(int i = 0; i < parts.length; i++){
            if(parts[i] == null || parts[i].isEmpty()){
                return false;
            }
        }
        return true;
    }

    public static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidateNumber(double number){
        if(number > Double.MAX_VALUE){
            return false;
        }
        return true;
    }
}