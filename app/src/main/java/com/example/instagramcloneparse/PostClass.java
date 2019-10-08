package com.example.instagramcloneparse;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PostClass extends ArrayAdapter<String> {

    private  final ArrayList<String> username;
    private final ArrayList<String> userComment;
    private final ArrayList<Bitmap> userImage;
    private final Activity context; //constructor icin context almamız gerekiyor o yuzden Activity olusturduk.

    public PostClass(ArrayList<String> username, ArrayList<String> userComment, ArrayList<Bitmap> userImage,Activity context){
        super(context,R.layout.custom_view,username);
        this.username=username;
        this.userComment=userComment;
        this.userImage=userImage;
        this.context=context;
    }//bir sınıf yaratıldı ve dort degiskeni vermemizi isteyecek diger sınıflar.
    //custom_view a post class ı bagladık.


    @NonNull
    @Override//override edebilecegimiz arrayadaptor metodu mevcut.

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater=context.getLayoutInflater();
        View customView=layoutInflater.inflate(R.layout.custom_view,null,true);

        //customView sayesinde username,commenttext,imageview objelerini burda koda yazdırabildik.
        TextView userNameText=customView.findViewById(R.id.custom_view_user_name_text);
        TextView commentText=customView.findViewById(R.id.custom_view_comment_text);
        ImageView imageView=customView.findViewById(R.id.custom_view_imageView);

        userNameText.setText(username.get(position));
        //feed activityden yollanan arraylisti buraya kaydettik onuda customview a ilettik

        imageView.setImageBitmap(userImage.get(position));
        commentText.setText(userComment.get(position));



        return customView;//boylece customview bu post class icerisine dahil oldu.r
    }
}
