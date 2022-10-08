package com.AOM.aomentityobject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private LinearLayout main;
    private int id;
    private List<EditText> editTexts = new ArrayList<EditText>();
    private ProductEntityType tShirtType;
    private ProductEntityType tvType;
    private ProductEntityType selectProductEntity;
    private Spinner spinnerView;
    private String[] listProduct={"TShirt","TV"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = findViewById(R.id.layout_main);
        spinnerView = findViewById(R.id.spinnerView);
        Button btn_submit = findViewById(R.id.btn_submit);

        spinnerView.setOnItemSelectedListener(this);


        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listProduct);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerView.setAdapter(aa);


        tShirtType = new ProductEntityType("TShirt");
        tShirtType.addPropertyType(new ProductPropertyType("size", Integer.class));
        tShirtType.addPropertyType(new ProductPropertyType("color", String.class));
        tShirtType.addPropertyType(new ProductPropertyType("material", String.class));

        tvType = new ProductEntityType("TV");
        tvType.addPropertyType(new ProductPropertyType("size", String.class));
        tvType.addPropertyType(new ProductPropertyType("model", String.class));
        tvType.addPropertyType(new ProductPropertyType("brand", String.class));
        tvType.addPropertyType(new ProductPropertyType("production_year", String.class));

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductEntity productEntity = selectProductEntity.newEntity();
                Set<String> propNames = selectProductEntity.getPropertyName();
                int n = 0;
                for(String propName : propNames) {
                    String s = editTexts.get(n).getText().toString();
                    if(selectProductEntity.getPropertyType(propName).getType() == Integer.class) {
                        productEntity.setProperty(propName, Integer.valueOf(s));
                    }else {
                        productEntity.setProperty(propName, editTexts.get(n).getText().toString());
                    }
                    n++;
                }
                AOMUtils.printEntity(productEntity);
            }
        });
    }


    private void addFilde(ProductEntityType productEntityType) {
        main.removeAllViews();
        editTexts.clear();
        id = 1;
        Set<String> propNames = productEntityType.getPropertyName();
        selectProductEntity = productEntityType;
        for(String propName : propNames) {

            LinearLayout editTextLayout = new LinearLayout(this);
            editTextLayout.setOrientation(LinearLayout.HORIZONTAL);
            main.addView(editTextLayout);

            TextView tv1 = new TextView(this);
            tv1.setId(id++);
            tv1.setText(propName + " :");
            editTextLayout.addView(tv1);

            EditText et1 = new EditText(this);
            et1.setId(id++);
            if(productEntityType.getPropertyType(propName).getType() == Integer.class)
                et1.setInputType(InputType.TYPE_CLASS_NUMBER);
            et1.setHint("Please enter a " + propName);
            et1.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f));
            editTextLayout.addView(et1);
            editTexts.add(et1);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
       switch (i){
           case 0:
               addFilde(tShirtType);
               break;
           case 1:
               addFilde(tvType);
               break;
       }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}