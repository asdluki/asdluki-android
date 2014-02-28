
package com.gmail.asdluki;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity
public class MainActivity extends Activity
{
    private int[] textViews = {
            R.id.reminder,
            R.id.companyData,
            R.id.addressData,
            R.id.address1,
            R.id.address2,
            R.id.companyName,
            R.id.bankTransferHeader,
            R.id.bankName,
            R.id.bankAccountNumber,
            R.id.vatid,
            R.id.vatIdValue
    };
    private String endOfLine = System.getProperty("line.separator");
    private Animation rotation;
    @ViewById(R.id.imgMe)
    public ImageView imageWithMe;
    @Click
    public void sendByEmail() {
        Intent sendIntent = new Intent(android.content.Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject));
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                String.format("%s%s%s %s%s%s%s%s%s%s%s%s%s%s",
                        getString(R.string.companyNameValue), endOfLine,
                        getString(R.string.vatId), getString(R.string.vatIdValue), endOfLine,
                        getString(R.string.address1), endOfLine,
                        getString(R.string.address2), endOfLine,
                        endOfLine,
                        getString(R.string.bankName), endOfLine,
                        getString(R.string.bankAccountNumber), endOfLine));
        startActivity(sendIntent);
    }
    @Click
    public void imgMe() {
        imageWithMe.startAnimation(rotation);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/coolvetica_rg.ttf");

        for(int element : textViews) {
            TextView view = (TextView)findViewById(element);
            view.setTypeface(font);
        }

        rotation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
    }

}
