package com.print.demo.printview;

import com.print.demorego.R;
import utils.ApplicationContext;
import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class TextSecondActivity extends Activity {
	public RadioButton barcode;
	public RadioButton QRcode;
	public Button barPrint;
	public EditText bartext;
	public EditText barwide;
	public EditText barhight;
	public EditText barhri;
	public Spinner hripostion;
	public EditText barsize;
	public TextView twide;
	public TextView thight;
	public TextView thri;
	public Spinner barcodetype;
	public ImageView imageView;
	public RadioGroup group_barcode;
	private ArrayAdapter<String> adapterArea;
	private TypedArray images;
	public String defValues[];
	public ApplicationContext context;
	public int sate = 0;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actvity_barcode);
		context = (ApplicationContext) getApplicationContext();
		// 文字显示
		twide = (TextView) findViewById(R.id.textView_barwide);
		thight = (TextView) findViewById(R.id.textView_barhight);
		thri = (TextView) findViewById(R.id.textView_barhri);

		barcodetype = (Spinner) findViewById(R.id.spinner_barcodetype);
		barcode = (RadioButton) findViewById(R.id.barcode);
		QRcode = (RadioButton) findViewById(R.id.QRcode);
		barPrint = (Button) findViewById(R.id.button_barPrint);
		bartext = (EditText) findViewById(R.id.edit_bartext);
		barwide = (EditText) findViewById(R.id.edit_barwide);
		barhight = (EditText) findViewById(R.id.edit_barhight);
		hripostion = (Spinner) findViewById(R.id.spinner_hripostion);
		barhri = (EditText) findViewById(R.id.edit_barhri);
		imageView = (ImageView) findViewById(R.id.imageView1);
		group_barcode = (RadioGroup) findViewById(R.id.radioGroup_barcode);
		// 要显示的条码图片
		images = getResources().obtainTypedArray(R.array.barcodepic);
		defValues = getResources().getStringArray(R.array.barcodedefault);

		adapterArea = new ArrayAdapter<String>(TextSecondActivity.this,
				android.R.layout.simple_spinner_item, getResources()
						.getStringArray(R.array.barcodetype));// 在列表视图中所代表的对象。
		adapterArea
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		barcodetype.setAdapter(adapterArea);
		group_barcode
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						switch (group.getCheckedRadioButtonId()) {
						case R.id.barcode:
							images = getResources().obtainTypedArray(
									R.array.barcodepic);
							defValues = getResources().getStringArray(
									R.array.barcodedefault);
							// 在列表视图中所代表的对象。
							adapterArea = new ArrayAdapter<String>(
									TextSecondActivity.this,
									android.R.layout.simple_spinner_item,
									getResources().getStringArray(
											R.array.barcodetype));// 在列表视图中所代表的对象。
							adapterArea
									.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
							barcodetype.setAdapter(adapterArea);
							break;
						case R.id.QRcode:
							images = getResources().obtainTypedArray(
									R.array.QRpic);
							defValues = getResources().getStringArray(
									R.array.QRdefault);
							imageView.setImageDrawable(images.getDrawable(0));
							adapterArea = new ArrayAdapter<String>(
									TextSecondActivity.this,
									android.R.layout.simple_spinner_item,
									getResources().getStringArray(
											R.array.QRtype));// 在列表视图中所代表的对象。
							adapterArea
									.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
							barcodetype.setAdapter(adapterArea);
							// 根据不同的条码，显示文字不一样
							twide.setText(R.string.textView_barversion);// "版本"
							thight.setText(R.string.textView_barerr);// "纠错"
							thri.setText(R.string.textView_barsize);// "放大"
							break;
						default:
							break;
						}
					}
				});

		barcodetype.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				imageView.setImageDrawable(images.getDrawable(position));
				bartext.setText(defValues[position].split(",")[0]);
				barwide.setText(defValues[position].split(",")[1]);
				barhight.setText(defValues[position].split(",")[2]);
				if (QRcode.isChecked()) {
					hripostion.setVisibility(View.GONE);
					barhri.setVisibility(View.VISIBLE);
					barhri.setText(defValues[position].split(",")[3]);
					if (position == 1) {
						twide.setText(R.string.textView_barwide);// "宽度"
						thight.setText(R.string.textView_barhight);// "高度"

					} else if (position == 2) {
						twide.setText(R.string.textView_barlines);// "行字"
						thight.setText(R.string.textView_barerr);// "纠错"
					} else {
						twide.setText(R.string.textView_barversion);// "版本"
						thight.setText(R.string.textView_barerr);// "纠错"
					}
				} else {
					hripostion.setVisibility(View.VISIBLE);
					barhri.setVisibility(View.GONE);
					twide.setText(R.string.textView_barwide);// "宽度"
					thight.setText(R.string.textView_barhight);// "高度"
					thri.setText(R.string.textView_barhri);// "位置"
				}
			}

			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});

		barPrint.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				context.getObject().CON_PageStart(context.getState(), false, 0,
						0);
				context.getObject().ASCII_CtrlReset(context.getState());
				int hri = hripostion.getSelectedItemPosition();
				if (barcode.isChecked()) {
					context.getObject().ASCII_Print1DBarcode(
							context.getState(),
							barcodetype.getSelectedItemPosition() + 65,
							Integer.parseInt(barwide.getText().toString()),
							Integer.parseInt(barhight.getText().toString()),
							hri, bartext.getText().toString());
				} else {
					context.getObject().ASCII_Print2DBarcode(
							context.getState(),
							barcodetype.getSelectedItemPosition(),
							bartext.getText().toString(),
							Integer.parseInt(barwide.getText().toString()),
							Integer.parseInt(barhight.getText().toString()),
							Integer.parseInt(barhri.getText().toString()));
				}
				context.getObject().CON_PageEnd(context.getState(),
						context.getPrintway());
			}
		});
	}
}