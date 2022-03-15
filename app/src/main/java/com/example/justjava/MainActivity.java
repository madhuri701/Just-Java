package com.example.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.lang.ClassCastException;
import java.text.NumberFormat;
import java.util.jar.Attributes;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void increment(View view)
    {
      if(quantity==100)
      {
          Toast.makeText(this,"you cannot have more than 100 coffees",Toast.LENGTH_SHORT).show();
          return;
      }
        quantity = quantity+1;
        display(quantity);
    }



        public void decrement (View view) {
            if (quantity==1) {
                Toast.makeText(this,"you cannot have less than 1 coffee",Toast.LENGTH_SHORT).show();
                return;
            }
                quantity = quantity - 1;
                display(quantity);

            }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view)
    {
        EditText nameField = (EditText)findViewById(R.id.name_field) ;
        String name = nameField.getText().toString();
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        int price = calculatePrice(hasWhippedCream);
      String priceMessage="name:" + name + "\nTotal $" + price +  " \nAdd whipped cream?" + " " + hasWhippedCream ;
      priceMessage = priceMessage + "\nThank you!";
       Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "just java order for" + name);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
      displayMessage(priceMessage);



    }
    private int calculatePrice(boolean addWhippedCream)
    {
        int basePrice =5;
        if(addWhippedCream)
        {
            basePrice = basePrice +1;
        }

        return quantity*basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number, boolean addWhippedCream) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

}