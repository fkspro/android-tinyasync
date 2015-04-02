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
src/com/example/tinyasync, and the Android manifest and layout files
to their appropriate places.

## Further reading

This is a degenerate example of the half-sync, half-async pattern:

https://www.linux.com/community/blogs/132-mobile/750382-android-asynctask-internal-half-sync-half-async-design-pattern

Instead of a queue a new thread is fired off for every request.  For
infrequent tasks (such as a user pressing a button) there's no need
for queuing, and it also allows for the tasks to be asynchronous from
each other.

## Motivation

If it's still not clear why anyone would want to do this ever, I came
up with some use cases:

* Debugging and testing.  Robotium is great but its focus is testing
the app from the perspective of the user.  If you need to figure out a
tricky bug between your internal app logic and your api server it may
be a lot easier to whip up a dozen lines of java that trigger the bug
directly and print debugging info to a textview.

* UX user testing, psych experiments.  You can easily and quickly
graft arbitrary functionality onto existing app code, from sending
certain button presses to a server to a full remote monitoring and
control system.

* Any situation where the user is not the one "driving" the app.  For
example, an automated industrial testing system might need a worker to
go to various parts of the plant, activate switches, record video,
etc.  This is an extreme inversion of control example, where most of
the application is controlled by the server and the user responds to
activities that the server pops up, rather than the (usual) other way
around.

* You loved HyperCard.  You like procedural programming and blocking
I/O.  You secretly wish you could write Android apps like 90s perl
CGIs, where each page on the web server corresponded to a single perl
file containing all the code needed to display and process that page.

## Future plans?

Take this insanity to its logical conclusion and use coroutines, so
you can just yield() on anything that might block and otherwise run
all your code directly within the activity instead of runnables on
external threads.
