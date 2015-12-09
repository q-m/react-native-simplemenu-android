package org.thequestionmark.rnsimplemenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.content.DialogInterface;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;

public class SimpleMenuModule extends ReactContextBaseJavaModule {
  private final ReactApplicationContext mReactContext;
  private final Activity mMainActivity;

  public SimpleMenuModule(ReactApplicationContext reactContext, Activity mainActivity) {
    super(reactContext);

    mReactContext = reactContext;
    mMainActivity = mainActivity;
  }

  @Override
  public String getName() {
    return "UISimpleMenuManager";
  }

  @ReactMethod
  public void showDialog(final String title, final ReadableArray titles, final Callback callback) {
    String[] mTitles = new String[titles.size()];

    for (int i = 0; i < titles.size(); i++) {
      mTitles[i] = titles.getString(i);
    }

    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mMainActivity,
                         android.R.layout.select_dialog_item, mTitles);
    AlertDialog.Builder builder = new AlertDialog.Builder(mMainActivity);
    if (title != null) {
      builder.setTitle(title);
    }

    builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int index) {
        callback.invoke(index);
      }
    });

    final AlertDialog dialog = builder.create();

    /**
     * override onCancel method to callback cancel in case of a touch
     * outside of the dialog or the BACK key pressed
     */
    dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
      @Override
      public void onCancel(DialogInterface dialog) {
        dialog.dismiss();
      }
    });
    dialog.show();
  }

}
