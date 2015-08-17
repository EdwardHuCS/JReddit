package jreddit.github.com.jredditsampleproject;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;


import com.github.jreddit.entity.Submission;
import com.github.jreddit.entity.User;
import com.github.jreddit.retrieval.Submissions;
import com.github.jreddit.retrieval.params.SubmissionSort;
import com.github.jreddit.utils.restclient.PoliteHttpRestClient;
import com.github.jreddit.utils.restclient.RestClient;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by edward on 8/14/15.
 */
public class ListViewFragment extends Fragment{
    public ListView listView;
    List<Submission> submissionList;
    Handler handler;

    public static Fragment newInstance() {
        ListViewFragment fragment = new ListViewFragment();
        return fragment;
    }

    public ListViewFragment() {
        submissionList = new ArrayList<Submission>();
        handler=new Handler();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_list_view, container, false);
        listView = (ListView)v.findViewById(R.id.listView);
        return v;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        if(submissionList.size()==0){

            // Must execute network tasks outside the UI
            // thread. So create a new thread.

            new Thread(){
                public void run(){
                    RestClient restClient = new PoliteHttpRestClient();
                restClient.setUserAgent("bot/1.0 by name");

                // Connect the user
                // change USER and PASSWORD with your own credentials
                User user = new User(restClient, "edwardthegreat2", "Arcsaber8dx");
                try {
                    user.connect();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                                    // Handle to Submissions, which offers the basic API submission functionality
                Submissions subms = new Submissions(restClient, user);


                // Retrieve submissions of a submission
                submissionList= subms.ofSubreddit("all", SubmissionSort.TOP, -1, 100,
                        null, null,
                        true);

                    // UI elements should be accessed only in
                    // the primary thread, so we must use the
                    // handler here.

                    handler.post(new Runnable(){
                        public void run(){

                            SubmissionAdapter adapter = new SubmissionAdapter(getActivity(),submissionList);
                            listView.setAdapter(adapter);


                        }
                    });
                }
            }.start();
        }

    }

}//end class
