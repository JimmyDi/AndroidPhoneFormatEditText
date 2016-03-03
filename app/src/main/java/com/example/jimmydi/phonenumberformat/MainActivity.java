package com.example.jimmydi.phonenumberformat;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.terrakok.phonematter.PhoneFormat;


public class MainActivity extends AppCompatActivity {

    private EditText et;

    String StringInput="";
    String BeforeCursor2="";

    int CountTotal=0;
    int NumberUse=0;

    int CountNumber=0;
    int BeforeCursorCountNumber=0;
    int PresentPosition=0;
    int CountNumberInString=0;

    char[] BeforeCursorArray=new char[20];
    char[] InputStringArray=new char[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et=(EditText)findViewById(R.id.editText);


        final PhoneFormat NumberFormat=new PhoneFormat(getApplication());

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                CountNumber = 0;
                BeforeCursorCountNumber=0;
                CountTotal=0;
                CountNumberInString=0;

                PresentPosition= et.getSelectionStart();
                StringInput = et.getText().toString();

                StringInput = NumberFormat.format(s.toString());

                if (count==1) {
                    BeforeCursor2 = s.toString().substring(0, PresentPosition - 1);
                }

                else if (count==0){
                    BeforeCursor2 = s.toString().substring(0, PresentPosition);
                }


                InputStringArray= StringInput.toCharArray();
                BeforeCursorArray=BeforeCursor2.toCharArray();


                //calculate the number of numbers
                for (int j = 0; j < start; j++) {
                    if (((int) BeforeCursorArray[j] >= 48) && ((int) BeforeCursorArray[j] <= 57)) {
                        BeforeCursorCountNumber = BeforeCursorCountNumber + 1;
                    }
                }

                //if add number,total number=number+1
                if(count==1){
                    NumberUse=BeforeCursorCountNumber+1;
                }
                //if delete number, total number=number
                else if (count==0){
                    NumberUse=BeforeCursorCountNumber;

                }

                if (StringInput.equals("+")){
                    CountTotal=1;
                }
                else {

                    while (CountNumberInString != NumberUse) {
                        if ((int) InputStringArray[CountTotal] >= 48 && (int) InputStringArray[CountTotal] <= 57) {
                            //count the number of numbers, if not ,do not count into the parameter
                            CountNumberInString = CountNumberInString + 1;
                        } else {
                        }
                        //calculate the offset
                        CountTotal = CountTotal + 1;
                    }

                }
                        //close the TextListener before changing the text, after that open the TextListener again.
                        et.removeTextChangedListener(this);
                        et.setText(StringInput);
                        et.addTextChangedListener(this);

                       //set the position of cursor
                       et.setSelection(CountTotal);

            }



            @Override
            public void afterTextChanged(Editable s) {

            }

        });

    }



}
