package com.madreain.morepopup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.madreain.morepopup.util.PopupUtil;

import java.util.ArrayList;

/**
 * @author madreain
 * @date 2020-03-10.
 * module：popop工具类
 * description：
 */
public class MainActivity extends AppCompatActivity {

    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    TextView tv7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.tv1);
        tv1.setOnClickListener(v -> {
            //更多按钮
            ArrayList<Object> testData = new ArrayList<>();
            testData.add("qq1");
            PopupUtil.getInstance().listPop(this, tv1, testData, (data, view, position, morePopup) -> {
                morePopup.dismiss();
            });

        });

        tv2 = findViewById(R.id.tv2);
        tv2.setOnClickListener(v -> {
            //更多按钮
            ArrayList<Object> testData = new ArrayList<>();
            testData.add(new TestData("qq1"));
            testData.add(new TestData("qq2"));
            testData.add(new TestData("qq3"));
            PopupUtil.getInstance().listPop(this, tv2, testData, (data, view, position, morePopup) -> {
                morePopup.dismiss();
            });

        });

        tv3 = findViewById(R.id.tv3);
        tv3.setOnClickListener(v -> {
            //更多按钮
            ArrayList<Object> testData = new ArrayList<>();
            testData.add(new TestData("qq1"));
            testData.add(new TestData("qq2"));
            testData.add(new TestData("qq3"));
            testData.add(new TestData("qq4"));
            testData.add(new TestData("qq5"));
            testData.add(new TestData("qq6"));
            testData.add(new TestData("qq7"));
            testData.add(new TestData("qq8"));
            testData.add(new TestData("qq9"));
            testData.add(new TestData("qq10"));
            testData.add(new TestData("qq11"));
            PopupUtil.getInstance().listPop(this, tv3, testData, (data, view, position, morePopup) -> {
                morePopup.dismiss();
            });

        });


        tv4 = findViewById(R.id.tv4);
        tv4.setOnClickListener(v -> {
            //更多按钮
            ArrayList<Object> testData = new ArrayList<>();
            testData.add(new TestData("qq1"));
            testData.add(new TestData("qq2"));
            testData.add(new TestData("qq3"));
            testData.add(new TestData("qq4"));
            testData.add(new TestData("qq5"));
            testData.add(new TestData("qq6"));
            testData.add(new TestData("qq7"));
            testData.add(new TestData("qq8"));
            testData.add(new TestData("qq9"));
            testData.add(new TestData("qq10"));
            testData.add(new TestData("qq11"));
            PopupUtil.getInstance().listPop(this, tv4, testData, (data, view, position, morePopup) -> {
                morePopup.dismiss();
            });

        });


        tv5 = findViewById(R.id.tv5);
        tv5.setOnClickListener(v -> {
            //更多按钮
            ArrayList<Object> testData = new ArrayList<>();
            testData.add(new TestData("qq1"));
            testData.add(new TestData("qq2"));
            testData.add(new TestData("qq3"));
            testData.add(new TestData("qq4"));
            testData.add(new TestData("qq5"));
            testData.add(new TestData("qq6"));
            testData.add(new TestData("qq7"));
            testData.add(new TestData("qq8"));
            testData.add(new TestData("qq9"));
            testData.add(new TestData("qq10"));
            testData.add(new TestData("qq11"));
            PopupUtil.getInstance().listPop(this, tv5, testData, (data, view, position, morePopup) -> {
                morePopup.dismiss();
            });

        });


        tv6 = findViewById(R.id.tv6);
        tv6.setOnClickListener(v -> {
            //更多按钮
            ArrayList<Object> testData = new ArrayList<>();
            testData.add(new TestData("qq1"));
            testData.add(new TestData("qq2"));
            testData.add(new TestData("qq3"));
            testData.add(new TestData("qq4"));
            testData.add(new TestData("qq5"));
            testData.add(new TestData("qq6"));
            testData.add(new TestData("qq7"));
            testData.add(new TestData("qq8"));
            testData.add(new TestData("qq9"));
            testData.add(new TestData("qq10"));
            testData.add(new TestData("qq11"));
            PopupUtil.getInstance().listPop(this, tv6, testData, (data, view, position, morePopup) -> {
                morePopup.dismiss();
            });

        });


        tv7 = findViewById(R.id.tv7);
        tv7.setOnClickListener(v -> {
            //更多按钮
            ArrayList<Object> testData = new ArrayList<>();
            testData.add(new TestData("qq1"));
            testData.add(new TestData("qq2"));
            testData.add(new TestData("qq3"));
            testData.add(new TestData("qq4"));
            testData.add(new TestData("qq5"));
            testData.add(new TestData("qq6"));
            testData.add(new TestData("qq7"));
            testData.add(new TestData("qq8"));
            testData.add(new TestData("qq9"));
            testData.add(new TestData("qq10"));
            testData.add(new TestData("qq11"));
            PopupUtil.getInstance().listPop(this, tv7, testData, (data, view, position, morePopup) -> {
                morePopup.dismiss();
            });

        });

    }

}
