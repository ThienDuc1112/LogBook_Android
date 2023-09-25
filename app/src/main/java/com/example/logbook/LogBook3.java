package com.example.logbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logbook.models.Profile;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LogBook3 extends AppCompatActivity implements View.OnClickListener{

    private TextInputEditText nameInput, DOBInput, emailInput;
    private TextInputLayout layout1,layout2,layout3;
    private RadioGroup radioGroup;
    private RecyclerView recyclerView;
    private ProfileAdapter profileAdapter;
    private RadioButton firstButton;
    private Button saveBtn, viewBtn;
    private Toolbar myToolbar;
    private List<Profile> profileList;
    private int idImage = -1;
    private boolean isValid = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_book3);
        profileList = new ArrayList<>();
        initView();
        DOBInput.setFocusable(false);
        DOBInput.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
        viewBtn.setOnClickListener(this);
        handleRequiredStatus();
        selectImage();

    }

    private void validate(){
        String name = nameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String DOB = DOBInput.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            nameInput.setError("Name is required");
        }else if(!isValidName(name)){
            nameInput.setError("Name must be alphabet characters");
        } else if (name.length() > 30||name.length() <6) {
            nameInput.setError("Name must be more than 5 characters and less than 30 characters");
        }else if (TextUtils.isEmpty(DOB)) {
            DOBInput.setError("Date of Birth is required");
        } else if (TextUtils.isEmpty(email)) {
            emailInput.setError("Email is required");
        } else if (!isValidEmail(email)) {
            emailInput.setError("Email is not valid");
        } else if (email.length() > 40) {
            emailInput.setError("Email must be less than 254 characters");
        } else if (idImage == -1) {
            new AlertDialog.Builder(LogBook3.this)
                    .setTitle("Warning")
                    .setMessage("You have to select an image profile")
                    .setNeutralButton("Back", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .show();
        } else{
            isValid = true;
        }
    }

    private void handleRequiredStatus(){
        nameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    layout1.setHelperTextEnabled(false);
                } else {
                    layout1.setHelperTextEnabled(true);
                    layout1.setHelperText("Required*");
                }
            }
        });
        DOBInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    layout2.setHelperTextEnabled(false);
                } else {
                    layout2.setHelperTextEnabled(true);
                    layout2.setHelperText("Required*");
                }
            }
        });
        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    layout3.setHelperTextEnabled(false);
                } else {
                    layout3.setHelperTextEnabled(true);
                    layout3.setHelperText("Required*");
                }
            }
        });
    }

    private boolean isValidName(String name) {
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!(Character.isLetter(c) || c == ' ')) {
                return false;
            }
        }
        return true;
    }
    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    private void initView(){
        layout1 = findViewById(R.id.editTextText);
        layout2 = findViewById(R.id.editTextText2);
        layout3 = findViewById(R.id.editTextText3);
        nameInput = (TextInputEditText)findViewById(R.id.nameEditText);
        DOBInput = (TextInputEditText)findViewById(R.id.DOBEditText);
        emailInput = (TextInputEditText)findViewById(R.id.emailEditText);
        radioGroup = findViewById(R.id.radio_group);
        firstButton = findViewById(R.id.radioButton1);
        saveBtn = findViewById(R.id.saveBtn);
        viewBtn = findViewById(R.id.viewBtn);
        myToolbar = findViewById(R.id.toolBar);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Contact Database");
        myToolbar.setTitleTextColor(getColor(R.color.purple));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.saveBtn){
            validate();
            if(isValid){
                handleAddProfile();
            }
        } else if (view.getId() == R.id.DOBEditText) {
            DialogFragment newFragment = new LogBook3.DatePickerFragment();
            newFragment.show(getSupportFragmentManager(), "datePicker");
        } else if (view.getId() == R.id.viewBtn) {
            Intent intent = new Intent(LogBook3.this, Detail.class);
            startActivity(intent);
        }
    }

    private void handleAddProfile(){
        String name = nameInput.getText().toString().trim();
        String DOB = DOBInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        if(idImage == -1){
            return;
        }
        Profile profile = new Profile(name,DOB,email,idImage);
        ProfileDatabase.getInstance(this).profileDAO().insertProfile(profile);
        nameInput.setText("");
        DOBInput.setText("");
        emailInput.setText("");
        firstButton.setChecked(true);
        Intent intent = new Intent(LogBook3.this, Notification.class);
        startActivity(intent);
    }

    private void selectImage(){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radioButton1){
                    idImage = R.drawable.amime;
                } else if (i == R.id.radioButton2) {
                    idImage = R.drawable.beastars;
                } else if (i == R.id.radioButton3) {
                    idImage = R.drawable.chainsaw;
                }else{
                    idImage = -1;
                }
            }
        });
    }

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            String selectedDate = day + "/" + (month + 1) + "/" + year;
            try {
                ((LogBook3) requireActivity()).DOBInput.setText(selectedDate);
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            LocalDate date = LocalDate.now();
            int year = date.getYear();
            int month = date.getMonthValue();
            int day = date.getDayOfMonth();
            return new DatePickerDialog(getActivity(), this, year, --month, day);
        }
    }
}