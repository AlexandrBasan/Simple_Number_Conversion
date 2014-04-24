/*
 *  Copyright 2011 vostdev.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package com.cbsb.snc;


import com.cbsb.hexconverter.R;
import com.cbsb.hexconverter.R.string;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Snc extends Activity implements OnClickListener {
	private static final int DEC = 0;
	private static final int HEX = 1;
	private static final int BIN = 2;
	private static final int OCT = 3;
	private EditText insertTxt;
	private TextView resultTxt;
	private Spinner fromSpinner;
	private Spinner toSpinner;
	private Button convertBtn;
	private Button clearBtn;
	private OnItemSelectedListener fromItemListener;
	private OnItemSelectedListener toItemListener;
	private int firstSpinnerSelection = 0;
	private int secondSpinnerSelection = 1;
	// идентификаторы для пунктов меню
	private static final int IDM_About = 101;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        findViews();
        setAdapters();
        setListeners();
  
        Typeface tf = Typeface.createFromAsset(this.getAssets(), "fonts/Dungeon.TTF");
        TextView tv = (TextView) findViewById(R.id.from);
        TextView tm = (TextView) findViewById(R.id.to);
        TextView rs = (TextView) findViewById(R.id.result);
        TextView rt = (TextView) findViewById(R.id.result_txt);
        tv.setTypeface(tf);
        tm.setTypeface(tf); 
        rs.setTypeface(tf);
        rt.setTypeface(tf);

        //AdManager.setTestDevices( new String[] { "ID" } );
    }
 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, IDM_About, Menu.NONE, string.about).setIcon(R.drawable.ic_menu_info_details);
		return super.onCreateOptionsMenu(menu);
	}
	

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://proalab.com/?page_id=517"));
		startActivity(browserIntent);
		return super.onOptionsItemSelected(item);
	}

	private void setListeners() {
		// TODO Auto-generated method stub
		fromItemListener = new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				switch(arg2){
				case DEC:
					firstSpinnerSelection = DEC;					
					//Log.d("MYTAG","selected 0");
					break;
				case HEX:
					firstSpinnerSelection = HEX;
					//Log.d("MYTAG","selected 1");
					break;
				case BIN:
					firstSpinnerSelection = BIN;
					//Log.d("MYTAG","selected 2");
					break;
				case OCT:
					firstSpinnerSelection = OCT;
					//Log.d("MYTAG","selected 3");
					break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}};
			
			toItemListener = new OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					switch(arg2){
					case DEC:
						secondSpinnerSelection = DEC;
						//Log.d("MYTAG","selected 0");
						break;
					case HEX:
						secondSpinnerSelection = HEX;
						//Log.d("MYTAG","selected 1");
						break;
					case BIN:
						secondSpinnerSelection = BIN;
						//Log.d("MYTAG","selected 2");
						break;
					case OCT:
						secondSpinnerSelection = OCT;
						//Log.d("MYTAG","selected 3");
						break;
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}};
				
				fromSpinner.setOnItemSelectedListener(fromItemListener);
				toSpinner.setOnItemSelectedListener(toItemListener);
				convertBtn.setOnClickListener(this);
				clearBtn.setOnClickListener(this);
		
	}
	private void setAdapters() {
		// TODO Auto-generated method stub
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numerals, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		fromSpinner.setAdapter(adapter);
		toSpinner.setAdapter(adapter);
		
		fromSpinner.setSelection(0);
		toSpinner.setSelection(1);
	}
	private void findViews() {
		// TODO Auto-generated method stub
		insertTxt = (EditText) findViewById(R.id.insert_txt);
		resultTxt = (TextView) findViewById(R.id.result_txt);
		fromSpinner = (Spinner) findViewById(R.id.from_nc);
		toSpinner = (Spinner) findViewById(R.id.to_nc);
		convertBtn = (Button) findViewById(R.id.convert_btn);
		clearBtn = (Button) findViewById(R.id.clear_btn);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.convert_btn:
			convertNum();
			break;
		case R.id.clear_btn:
			resultTxt.setText("");
			insertTxt.setText("");
			break;
		
		}
		
	}
	private void convertNum() {
		// TODO Auto-generated method stub
		if(firstSpinnerSelection == DEC && secondSpinnerSelection == DEC){
			if(insertTxt.getText().length() != 0)
				nonConvMsg("DEC");
			//Log.d("","DEC2DEC");
		}
		if(firstSpinnerSelection == DEC && secondSpinnerSelection == HEX){
			dec2hex();
			//Log.d("","DEC2HEX");
		}
		if(firstSpinnerSelection == DEC && secondSpinnerSelection == BIN){
			dec2bin();
			//Log.d("","DEC2BIN");
		}
		if(firstSpinnerSelection == DEC && secondSpinnerSelection == OCT){
			dec2oct();
			//Log.d("","DEC2OCT");
		}
		if(firstSpinnerSelection == HEX && secondSpinnerSelection == DEC){
			hex2dec();
			//Log.d("","HEX2DEC");
		}
		if(firstSpinnerSelection == HEX && secondSpinnerSelection == HEX){
			if(insertTxt.getText().length() != 0)
				nonConvMsg("HEX");
			//Log.d("","HEX2HEX");
		}
		if(firstSpinnerSelection == HEX && secondSpinnerSelection == BIN){
			hex2bin();
			//Log.d("","HEX2BIN");
		}
		if(firstSpinnerSelection == HEX && secondSpinnerSelection == OCT){
			hex2oct();
			//Log.d("","HEX2OCT");
		}
		if(firstSpinnerSelection == BIN && secondSpinnerSelection == DEC){
			bin2dec();
			//Log.d("","BIN2DEC");
		}
		if(firstSpinnerSelection == BIN && secondSpinnerSelection == HEX){
			bin2hex();
			//Log.d("","BIN2HEX");
		}
		if(firstSpinnerSelection == BIN && secondSpinnerSelection == BIN){
			if(insertTxt.getText().length() != 0)
				nonConvMsg("BIN");
			//Log.d("","BIN2BIN");
		}
		if(firstSpinnerSelection == BIN && secondSpinnerSelection == OCT){
			bin2oct();
			//Log.d("","BIN2OCT");
		}
		if(firstSpinnerSelection == OCT && secondSpinnerSelection == DEC){
			oct2dec();
			//Log.d("","OCT2DEC");
		}
		if(firstSpinnerSelection == OCT && secondSpinnerSelection == HEX){
			oct2hex();
			//Log.d("","OCT2HEX");
		}
		if(firstSpinnerSelection == OCT && secondSpinnerSelection == BIN){
			oct2bin();
			//Log.d("","OCT2BIN");
		}
		if(firstSpinnerSelection == OCT && secondSpinnerSelection == OCT){
			if(insertTxt.getText().length() != 0)
				nonConvMsg("OCT");
			//Log.d("","OCT2OCT");
		}
	}
	private void oct2bin() {
		// TODO Auto-generated method stub
		try{
			int i = Integer.parseInt(wellFormedTxt(),8);			
			String bin = Integer.toBinaryString(i);
			resultTxt.setText(bin);
		}catch(NumberFormatException e){
			errMsg();
		}
	}
	private void oct2hex() {
		// TODO Auto-generated method stub
		try{
			int i = Integer.parseInt(wellFormedTxt(),8);			
			String hex = Integer.toHexString(i);
			resultTxt.setText(hex);
		}catch(NumberFormatException e){
			errMsg();
		}
	}
	private void oct2dec() {
		// TODO Auto-generated method stub
		try{
			int i = Integer.parseInt(wellFormedTxt(),8);			
			resultTxt.setText(Integer.toString(i));
		}catch(NumberFormatException e){
			errMsg();
		}
	}
	private void bin2oct() {
		// TODO Auto-generated method stub
		try{
			int i = Integer.parseInt(wellFormedTxt(),2);
			String oct = Integer.toOctalString(i);
			resultTxt.setText(oct);
		}catch(NumberFormatException e){
			errMsg();
		}
	}
	private void hex2oct() {
		// TODO Auto-generated method stub
		try{
			int i = Integer.parseInt(wellFormedTxt(),16);			
			String oct = Integer.toOctalString(i);
			resultTxt.setText(oct);
		}catch(NumberFormatException e){
			errMsg();
		}
	}
	private void dec2oct() {
		// TODO Auto-generated method stub
		try{
			int i = Integer.parseInt(wellFormedTxt());
			String oct = Integer.toOctalString(i);
			resultTxt.setText(oct);
		}catch(NumberFormatException e){
			errMsg();
		}
	}
	private void bin2hex() {
		// TODO Auto-generated method stub
		try{
			int i = Integer.parseInt(wellFormedTxt(),2);
			String hex = Integer.toHexString(i);
			resultTxt.setText(hex);
		}catch(NumberFormatException e){
			errMsg();
		}
	}
	private void bin2dec() {
		// TODO Auto-generated method stub
		try{
			int i = Integer.parseInt(wellFormedTxt(),2);			
			resultTxt.setText(Integer.toString(i));
		}catch(NumberFormatException e){
			errMsg();
		}
	}
	private void hex2bin() {
		// TODO Auto-generated method stub
		try{
			
			int i = Integer.parseInt(wellFormedTxt(),16);
			String hex = Integer.toString(i);			
			i = Integer.parseInt(hex);
			hex = Integer.toBinaryString(i);
			resultTxt.setText(hex);
			
		}catch(NumberFormatException e){
			errMsg();
		}
	}
	private void hex2dec() {
		// TODO Auto-generated method stub
		try{
			int i = Integer.parseInt(wellFormedTxt(),16);			
			resultTxt.setText(Integer.toString(i));
		}catch(NumberFormatException e){
			errMsg();
		}
		
	}
	private void dec2bin() {
		// TODO Auto-generated method stub
		try{
			int i = Integer.parseInt(wellFormedTxt());
			String bin = Integer.toBinaryString(i);
			resultTxt.setText(bin);
		}catch(NumberFormatException e){
			errMsg();
		}
		
	}
	private void dec2hex() {
		// TODO Auto-generated method stub
		try{
			int i = Integer.parseInt(wellFormedTxt());
			String hex = Integer.toHexString(i);
			resultTxt.setText(hex);
		}catch(NumberFormatException e){
			errMsg();		
			}
	}	
	private String wellFormedTxt(){
		return insertTxt.getText().toString().replaceAll("\\s+", "");
	}
	private void errMsg(){
		Toast.makeText(this, "Not a valid number", Toast.LENGTH_LONG).show();
	}
	private void nonConvMsg(String msg){
		Toast.makeText(this, "Already "+msg, Toast.LENGTH_LONG).show();
	}
	
}