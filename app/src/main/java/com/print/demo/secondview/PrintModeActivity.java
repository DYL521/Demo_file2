package com.print.demo.secondview;

import utils.ApplicationContext;
import com.print.demorego.R;
import com.print.demo.printview.GraphicTabsActivity;
import com.print.demo.printview.TextTabsActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PrintModeActivity extends Activity {
	public Button text;
	public Button pic;
	public Bundle b;
	public Button mLabel;
	private ApplicationContext mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_printmode);
		mContext = (ApplicationContext) getApplicationContext();
		text = (Button) findViewById(R.id.button_text);
		pic = (Button) findViewById(R.id.button_pic);
		mLabel = (Button) findViewById(R.id.button_label);

		text.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(PrintModeActivity.this,
						TextTabsActivity.class);
				startActivity(intent);
			}
		});
		pic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(PrintModeActivity.this,
						GraphicTabsActivity.class);
				// 图形是true
				startActivity(intent);
			}
		});
	}
}
