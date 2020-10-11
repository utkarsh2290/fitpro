package com.example.fitpro.ui.test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.fitpro.R;

public class TestFragment extends Fragment {

    private RelativeLayout articleYoga,articleDiet,articleExercise,articleVegan;
    private RelativeLayout testCard1,testCard2,testCard3,rPositiveResult1,rPositiveResult2,rPositiveResult3,rNegativeResult1,rNegativeResult2,rNegativeResult3;
    private TextView tvCortisol,tvCortisol2,tvCortisol3,CHECK;

    public static int mExpandedPosition=-1;
    public static int previousExpandedPosition=-1;


    public TestFragment(){

    }
    private TestViewModel testViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        testViewModel =
                ViewModelProviders.of(this).get(TestViewModel.class);
        View root = inflater.inflate(R.layout.fragment_test, container, false);

        articleDiet=root.findViewById(R.id.articleCard2);
        articleYoga=root.findViewById(R.id.articleCard1);
        articleExercise=root.findViewById(R.id.articleCard3);
        articleVegan=root.findViewById(R.id.articleCard4);
        testCard1=root.findViewById(R.id.rLayoutTest1);
        testCard2=root.findViewById(R.id.rLayoutTest2);
        testCard3=root.findViewById(R.id.rLayoutTest3);
        tvCortisol=root.findViewById(R.id.tvCortisol);
        tvCortisol2=root.findViewById(R.id.tvCortisol2);
        tvCortisol3=root.findViewById(R.id.tvCortisol23);
        rPositiveResult1=root.findViewById(R.id.rLayoutPositiveResult);
        rPositiveResult2=root.findViewById(R.id.rLayoutPositiveResult2);
        rPositiveResult3=root.findViewById(R.id.rLayoutPositiveResult23);
        rNegativeResult1=root.findViewById(R.id.rLayoutNegativeResult);
        rNegativeResult2=root.findViewById(R.id.rLayoutNegativeResult2);
        rNegativeResult3=root.findViewById(R.id.rLayoutNegativeResult23);




        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        articleYoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData((Uri.parse("https://www.timesnownews.com/health/article/7-proven-benefits-of-yoga-and-meditation-for-your-mind-and-body/620691")));
                startActivity(intent);
            }
        });

        articleDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData((Uri.parse("https://www.betterhealth.vic.gov.au/health/ConditionsAndTreatments/heart-disease-and-food")));
                startActivity(intent);
            }
        });

        articleExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData((Uri.parse("https://www.timesnownews.com/health/article/7-proven-benefits-of-yoga-and-meditation-for-your-mind-and-body/620691https://www.ahajournals.org/doi/full/10.1161/01.CIR.0000048890.59383.8D")));
                startActivity(intent);
            }
        });

        articleVegan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData((Uri.parse("https://www.medicalnewstoday.com/articles/321992")));
                startActivity(intent);
            }
        });
        final boolean expanded= false;
        testCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int x=Integer.parseInt(tvCortisol.getText().toString().substring(11,13));

               if(x>=60){
                   rNegativeResult1.setVisibility(View.VISIBLE);

               }
               else{
                   rPositiveResult1.setVisibility(View.VISIBLE);
               }

            }
        });

        testCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x=Integer.parseInt(tvCortisol2.getText().toString().substring(11,13));

                if(x>=60){
                    rNegativeResult2.setVisibility(View.VISIBLE);
                }
                else{
                    rPositiveResult2.setVisibility(View.VISIBLE);
                }
            }
        });

        testCard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x=Integer.parseInt(tvCortisol3.getText().toString().substring(11,13));

                if(x>=60){
                    rNegativeResult3.setVisibility(View.VISIBLE);
                }
                else{
                    rPositiveResult3.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}