package com.lelocabdriver.baseclasses;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.lelocabdriver.MainActivity;
import com.lelocabdriver.R;


public class BaseFragment extends Fragment {


    private View progressBar;
    public static View progressBarOuter;
    private MainActivity activity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initProgressBarOuter((AppCompatActivity) getActivity());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void initProgressBarInner(LayoutInflater layoutInf, ViewGroup viewGroup) {
        // Inflate progress bar custom layout
        progressBar = layoutInf.inflate(R.layout.layout_progress, null);
        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        viewGroup.addView(progressBar);
    }

    public void showProgressInner() {
        if (progressBar != null)
            progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressInner() {
        if (progressBar != null)
            progressBar.setVisibility(View.GONE);
    }

    //This progress bar will cover entire view

    public void initProgressBarOuter(AppCompatActivity activity) {
        // Inflate progress bar custom layout
        this.activity = (MainActivity) activity;
        progressBarOuter = this.activity.getLayoutInflater().inflate(R.layout.layout_progress, null);
        progressBarOuter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        View v = this.activity.findViewById(android.R.id.content).getRootView();
        ViewGroup viewGroup = (ViewGroup) v;
        viewGroup.addView(progressBarOuter);
    }

    public void showProgressOuter() {
        if (progressBarOuter != null)
            progressBarOuter.setVisibility(View.VISIBLE);
    }


    public void hideProgressOuter() {
        if (progressBarOuter != null)
            progressBarOuter.setVisibility(View.GONE);
    }

    public void displayToast(String message) {
           /* Snackbar.make(getActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                    .show();*/
        Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorAccent));
        snackbar.show();
    }


    public void showDialog(AppCompatActivity activity, String title, DialogInterface.OnClickListener listener1, DialogInterface.OnClickListener listener2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //builder.setMessage(message);
        builder.setTitle(title);
        builder.setPositiveButton("Ok", listener1);
        builder.setNegativeButton("Cancel", listener2);
        builder.show();
    }


    public static final void hideKeyboard(AppCompatActivity ctx) {

        if (ctx.getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) ctx
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(ctx.getCurrentFocus().getWindowToken(),
                    0);
        }
    }

    public static final void showKeyboard(AppCompatActivity ctx) {

        if (ctx.getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) ctx
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
    }


    public void setOnClick(View view) {
        //  if(view.getId()==R.id.ivToolBar)
        //  {

        //  }
    }

    public String getFilePathFromUri(Uri uri, AppCompatActivity activity) {
        Cursor cursor = activity.managedQuery(uri,
                new String[]{MediaStore.Audio.Media.DATA},
                null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();

        return cursor.getString(column_index);
    }

    protected String getText(EditText editText) {
        return editText.getText().toString();
    }


}
