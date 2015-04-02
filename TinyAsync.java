package com.example.tinyasync;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;


public class TinyAsync<ActClass extends Activity> implements Runnable {

    protected WeakReference<ActClass> weact = null;

    // Runnable with activity reference
    public class Actable implements Runnable {
	protected ActClass act_ref = null;

	@Override public void run() {
	    if(weact != null) {
		act_ref = weact.get();
		if(act_ref != null) {
		    actask();
		}
	    }
	}

	// Override this class, has access to act_ref
	public void actask() { return; }
    }


    public TinyAsync(WeakReference<ActClass> wref) {
	this.weact = wref;
    }

    @Override
    public void run() {
	Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
	main();
    }

    // Run activity runnable if possible
    public void act(Actable a) {
	(new Handler(Looper.getMainLooper())).post(a);
    }

    // Start the thread that will run this task
    public void start_task() {
	(new Thread(this)).start();
    }


    // Override these in subclasses

    // Main program, run in separate thread, can block on api requests etc
    public void main() { return; }

}
