package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.security.cert.PolicyQualifierInfo;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    CheckBox whippedCream, chocolate;
    TextView priceTextView, quantityTextView , myText;
    Button myButton;
    EditText myInput;
    String name;
    int quantity = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        whippedCream = (CheckBox) findViewById(R.id.cream_coffee);
        chocolate = (CheckBox) findViewById(R.id.cream_coffee2);
        priceTextView = (TextView) findViewById(R.id.price_text_view);
        quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        myButton = (Button)findViewById(R.id.order_button);
        myText = (TextView)findViewById(R.id.or);
        myInput = (EditText)findViewById(R.id.EnterName);




    }





    public void submitOrder(View view) {

        boolean has_chocolate = chocolate.isChecked();
        boolean has_whippedCream = whippedCream.isChecked();
        //setText();
        Editable nameEditable = myInput.getText();
        name = nameEditable.toString();



        String priceMessage = "TOTAL AMOUNT =" + (quantity * 10) + "Rs" + "\n Thank You!";
        //the result is stored in a boolean called has_wippedCream from the mathod isChecked()

        if (has_whippedCream == true) {

            displayMessage(name+"\n"+"ADD WHIPPED CREAM?\n" + has_whippedCream + "\n" + "QUANTITY:" + quantity + "\n" + "TOTAL=" + quantity * 20);
        }
        if (has_chocolate == true) {
            displayMessage(name+"\n"+"ADD CHOCOLATE?\n" + has_chocolate + "\n" + "QUANTITY:" + quantity + "\n" + "TOTAL=" + quantity * 15);
            //displayMessage("TOTAL+"+quantity*15);
        }
        if (has_chocolate == true && has_whippedCream == true) {
            displayMessage(name+"\n"+"ADD CHOCOLATE + WHIPPED CREAM?\n" + has_chocolate + "\n" + "QUANTITY:" + quantity + "\n" + "TOTAL=" + quantity * 35);
        }
         Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,
                getString(R.string.order_summary_email_subject, name));
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }




    //display(quantity);
    //displayMessage (priceMessage) ; adding in the order summary


    public void increment(View view) {
        if (quantity < 100) {
            quantity += 1;

            display(quantity);
        } else
            displayMessage("invalid");

    }

    public void decrement(View view) {
        if (quantity > 0) {
            quantity -= 1;
            display(quantity);

        } else
            displayMessage("invalid");
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {

        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {

        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    public void displayMessage(String message) {

        priceTextView.setText(message);
    }






}
