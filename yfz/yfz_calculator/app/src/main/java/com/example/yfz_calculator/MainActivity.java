package com.example.yfz_calculator;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.service.autofill.OnClickAction;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Button> buttonList;
    private Button yfz_one,yfz_two,yfz_three,yfz_four,yfz_five,yfz_six,yfz_seven,yfz_eight,yfz_nine,yfz_zero,yfz_divide,yfz_add,yfz_times,yfz_equals,yfz_dot,yfz_minus,yfz_left_brackets,yfz_right_brackets,yfz_reset;
    private TextView yfz_result;
    private EditText yfz_input;
    private String content="";
    private String  temp="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setonclick();//将id绑定点击事件
        yfz_input=findViewById(R.id.yfz_input);
        yfz_result=findViewById(R.id.yfz_result);
        content="";
    }

    public void getResult(View view) {  //当用户点击"等于"那么就展示结果
        calculate();
        yfz_result.setText(content);
        yfz_input.setText("");
    }
    private void calculate(){  //开始计算input里的内容
        char [] x = content.toCharArray();
        Stack<Character> b = new Stack<>();
        String back = "";//后缀表达式
        for (int i = 0; i <x.length; i++) {
            //System.out.println(back);
            if(x[i] == '(') b.push(x[i]);
            if(x[i] == '+' || x[i] == '-') {
                while(!b.isEmpty() && (b.peek() == '*' || b.peek() == '/')) {
                    back = back + b.peek();
                    b.pop();
                }
                b.push(x[i]);
            }
            if(x[i] == '*' || x[i] == '/') {
                while(!b.isEmpty() && (b.peek() == '*' || b.peek() == '/')) {
                    back = back + b.peek();
                    b.pop();
                }
                b.push(x[i]);
            }
            if(x[i] == ')') {
                while (!b.isEmpty()){
                    if(b.peek() == '(') {b.pop(); break;}
                    //System.out.println(back);
                    back = back + b.peek();
                    b.pop();
                }
            }
            if(x[i] >= '0' && x[i] <= '9') back = back + x[i];
        }
        while(!b.isEmpty()){
            back += b.peek();
            b.pop();
        }
        System.out.println(back);
        Stack<Double> bb = new Stack<>();
        double p,q;
        char [] xx = back.toCharArray();
        for (int i = 0; i < xx.length; i++) {
            if(xx[i] >= '0' && xx[i] <= '9') {
                double s = (double) xx[i] - 48;
                //System.out.println(s);
                bb.push(s);
                //System.out.println(xx[i] - 48);
            }
            if(xx[i] == '+'){
                p = bb.peek();
                bb.pop();
                q = bb.peek();
                bb.pop();
                double result = p+q;
                bb.push(result);
                //System.out.println(result);
            }
            if(xx[i] == '-'){
                p = bb.peek();
                bb.pop();
                q = bb.peek();
                bb.pop();
                double result = q-p;
                bb.push(result);
                //System.out.println(result);
            }
            if(xx[i] == '*'){
                p = bb.peek();
                bb.pop();
                q = bb.peek();
                bb.pop();
                double result = p*q;
                bb.push(result);
                //System.out.println(result);
            }
            if(xx[i] == '/'){
                p = bb.peek();
                bb.pop();
                q = bb.peek();
                bb.pop();
                double result = q/p;
                bb.push(result);
                //System.out.println(result);
            }
        }
        int aaa = bb.peek().intValue();
        System.out.println(aaa);
        content=String.valueOf(aaa);

    }


    private void setonclick(){
        findViewById(R.id.yfz_add).setOnClickListener(this);
        findViewById(R.id.yfz_zero).setOnClickListener(this);
        findViewById(R.id.yfz_one).setOnClickListener(this);
        findViewById(R.id.yfz_two).setOnClickListener(this);
        findViewById(R.id.yfz_three).setOnClickListener(this);
        findViewById(R.id.yfz_three).setOnClickListener(this);
        findViewById(R.id.yfz_four).setOnClickListener(this);
        findViewById(R.id.yfz_five).setOnClickListener(this);
        findViewById(R.id.yfz_six).setOnClickListener(this);
        findViewById(R.id.yfz_seven).setOnClickListener(this);
        findViewById(R.id.yfz_eight).setOnClickListener(this);
        findViewById(R.id.yfz_nine).setOnClickListener(this);
        findViewById(R.id.yfz_dot).setOnClickListener(this);
        findViewById(R.id.yfz_divide).setOnClickListener(this);
        findViewById(R.id.yfz_times).setOnClickListener(this);
        findViewById(R.id.yfz_add).setOnClickListener(this);
        findViewById(R.id.yfz_minus).setOnClickListener(this);
        findViewById(R.id.yfz_left_brackets).setOnClickListener(this);
        findViewById(R.id.yfz_right_brackets).setOnClickListener(this);
        findViewById(R.id.yfz_reset).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {  //通过实现onclick接口，来实现每个按钮
        switch (v.getId()){  //通过id获取点击组件

            case R.id.yfz_zero:
                temp="0";
                break;
            case R.id.yfz_one:
                temp="1";
                break;
            case R.id.yfz_two:
                temp="2";
                break;
            case R.id.yfz_three:
                temp="3";
                break;
            case R.id.yfz_four:
                temp="4";
                break;
            case R.id.yfz_five:
                temp="5";
                break;
            case R.id.yfz_six:
                temp="6";
                break;
            case R.id.yfz_seven:
                temp="7";
                break;
            case R.id.yfz_eight:
                temp="8";
                break;
            case R.id.yfz_nine:
                temp="9";
                break;
            case R.id.yfz_dot:
                temp=".";
                break;
            case R.id.yfz_add:
                temp="+";
                break;
            case R.id.yfz_divide:
                temp="/";
                break;
            case R.id.yfz_times:
                temp="*";
                break;
            case R.id.yfz_minus:
                temp="-";
                break;
            case R.id.yfz_left_brackets:
                temp="(";
                break;
            case R.id.yfz_right_brackets:
                temp=")";
                break;
            case R.id.yfz_reset:
                content="";
                temp="";
                break;
            default:System.out.println("none");
        }
        content=content+temp;
        yfz_input.setText(content);
    }
}
