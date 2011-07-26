//Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

package com.restphone.TextViewDemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ArrowKeyMovementMethod;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TextViewDemoActivity extends Activity {
  TextViewDemoActivity self = this;

  OnCheckedChangeListener resetOptionsListener = new OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
      buildTextViewOptions();
      Toast.makeText(self, "created new view", Toast.LENGTH_SHORT).show();
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
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        buildTextViewOptions();
      }
    }, 1000);
  }

  private void buildTextViewOptions() {
    final TextView tv;

    RadioGroup viewTypeRadioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
    viewTypeRadioGroup.setOnCheckedChangeListener(resetOptionsRadioListener);
    switch (viewTypeRadioGroup.getCheckedRadioButtonId()) {
    case R.id.isEditText:
      tv = new EditText(this);
      break;
    case R.id.isTextView:
      tv = new TextView(this);
      break;
    default:
      throw new RuntimeException("Must choose a type of text control");
    }

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

    CheckBox linkMovementMethodCheckbox = (CheckBox) findViewById(R.id.linkMovementMethod);
    if (linkMovementMethodCheckbox.isChecked()) {
      MovementMethod movementMethod = ArrowKeyMovementMethod.getInstance();
      tv.setMovementMethod(movementMethod);
    }
    linkMovementMethodCheckbox.setOnCheckedChangeListener(resetOptionsListener);

    CheckBox clickableCheckbox = (CheckBox) findViewById(R.id.clickable);
    tv.setClickable(clickableCheckbox.isChecked());
    clickableCheckbox.setOnCheckedChangeListener(resetOptionsListener);

    CheckBox longclickableCheckbox = (CheckBox) findViewById(R.id.longClickable);
    tv.setLongClickable(longclickableCheckbox.isChecked());
    longclickableCheckbox.setOnCheckedChangeListener(resetOptionsListener);

    CheckBox singleLineCheckbox = (CheckBox) findViewById(R.id.singleLine);
    tv.setSingleLine(singleLineCheckbox.isChecked());
    singleLineCheckbox.setOnCheckedChangeListener(resetOptionsListener);

    CheckBox cursorVisibleCheckBox = (CheckBox) findViewById(R.id.cursorVisible);
    tv.setCursorVisible(cursorVisibleCheckBox.isChecked());
    cursorVisibleCheckBox.setOnCheckedChangeListener(resetOptionsListener);

    CheckBox spannableTextCheckBox = (CheckBox) findViewById(R.id.spannableText);
    if (spannableTextCheckBox.isChecked()) {
      tv.setText(sampleString(), TextView.BufferType.SPANNABLE);
    } else {
      tv.setText(sampleString());
    }
    spannableTextCheckBox.setOnCheckedChangeListener(resetOptionsListener);

    CheckBox focusableCheckBox = (CheckBox) findViewById(R.id.focusable);
    tv.setFocusable(focusableCheckBox.isChecked());
    focusableCheckBox.setOnCheckedChangeListener(resetOptionsListener);

    if (tv instanceof EditText) {
      EditText viewAsEditView = (EditText) tv;
    }

    tv.requestFocus();
    tv.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        tv.requestFocus();
      }
    });

    FrameLayout holder = (FrameLayout) findViewById(R.id.textViewHolder);
    holder.removeAllViews();
    holder.addView(tv);
  }

  private String sampleString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 100; i++) {
      sb.append(Integer.toString(i) + " ");
    }
    return sb.toString();
  }
}