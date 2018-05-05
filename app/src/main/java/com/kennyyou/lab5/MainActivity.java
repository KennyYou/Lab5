package com.kennyyou.lab5;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

    public void button(View view) throws java.io.IOException {

        //Declaration
        TextView tv;
        tv = (TextView) findViewById(R.id.edit_textmain);

        EditText et;
        et = (EditText) findViewById(R.id.edit_infile);
        String filename = et.getText().toString();

        try{
        //Open Asset Manager
        AssetManager assetManager = getAssets();
        Scanner scan = new Scanner(assetManager.open(filename));
        Scanner sc = new Scanner(assetManager.open(filename));

        //Read file once to get array then re-read for the elements


            tv.setText("");

            //Read files
            int count = 0;
            while(scan.hasNext()){
                scan.nextInt();
                count++;
            }
            int array[] = new int[count];

            int countv2 = 0;
            while(sc.hasNext()){
                array[countv2] = sc.nextInt();
                countv2++;
            }

            //The sorting
            Arrays.sort(array,0, array.length);

            //Find total
            tv.append("# of values: " + count + "\n");

            //Find spread
            int spread = array[array.length-1] - array[0];
            tv.append("spread: " + spread + "\n");

            //Find RMS
            double rms1;
            double totalb4 = 0;
            double total;
            for(int num = 0; num < array.length; num++)
            {
                int rms = array[num];
                rms1 = rms * rms;
                totalb4 = rms1 + totalb4;
            }
            total = totalb4 / count;
            double total3 = Math.sqrt(total);

            DecimalFormat df2 = new DecimalFormat(".##");
            tv.append("RMS: " + df2.format(total3) + "\n");

            double median;
            //Find median
            if (array.length % 2 == 0){
                median = ((double)array[array.length/2] + (double)array[array.length/2 - 1])/2;
            }
            else{
                median = (double) array[array.length/2];
            }

            tv.append("The median is: " + median);

            //Printwriter


            EditText et1 = (EditText) findViewById(R.id.edit_infile2);
            String outfile = et1.getText().toString();

            File writefile = new File(getExternalFilesDir(null), outfile); //outfile is user specified
            FileWriter fw = new FileWriter(writefile);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            for (int i = 0; i <= countv2 - 1; i++){
                pw.append(String.valueOf(array[i]) + "\n");
            }

            sc.close();
            scan.close();
            pw.close();

        }
        catch (Exception e){
            tv.setText("");
            tv.append("Something went wrong try again");
        }
    }
}
