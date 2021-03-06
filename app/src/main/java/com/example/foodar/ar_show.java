package com.example.foodar;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class ar_show extends AppCompatActivity {
    private ModelRenderable renderable;private String choose_dish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_ar_show);
        Toast t=Toast.makeText(getApplicationContext(),getIntent().getStringExtra("title"),Toast.LENGTH_SHORT);
        t.show();
        choose_dish=getIntent().getStringExtra("title");
        FirebaseApp.initializeApp(this);
        String path = "restaurant/"+choose_dish+"/out.glb";
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference modelRef = storage.getReference().child(path);

        ArFragment arFragment = (ArFragment) getSupportFragmentManager()
                .findFragmentById(R.id.arFragment);

        findViewById(R.id.downloadBtn)
                .setOnClickListener(v -> {

                    try {
                        File file = File.createTempFile("out", "glb");

                        modelRef.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                                buildModel(file);

                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });

        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {

            AnchorNode anchorNode = new AnchorNode(hitResult.createAnchor());
            anchorNode.setRenderable(renderable);
            Vector3 vector3 = new Vector3(0.1f,0.1f,0.1f);
            anchorNode.setLocalScale(vector3);
            arFragment.getArSceneView().getScene().addChild(anchorNode);


        });
    }

    private void buildModel(File file) {

        RenderableSource renderableSource = RenderableSource
                .builder()
                .setSource(this, Uri.parse(file.getPath()), RenderableSource.SourceType.GLB)
                .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                .build();

        ModelRenderable
                .builder()
                .setSource(this, renderableSource)
                .setRegistryId(file.getPath())
                .build()
                .thenAccept(modelRenderable -> {
                    Toast.makeText(this, "Model built", Toast.LENGTH_SHORT).show();;
                    renderable = modelRenderable;

                });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    switch(item.getItemId()){
        case R.id.home:finish();
        return true;
    }
    return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}