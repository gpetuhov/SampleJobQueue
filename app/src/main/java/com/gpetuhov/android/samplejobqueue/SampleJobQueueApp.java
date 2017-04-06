package com.gpetuhov.android.samplejobqueue;

import android.app.Application;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;

import timber.log.Timber;

public class SampleJobQueueApp extends Application {

    private static SampleJobQueueApp instance;

    private JobManager jobManager;

    public static SampleJobQueueApp getInstance() {
        return instance;
    }

    public JobManager getJobManager() {
        if (null == jobManager) {
            configureJobManager();
        }
        return jobManager;
    }

    private void configureJobManager() {
        // Job Manager needs configuration
        // Here we just create configuration with default values
        Configuration configuration = new Configuration.Builder(this).build();

        // Create Job Manager
        jobManager = new JobManager(configuration);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        // Initialize Timber
        if (BuildConfig.DEBUG) {
            // Here we use Timber DebugTree for logging in debug version
            Timber.plant(new Timber.DebugTree());
        } else {
            // Here we may use some crash library to report crashes in release version
        }
    }
}