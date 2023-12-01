package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class Ngodocument extends AppCompatActivity {
    Button btnbrowse, btnupload;
    EditText about ;
    ImageView imgview;
    Uri FilePathUri;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngodocument);
        storageReference = FirebaseStorage.getInstance().getReference("Document");
        databaseReference = FirebaseDatabase.getInstance().getReference("Admin");
        btnbrowse = (Button)findViewById(R.id.btnbrowse1);
        btnupload= (Button)findViewById(R.id.btnupload1);
        about = (EditText)findViewById(R.id.aboutorg);
        imgview = (ImageView)findViewById(R.id.image_view);
        progressDialog = new ProgressDialog(Ngodocument.this);// context name as per your project name
        btnbrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), Image_Request_Code);

            }
        });
        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String ngoinfo = about.getText().toString().trim();
                if (ngoinfo.isEmpty()||ngoinfo.length()<10){
                    about.setError("Please enter minimum 10 words");
                    about.requestFocus();
                    return;
                }
                UploadImage();

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();
            Picasso.with(this).load(FilePathUri).into(imgview);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                imgview.setImageBitmap(bitmap);
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }


    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }


    public void UploadImage() {

        if (FilePathUri != null) {

            progressDialog.setTitle("Image is Uploading...");
            progressDialog.show();
            final StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));
            storageReference2.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            storageReference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String TempImageName = about.getText().toString().trim();
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();
                                    @SuppressWarnings("VisibleForTests")
                                    uploadinfo1 imageUploadInfo = new uploadinfo1(TempImageName, uri.toString());
                                    String ImageUploadId = databaseReference.push().getKey();
                                    databaseReference.child(ImageUploadId).setValue(imageUploadInfo);
                                    Toast.makeText(Ngodocument.this, "IF YOUR ID IS VALID YOU WILL GET A CONFORMATION EMAIL", Toast.LENGTH_LONG).show();
                                }
                            });

                        }
                    });
        }
        else {

            Toast.makeText(Ngodocument.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }
    }

}