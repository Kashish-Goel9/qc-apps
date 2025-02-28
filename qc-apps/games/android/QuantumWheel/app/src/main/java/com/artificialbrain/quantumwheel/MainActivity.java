package com.artificialbrain.quantumwheel;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rubikstudio.library.LuckyWheelView;
import rubikstudio.library.model.LuckyItem;

public class MainActivity extends Activity {
    List<LuckyItem> data = new ArrayList<>();
    ArrayList<Choice> choiceList = new ArrayList<>();

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final LuckyWheelView luckyWheelView = (LuckyWheelView) findViewById(R.id.luckyWheel);
        choiceList = (ArrayList<Choice>) getIntent().getExtras().getSerializable("list");
        for(int i = 0; i < choiceList.size(); i++)
        {
            LuckyItem luckyItem = new LuckyItem();
            Choice choice = choiceList.get(i);
            luckyItem.topText = choice.getChoiceName();
            luckyItem.color = 0xffFFF3E0;
            data.add(luckyItem);
        }

       /* LuckyItem luckyItem1 = new LuckyItem();
        luckyItem1.topText = "100";
        luckyItem1.icon = R.drawable.test1;
        luckyItem1.color = 0xffFFF3E0;
        data.add(luckyItem1);

        LuckyItem luckyItem2 = new LuckyItem();
        luckyItem2.topText = "200";
        luckyItem2.icon = R.drawable.test2;
        luckyItem2.color = 0xffFFE0B2;
        data.add(luckyItem2);

        LuckyItem luckyItem3 = new LuckyItem();
        luckyItem3.topText = "300";
        luckyItem3.icon = R.drawable.test3;
        luckyItem3.color = 0xffFFCC80;
        data.add(luckyItem3);

        //////////////////
        LuckyItem luckyItem4 = new LuckyItem();
        luckyItem4.topText = "400";
        luckyItem4.icon = R.drawable.test4;
        luckyItem4.color = 0xffFFF3E0;
        data.add(luckyItem4);

        LuckyItem luckyItem5 = new LuckyItem();
        luckyItem5.topText = "500";
        luckyItem5.icon = R.drawable.test5;
        luckyItem5.color = 0xffFFE0B2;
        data.add(luckyItem5);

        LuckyItem luckyItem6 = new LuckyItem();
        luckyItem6.topText = "600";
        luckyItem6.icon = R.drawable.test6;
        luckyItem6.color = 0xffFFCC80;
        data.add(luckyItem6);
        //////////////////

        //////////////////////
        LuckyItem luckyItem7 = new LuckyItem();
        luckyItem7.topText = "700";
        luckyItem7.icon = R.drawable.test7;
        luckyItem7.color = 0xffFFF3E0;
        data.add(luckyItem7);

        LuckyItem luckyItem8 = new LuckyItem();
        luckyItem8.topText = "800";
        luckyItem8.icon = R.drawable.test8;
        luckyItem8.color = 0xffFFE0B2;
        data.add(luckyItem8); */


//        LuckyItem luckyItem9 = new LuckyItem();
//        luckyItem9.topText = "900";
//        luckyItem9.icon = R.drawable.test9;
//        luckyItem9.color = 0xffFFCC80;
//        data.add(luckyItem9);
//        ////////////////////////
//
//        LuckyItem luckyItem10 = new LuckyItem();
//        luckyItem10.topText = "1000";
//        luckyItem10.icon = R.drawable.test10;
//        luckyItem10.color = 0xffFFE0B2;
//        data.add(luckyItem10);
//
//        LuckyItem luckyItem11 = new LuckyItem();
//        luckyItem11.topText = "2000";
//        luckyItem11.icon = R.drawable.test10;
//        luckyItem11.color = 0xffFFE0B2;
//        data.add(luckyItem11);
//
//        LuckyItem luckyItem12 = new LuckyItem();
//        luckyItem12.topText = "3000";
//        luckyItem12.icon = R.drawable.test10;
//        luckyItem12.color = 0xffFFE0B2;
//        data.add(luckyItem12);

        /////////////////////

        luckyWheelView.setData(data);
        luckyWheelView.setRound(5);

        /*luckyWheelView.setLuckyWheelBackgrouldColor(0xff0000ff);
        luckyWheelView.setLuckyWheelTextColor(0xffcc0000);
        luckyWheelView.setLuckyWheelCenterImage(getResources().getDrawable(R.drawable.icon));
        luckyWheelView.setLuckyWheelCursorImage(R.drawable.ic_cursor);*/

        progressBar = findViewById(R.id.quantum_progress_bar);

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                RandomNumberInput randomNumberInput = new RandomNumberInput("","",
//                        "",4,"");
                RandomNumberInput randomNumberInput = new RandomNumberInput(3);
                progressBar.setVisibility(View.VISIBLE);
                MainApplication.apiManager.generateRandomNumber(randomNumberInput, new Callback<QuantumRandomNumber>() {
                    @Override
                    public void onResponse(Call<QuantumRandomNumber> call, Response<QuantumRandomNumber> response) {
                        progressBar.setVisibility(View.GONE);
                        QuantumRandomNumber quantumRandomNumber = response.body();
                        luckyWheelView.startLuckyWheelWithTargetIndex
                                (Integer.parseInt(quantumRandomNumber.getQuantum_random_num()));
                    }

                    @Override
                    public void onFailure(Call<QuantumRandomNumber> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this,
                                "Error is " + t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        luckyWheelView.setLuckyRoundItemSelectedListener(new LuckyWheelView.LuckyRoundItemSelectedListener() {
            @Override
            public void LuckyRoundItemSelected(int index) {
//                Toast.makeText(getApplicationContext(), data.get(index).topText, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int getRandomIndex() {
        Random rand = new Random();
        System.out.println(data.size());
        return rand.nextInt(data.size() - 1) + 0;
    }

    private int getRandomRound() {
        Random rand = new Random();
        return rand.nextInt(10) + 15;
    }
}
