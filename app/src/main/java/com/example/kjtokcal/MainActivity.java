package com.example.kjtokcal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String DEFAULT_TOTAL = "100";
    public static final String NO_VALUE = "You did not enter a value";
    public static final String NO_VALUES = "You did not capture all values";
    Double conversion = 4.1868;
    String kj;
    String total;
    String serving;

    @BindView(R.id.kjEditText) EditText kjEditText;
    @BindView(R.id.totalEditText) EditText totalEditText;
    @BindView(R.id.servingEditText) EditText servingEditText;
    @BindView(R.id.resultTextView) TextView resultTextView;
    @BindView(R.id.kjToKcalButton) Button kjToKcalButton;
    @BindView(R.id.kjPerServingButton) Button kjPerServingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        totalEditText.setText(DEFAULT_TOTAL);
    }

    public void kjToKCal(View view) {
        kj = kjEditText.getText().toString();
        if (kj.equals(""))
            Toast.makeText(this, NO_VALUE, Toast.LENGTH_SHORT).show();
        else
            resultTextView.setText(String.valueOf(Double.parseDouble(kj) / conversion));
        dropKeyboard();
    }

    public void kjPerServing(View view) {
        kj = kjEditText.getText().toString();
        total = totalEditText.getText().toString();
        serving = servingEditText.getText().toString();

        if (kj.equals("") || total.equals("") || serving.equals(""))
            Toast.makeText(this, NO_VALUES, Toast.LENGTH_SHORT).show();
        else
            resultTextView.setText(String.valueOf((Double.parseDouble(kj) * (Double.parseDouble(serving) / Double.parseDouble(total))) / conversion));
        dropKeyboard();
    }

    private void dropKeyboard() {
        try  {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}