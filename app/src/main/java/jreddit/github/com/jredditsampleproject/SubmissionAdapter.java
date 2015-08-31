package jreddit.github.com.jredditsampleproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jreddit.entity.Submission;

import java.util.List;

/**
 * Created by edward on 8/15/15.
 */
public class SubmissionAdapter extends ArrayAdapter {
    Activity context;
    List<Submission> s;

    public SubmissionAdapter(Activity context,List<Submission> s)
    {
        super(context,R.layout.listviewitem,s);
        this.s = s;
        this.context=context;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listviewitem, null,true);

        TextView postTitle;
        postTitle=(TextView)rowView
                .findViewById(R.id.post_title);

        TextView postDetails;
        postDetails=(TextView)rowView
                .findViewById(R.id.post_details);

        TextView postScore;
        postScore=(TextView)rowView
                .findViewById(R.id.post_score);



        ImageView postImage;
        postImage = (ImageView)rowView
                .findViewById(R.id.post_thumbnail);



        postTitle.setText(s.get(position).getTitle());
        postDetails.setText(s.get(position).getSubreddit());
        postScore.setText(s.get(position).getScore().toString());


        return rowView;
    }
}

