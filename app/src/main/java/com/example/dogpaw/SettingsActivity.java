package com.example.dogpaw;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity
{
    private CircleImageView profileImageView;
    private EditText nameedittxt;
    private EditText phoneedittxt;
    private EditText vehicletypeedittxt;
    private ImageView closebutton;
    private ImageView savebutton;
    private TextView prochangebutton;

    private  String getType;
    private String checker = "";
    private Uri imageurl;
    private String myurl = "";
    private StorageTask uploadTask;
    private StorageReference storagepropicref;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getType = getIntent().getStringExtra("type");
        Toast.makeText(this, getType, Toast.LENGTH_SHORT).show();

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Clients").child(getType);
        storagepropicref = FirebaseStorage.getInstance().getReference().child("Profile Pictures");

        profileImageView =findViewById(R.id.profile_image);

        nameedittxt = findViewById(R.id.name);
        phoneedittxt = findViewById(R.id.phoneno);

        vehicletypeedittxt =findViewById(R.id.drivercartype);
        if (getType.equals("Drivers"))
        {
            vehicletypeedittxt.setVisibility(View.VISIBLE);

        }
        closebutton = findViewById(R.id.closebuttonsetting);
        savebutton = findViewById(R.id.savebuttonsetting);

        prochangebutton =findViewById(R.id.changepicbtn);

        closebutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (getType.equals("Drivers"))
                {
                    startActivity(new Intent(SettingsActivity.this, DriversMapActivity.class));
                }
                else
                {
                    startActivity(new Intent(SettingsActivity.this, CustomersMapActivity.class));

                }
            }
        });
        savebutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(checker.equals("clicked"))
                {
                    validateControllers();
                }
                else
                {
                    validatingwithdata();

                }
            }
        });
        prochangebutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checker = "clicked";
                CropImage.activity().setAspectRatio(1,1).start(SettingsActivity.this);


            }
        });

        getuserInfo();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)

    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK && data!=null)
        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageurl = result.getUri();
            profileImageView.setImageURI(imageurl);
        }
        else
        {
            if (getType.equals("Drivers"))
            {
                startActivity(new Intent(SettingsActivity.this, DriversMapActivity.class));
                // vehicletypeedittxt.setVisibility(View.VISIBLE);

            }
            else
            {
                startActivity(new Intent(SettingsActivity.this, CustomersMapActivity.class));
                // startActivity(new Intent(SettingsActivity.this, DriversMapActivity.class));
            }

            Toast.makeText(this, "Error Please Try again",Toast.LENGTH_SHORT).show();
        }
    }

    private void validateControllers()
    {
        if(TextUtils.isEmpty(nameedittxt.getText().toString()))
        {
            Toast.makeText(this, " Please Insert Name",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(nameedittxt.getText().toString()))
        {
            Toast.makeText(this, " Please Insert PhoneNo",Toast.LENGTH_SHORT).show();
        }
        else if(getType.equals("Drivers") && TextUtils.isEmpty(vehicletypeedittxt.getText().toString()))
        {
            Toast.makeText(this, " Please Insert Vehicle Type",Toast.LENGTH_SHORT).show();
        }
        else if (checker.equals("clicked"))
        {
            uploadPropic();
        }
    }

    private void uploadPropic()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Account Information");
        progressDialog.setMessage("Updating Account Information");
        progressDialog.show();

        if(imageurl != null)
        {
            final StorageReference fileRef = storagepropicref.child(mAuth.getCurrentUser().getUid() + ".jpg");
            uploadTask = fileRef.putFile(imageurl);
            uploadTask.continueWithTask(new Continuation()
            {
                @Override
                public Object then(@NonNull Task task) throws Exception
                {
                    if (!task.isSuccessful())
                    {
                        throw task.getException();
                    }
                    return  fileRef.getDownloadUrl();

                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>()
            {
                @Override
                public void onComplete(@NonNull Task <Uri> task)
                {
                    if(task.isSuccessful())
                    {
                        Uri downloadURL = task.getResult();
                        assert downloadURL != null;
                        myurl = downloadURL.toString();

                        HashMap<String,Object> userMap = new HashMap<>();
                        userMap.put("uid", mAuth.getCurrentUser().getUid());
                        userMap.put("name", nameedittxt.getText().toString());
                        userMap.put("phone", phoneedittxt.getText().toString());
                        userMap.put("image", myurl);

                        if (getType.equals("Drivers"))
                        {
                            userMap.put("Vehicle", vehicletypeedittxt.getText().toString());
                        }
                        databaseReference.child(mAuth.getCurrentUser().getUid()).updateChildren(userMap);
                        progressDialog.dismiss();
                        if (getType.equals("Drivers"))
                        {
                            startActivity(new Intent(SettingsActivity.this, DriversMapActivity.class));
                        }
                        else
                        {
                            startActivity(new Intent(SettingsActivity.this, CustomersMapActivity.class));
                        }
                    }
                }
            });
        }
        else
        {
            Toast.makeText(this, " Image not Selected",Toast.LENGTH_SHORT).show();
        }
    }


    private void validatingwithdata()
    {
        if(TextUtils.isEmpty(nameedittxt.getText().toString()))
        {
            Toast.makeText(this, " Please Insert Name",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(nameedittxt.getText().toString()))
        {
            Toast.makeText(this, " Please Insert PhoneNo",Toast.LENGTH_SHORT).show();
        }
        else if(getType.equals("Drivers") && TextUtils.isEmpty(vehicletypeedittxt.getText().toString()))
        {
            Toast.makeText(this, " Please Insert Vehicle Type",Toast.LENGTH_SHORT).show();
        }
        else
        {
            HashMap<String,Object> userMap = new HashMap<>();
            userMap.put("uid", mAuth.getCurrentUser().getUid());
            userMap.put("name", nameedittxt.getText().toString());
            userMap.put("phone", phoneedittxt.getText().toString());


            if (getType.equals("Drivers"))
            {
                userMap.put("Vehicle", vehicletypeedittxt.getText().toString());
            }
            databaseReference.child(mAuth.getCurrentUser().getUid()).updateChildren(userMap);

            if (getType.equals("Drivers"))
            {
                startActivity(new Intent(SettingsActivity.this, DriversMapActivity.class));
            }
            else
            {
                startActivity(new Intent(SettingsActivity.this, CustomersMapActivity.class));
            }
        }
    }


    private void getuserInfo()
    {
        databaseReference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount() >0)
                {
                    String name = dataSnapshot.child("name").getValue().toString();
                    String phone = dataSnapshot.child("phone").getValue().toString();

                    nameedittxt.setText(name);
                    phoneedittxt.setText(phone);

                    if (getType.equals("Drivers"))
                    {
                        String vehicle = dataSnapshot.child("Vehicle").getValue().toString();
                        vehicletypeedittxt.setText(vehicle);
                    }

                    if (dataSnapshot.hasChild("image"))
                    {
                        String image = dataSnapshot.child("image").getValue().toString();
                        Picasso.get().load(image).into(profileImageView);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {


            }
        });
    }
}
