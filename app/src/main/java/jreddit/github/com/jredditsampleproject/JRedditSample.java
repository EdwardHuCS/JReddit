package jreddit.github.com.jredditsampleproject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


public class JRedditSample extends FragmentActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_j_reddit_sample);
        getFragmentManager().beginTransaction().add(R.id.fragment_holder,ListViewFragment
                .newInstance())
                .commit();
    }

    public void refresh (View v)
    {
        Toast.makeText(getBaseContext(), "refresh", Toast.LENGTH_LONG).show();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_j_reddit_sample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
