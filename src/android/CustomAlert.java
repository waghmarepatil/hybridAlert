package com.pramod.customalert;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import io.ionic.starter.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;


public class CustomAlert extends CordovaPlugin {

  /**
   * The log tag for this plugin
   */
  static protected final String LOG_TAG = "CustomAlert";
  // The callback context used when calling back into JavaScript
  private CallbackContext command;



  /**
   * Executes the request.
   *
   * This method is called from the WebView thread.
   * To do a non-trivial amount of work, use:
   *     cordova.getThreadPool().execute(runnable);
   *
   * To run on the UI thread, use:
   *     cordova.getActivity().runOnUiThread(runnable);
   *
   * @param action   The action to execute.
   * @param args     The exec() arguments in JSON form.
   * @param callback The callback context used when calling
   *                 back into JavaScript.
   * @return         Whether the action was valid.
   */
  @Override
  public boolean execute (String action, JSONArray args,
                          CallbackContext callback) throws JSONException {

    this.command = callback;

    Log.d(LOG_TAG,"IN METHOD FOLDER:"+action);

    if ("showDialog".equalsIgnoreCase(action)) {
      open(args);
      return true;
    }

    return false;
  }

  /**
   * Returns the application context.
   */
  private Context getContext() { return cordova.getActivity(); }


  /**
   * Sends an intent to the email app.
   *
   * @param args
   * The email properties like subject or body
   * @throws JSONException
   */
  private void open (JSONArray args) throws JSONException {
    Log.d(LOG_TAG,"IN OPOEN FOLDER");

    final Dialog dialog = new Dialog(getContext());//(new ContextThemeWrapper(this, R.style.DialogSlideAnim));
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setCancelable(false);
    dialog.setContentView(R.layout.custom_dialog);

    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

    dialog.getWindow().setLayout(MATCH_PARENT,MATCH_PARENT);

    RelativeLayout relativeContainer = dialog.findViewById(R.id.relativeContainer);


    ObjectAnimator objectanimator1 = ObjectAnimator.ofFloat(relativeContainer,"y",250);
    objectanimator1.setDuration(300);
    objectanimator1.start();


    ImageView ivIcon = dialog.findViewById(R.id.ivIcon);

    TextView txtCustomTitle = dialog.findViewById(R.id.txtCustomTitle);
    TextView txtCustomDesc = dialog.findViewById(R.id.txtCustomDesc);
    Button btnOk = dialog.findViewById(R.id.btnOk);

    if (args.getString(3).equalsIgnoreCase("success")){
      ivIcon.setImageResource(R.drawable.ic_success);
      btnOk.setBackgroundColor(Color.parseColor("#00b050"));
    }else{
      ivIcon.setImageResource(R.drawable.ic_error);
      btnOk.setBackgroundColor(Color.parseColor("#FFFF0000"));
    }


    txtCustomTitle.setText(args.getString(0));
    txtCustomDesc.setText(args.getString(1));
    btnOk.setText(args.getString(2));

    btnOk.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dialog.dismiss();
        command.success();
      }
    });

    dialog.show();


  }



}
