# android-tinyasync

One stumbling block for Android developers is that Activities are not
allowed to block.  Any app communicating with a server directly has to
do this in a separate thread, usually with AsyncTask.  There is a lot
of confusion on how to do this, and a lot of different approaches.
Some developers even redesign their core workflows to use event-driven
patterns like RX Android.

This humble example shows how to do this mostly procedurally.  The
code that makes web requests is run in a separate thread, a
synchronous tasklet.  Activities can now contain both the UI and logic
in one place in the source code.

I'm a terrible java coder and I know this flies in the face of
orthodox Android development.  Neverthless I'm optimistic the internet
will judge me kindly.


## Instructions

Make a new com.example.tinyasync app.  Copy the three java files into
src/com/example/tinyasync and the Android manifest.
