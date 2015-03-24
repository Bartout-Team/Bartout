package ch.zhaw.bartout.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import ch.zhaw.bartout.R;

/**
 * Created by srueg on 24.03.15.
 */
public abstract class BaseActivity extends Activity {

    private final int layoutId;
    private final boolean homeAsUp;

    public BaseActivity(int layoutId){
        this(layoutId, true);
    }

    public BaseActivity(int layoutId, boolean homeAsUp){
        this.layoutId = layoutId;
        this.homeAsUp = homeAsUp;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        getActionBar().setDisplayHomeAsUpEnabled(homeAsUp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
