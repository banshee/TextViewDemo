package com.restphone.TextViewDemo;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TextViewDemoActivity extends Activity {
  OnCheckedChangeListener resetOptionsListener = new OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
      buildTextViewOptions();
    }

  };

  android.widget.RadioGroup.OnCheckedChangeListener resetOptionsRadioListener = new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
      buildTextViewOptions();
    }
  };

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    buildTextViewOptions();
  }

  private void buildTextViewOptions() {
    TextView tv;

    if (((RadioButton) findViewById(R.id.isEditText)).isChecked()) {
      tv = new EditText(this);
    } else if (((RadioButton) findViewById(R.id.isTextView)).isChecked()) {
      tv = new TextView(this);
    } else {
      throw new RuntimeException("Must choose a type of text control");
    }
    RadioGroup viewTypeRadioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
    viewTypeRadioGroup.setOnCheckedChangeListener(resetOptionsRadioListener);

    CheckBox verticalScrollingCheckbox = (CheckBox) findViewById(R.id.verticalScrolling);
    tv.setVerticalScrollBarEnabled(verticalScrollingCheckbox.isChecked());
    verticalScrollingCheckbox.setOnCheckedChangeListener(resetOptionsListener);

    CheckBox horizontalScrollingCheckbox = (CheckBox) findViewById(R.id.horizontalScrolling);
    tv.setHorizontalScrollBarEnabled(horizontalScrollingCheckbox.isChecked());
    horizontalScrollingCheckbox.setOnCheckedChangeListener(resetOptionsListener);

    CheckBox useScrollingMovementMethodCheckbox = (CheckBox) findViewById(R.id.useScrollingMovementMethod);
    if (useScrollingMovementMethodCheckbox.isChecked()) {
      MovementMethod movementMethod = ScrollingMovementMethod.getInstance();
      tv.setMovementMethod(movementMethod);
    }
    useScrollingMovementMethodCheckbox.setOnCheckedChangeListener(resetOptionsListener);

    CheckBox clickableCheckbox = (CheckBox) findViewById(R.id.clickable);
    tv.setClickable(clickableCheckbox.isChecked());
    clickableCheckbox.setOnCheckedChangeListener(resetOptionsListener);

    CheckBox longclickableCheckbox = (CheckBox) findViewById(R.id.longClickable);
    tv.setLongClickable(longclickableCheckbox.isChecked());
    longclickableCheckbox.setOnCheckedChangeListener(resetOptionsListener);

    tv.setText(sampleString());

    CheckBox singleLineCheckbox = (CheckBox) findViewById(R.id.singleLine);
    tv.setSingleLine(singleLineCheckbox.isChecked());
    singleLineCheckbox.setOnCheckedChangeListener(resetOptionsListener);

    EditText edtv = (EditText) tv;

    FrameLayout holder = (FrameLayout) findViewById(R.id.textViewHolder);
    holder.removeAllViews();
    holder.addView(tv);
  }

  private String sampleString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 1000; i++) {
      sb.append(Integer.toString(i) + " ");
    }
    return sb.toString();
  }
}