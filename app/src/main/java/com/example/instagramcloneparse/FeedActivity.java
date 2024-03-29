package com.example.instagramcloneparse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    ListView listView;
    //postclass ı feedactivity e baglamak icin
    ArrayList<String> usernamesFromParse;
    ArrayList<String> userCommentsFromParse;
    ArrayList<Bitmap> userImageFromParse;
    PostClass postClass;//adaptor gibi kullacagız.




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//menu baglamasını yapıyoruz.

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {//menulerden birisi tıklanırsa ne olacagını soyleyecegız.

        if (item.getItemId()==R.id.add_post){
            //intent

            Intent intent=new Intent(getApplicationContext(),UploadActivity.class);
            startActivity(intent);

        }else if (item.getItemId()==R.id.logout){

            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {
                    if (e!=null){
                        Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                    }else{

                        Intent intent=new Intent(getApplicationContext(),SignUpActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        listView=findViewById(R.id.listView);//list view'i aktif hale getirdik.

        usernamesFromParse=new ArrayList<>();//initialize ediyoruz.
        userCommentsFromParse=new ArrayList<>();
        userImageFromParse=new ArrayList<>();

        postClass=new PostClass(usernamesFromParse,userCommentsFromParse,userImageFromParse,this);

        listView.setAdapter(postClass); //postClassımız arrayAdapter classına extend ettigi icin herhangi bir sorun cıkmıyor.

        download();

    }

    //suan dataları indirmeliyiz ve kaydetmeliyiz ayrı bir methodla

    public void download(){
        //download islemini parsequery kullanarak yapıyoruz.

        ParseQuery<ParseObject> query=ParseQuery.getQuery("Posts");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e!=null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }else{
                    if (objects.size()>0){
                        for (final ParseObject object:objects){

                            ParseFile parseFile=(ParseFile) object.get("image");
                            parseFile.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if (e==null && data!=null){
                                        //burda bitmap isleminde byte dizisine cevirmistik, simdi byte dizisini decode ederek iamge'a cevirecegiz
                                        Bitmap bitmap= BitmapFactory.decodeByteArray(data,0,data.length);

                                        userImageFromParse.add(bitmap);//dizimizin icerisine koyduk
                                        usernamesFromParse.add(object.getString("username"));
                                        userCommentsFromParse.add(object.getString("comment"));

                                        postClass.notifyDataSetChanged();
                                    }
                                }
                            });
                        }
                    }
                }
            }
        });

    }


}
