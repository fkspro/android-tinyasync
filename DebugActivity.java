package com.example.tinyasync;

import java.lang.ref.WeakReference;

import com.example.tinyasync.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class DebugActivity extends Activity {

    private static final String TAG = "DebugActivity";
    String dstring = "*** Debug Started ***\n\n";
    TextView dtv;

    protected void dwrite(String dout) {
	dstring += dout + "\n";
	dtv.setText(dstring);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_debug);
	dtv = (TextView) findViewById(R.id.debug_text);
	
	dwrite(">>> DebugActivity started on thread #"
	       + String.valueOf(android.os.Process.myTid()));

	// Start an asynchronous task
	WeakReference<DebugActivity> wr = new WeakReference<DebugActivity>(this);
	(new HazTask(wr)).start_task();

	dwrite(">>> onCreate finished!");
	dwrite("");
    }


    // Sample Task subclass specific to this activity.
    // Can make as many of these as needed.
    public static class HazTask extends TinyAsync {

	public HazTask(WeakReference<DebugActivity> wr) { super(wr); }

	// Each actable subclass does something back on the activity
	class dwrite extends Actable {
	    String dmsg;
	    public dwrite(String message) { dmsg = message; }
	    @Override public void actask() { ((DebugActivity) act_ref).dwrite(dmsg); }
	}

	// This is run on a background thread
	@Override public void main() {
	    act(new dwrite("Task started on thread #" 
			   + String.valueOf(android.os.Process.myTid())));
	    String r = GetUrl.hitthepage("http://icanhazip.com");
	    if(null != r) { act(new dwrite("My IP is " + r)); }
	}
    }

}
