package com.gpetuhov.android.samplejobqueue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.birbit.android.jobqueue.JobManager;

public class MainActivity extends AppCompatActivity {

    private JobManager jobManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Get Job Manager from the application
        jobManager = SampleJobQueueApp.getInstance().getJobManager();

        // Create new job and add it to Job Manager
        jobManager.addJobInBackground(new MyJob());

        // In this example new job will be scheduled every time activity becomes active.
        // Previously scheduled jobs are not removed from queue on activity recreation
        // until they are completed.
    }
}