package com.example.moritamasashi.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DialogEx extends Activity implements View.OnClickListener{

    private final static int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
    private final static String TAG_MESSAGE = "0";
    private final static String TAG_YESNO = "1";
    private final static String TAG_TEXT = "2";
    private final static String TAG_IMAGE = "3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Creating layout
        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.WHITE);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        //Creating Button
        layout.addView(makeButton("Show the message dialog", TAG_MESSAGE));
        layout.addView(makeButton("Show the YES/NO dialog", TAG_YESNO));
        layout.addView(makeButton("Show the inputting text dialog", TAG_TEXT));
        layout.addView(makeButton(res2bmp(this, R.drawable.icon), TAG_IMAGE));
    }


    //Creating Buttons
    private Button makeButton(String text, String tag) {
        Button button = new Button(this);
        button.setText(text);
        button.setTag(tag);
        button.setOnClickListener(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
        return button;

    }


    //Creating Image Button
    private ImageButton makeButton(Bitmap bmp, String tag) {
        ImageButton img = new ImageButton(this);
        img.setTag(tag);
        img.setOnClickListener(this);
        img.setImageBitmap(bmp);
        img.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
        return img;
    }

    //change resource to bitmap
    public Bitmap res2bmp(Context text, int resID) {
        return BitmapFactory.decodeResource(text.getResources(), resID);
    }

    //It calls when puch button
    public void onClick(View view) {
        String tag = (String) view.getTag();

        if (TAG_MESSAGE.equals(tag)) {
            MessageDialog.show(this, "Message Dialog", "Pushed Button");
        } else if (TAG_YESNO.equals(tag)) {
            MessageDialog.show(this, "Yes/No Dialog", "Pushed Button");
        } else if (TAG_TEXT.equals(tag)) {
            MessageDialog.show(this, "Inputting text Dialog", "Pushed Button");
        } else if (TAG_IMAGE.equals(tag)) {
            MessageDialog.show(this, "Image Button Dialog", "Pushed Button");
        }
    }


    //defenision of Message Dialog
    public static class MessageDialog extends DialogFragment {

        public static void show(Activity activity, String title, String text) {
            MessageDialog f = new MessageDialog();
            Bundle args = new Bundle();
            args.putString("title", title);
            args.putString("text", text);
            f.setArguments(args);
            f.show(activity.getFragmentManager(), "messageDialog");
        }

        //Creating Dialog
        @Override
        public Dialog onCreateDialog(Bundle bundle) {
            AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
            ad.setTitle(getArguments().getString("title"));
            ad.setMessage(getArguments().getString("text"));
            ad.setPositiveButton("ok", null);
            return ad.create();
        }
    }

    //YES/NO dialog
    public static class YesNoDialog extends DialogFragment {

        public static void show(Activity activity, String title, String text) {
            YesNoDialog f = new YesNoDialog();
            Bundle args = new Bundle();
            args.putString("title", title);
            args.putString("text", text);
            f.setArguments(args);
            f.show(activity.getFragmentManager(), "YesNoDialog");
        }

        //Creating YesNoDialog
        @Override
        public Dialog onCreateDialog(Bundle bundle) {

            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    if (which == DialogInterface.BUTTON_NEGATIVE) {
                        MessageDialog.show(getActivity(), "", "NO");
                    } else if (which == DialogInterface.BUTTON_POSITIVE) {
                        MessageDialog.show(getActivity(), "", "YES");
                    }
                }
            };

            AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
            ad.setTitle(getArguments().getString("title"));
            ad.setMessage(getArguments().getString("texdt"));
            ad.setPositiveButton("YES", listener);
            ad.setNegativeButton("NO", listener);
            return ad.create();
        }
    }

    ;


    //Inputting text Dialog
    public static class TextDialog extends DialogFragment {
        private EditText editText;

        public static void show(Activity activity, String title, String text) {
            TextDialog f = new TextDialog();
            Bundle args = new Bundle();
            args.putString("title", title);
            args.putString("text", text);
            f.setArguments(args);
            f.show(activity.getFragmentManager(), "textDialog");
        }

        @Override
        public Dialog onCreateDialog(Bundle bundle) {
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    MessageDialog.show(getActivity(), "", editText.getText().toString());
                }
            };

            editText = new EditText(getActivity());

            AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
            ad.setTitle(getArguments().getString("title"));
            ad.setMessage(getArguments().getString("text"));
            ad.setView(editText);
            ad.setPositiveButton("OK", listener);

            if (bundle != null) editText.setText(bundle.getString("editTexst", ""));
            return ad.create();
        }

        @Override
        public void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            bundle.putString("editText", editText.getText().toString());
        }

    };

};


