package org.scorelab.crypto;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setContentView(R.layout.activity_main);
        final TextView msg = (TextView) findViewById(R.id.input);
        final TextView output = (TextView) findViewById(R.id.output);

        //Providers
        Provider[] providers = Security.getProviders();
        for(Provider p:providers) {
            String st =p.getName() + " " + p.getVersion() + "\n";
            output.append(st);
        }

        //Random numbers
        final Button button1 = (Button) findViewById(R.id.random);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String s1= msg.getText().toString();
                try{
                    Random n= new Random();
                    String base64 = Base64.encodeToString(n.getNumber(s1),Base64.DEFAULT);
                    output.setText(base64);
                }catch(Exception e) {
                    output.setText(e.toString());
                }
            }});

        //Next Random numbers
        final Button button2 = (Button) findViewById(R.id.next);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{
                    Random n= new Random();
                    String base64 = Base64.encodeToString(n.getNextNumber(),Base64.DEFAULT);
                    output.setText(base64);
                }catch(Exception e) {
                    output.setText(e.toString());
                }
            }});

        //Hash calculation
        final Button button3 = (Button) findViewById(R.id.hash);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String s1= msg.getText().toString();
                try{
                    SimpleHash h= new SimpleHash();
                    String base64 = Base64.encodeToString(h.makeHash(s1), Base64.DEFAULT);
                    output.setText(base64);
                }catch(Exception e) {
                    output.setText(e.toString());
                }
                try{
             //       randomNumber n= new randomNumber(s1);
               //     String base64 = Base64.encodeToString(n.getNumber(),Base64.DEFAULT)
                }catch(Exception e) {
                    output.setText(e.toString());
                }

            }});

        //Verifying hash
        final Button button4 = (Button) findViewById(R.id.verify);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String s1= msg.getText().toString();
                String s2= output.getText().toString();
                try{
                    SimpleHash h= new SimpleHash();
                    if (h.verifyHash(s1,Base64.decode(s2,Base64.DEFAULT)))
                        output.setText("Message Verified");
                    else output.setText("Verification Failed");
                }catch(Exception e) {
                    output.setText(e.toString());
                }
            }});

        //AES Encryption
        final Button button6 = (Button) findViewById(R.id.encrypt);
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String s1= msg.getText().toString();
                try{
                    AESEncrypt aes= new AESEncrypt(256);
                    String base64 = Base64.encodeToString(aes.encrypt(s1),Base64.DEFAULT);
                    output.setText(base64);
                }catch(Exception e) {
                        output.setText(e.toString());
                }

                }
            });

        //AES Decryption
        final Button button7 = (Button) findViewById(R.id.decrypt);
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String s2= output.getText().toString();
                try{
                    AESEncrypt aes= new AESEncrypt(256);
                    byte[] p=aes.decrypt(Base64.decode(s2,Base64.DEFAULT));
                    String s=new String(p);
                    output.setText(s);
                }catch(Exception e){
                        output.setText(e.toString());
                }

            }});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
