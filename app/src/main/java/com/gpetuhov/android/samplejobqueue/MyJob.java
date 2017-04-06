package com.gpetuhov.android.samplejobqueue;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;

import timber.log.Timber;

// Job to be done in background
public class MyJob extends Job {

    public static final String LOG_TAG = MyJob.class.getName();

    // Job priority
    public static final int PRIORITY = 1;

    protected MyJob() {
        // This job requires network connectivity,
        // and should be persisted in case the application exits before job is completed.
        super(new Params(PRIORITY).requireNetwork().persist());

        // Set tag for logging
        Timber.tag(LOG_TAG);
    }

    @Override
    public void onAdded() {
        // Job has been saved to disk.
        Timber.d("Job added to disk and scheduled to run");
    }

    @Override
    public void onRun() throws Throwable {
        Timber.d("Start executing job");

        // Imitate job logic
        Thread.sleep(5000);

        Timber.d("Job complete");
    }

    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {
        // Job has exceeded retry attempts or shouldReRunOnThrowable() has decided to cancel.
        Timber.d("Job cancelled");
    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable, int runCount, int maxRunCount) {
        // An error occurred in onRun.
        Timber.d("Error executing job");

        // Return retry strategy.
        return RetryConstraint.createExponentialBackoff(runCount, 1000);
    }
}