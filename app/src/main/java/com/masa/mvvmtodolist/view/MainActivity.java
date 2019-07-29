package com.masa.mvvmtodolist.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.NotificationManagerCompat;
import androidx.databinding.DataBindingUtil;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Person;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.masa.mvvmtodolist.R;
import com.masa.mvvmtodolist.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private static final String CHANNEL_ID = "mychannelid";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bubblehead:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    createGoogleBubble();
                }
                break;



            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setContentView(binding.getRoot());
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    void createGoogleBubble(){

        createNotificationChannel();

        Intent target = new Intent(this, MainActivity.class);
        PendingIntent bubbleIntent =
                PendingIntent.getActivity(this, 0, target, 0 );

        Notification.BubbleMetadata bubbleData =
                new Notification.BubbleMetadata.Builder()
                        .setDesiredHeight(600)
                        .setIcon(Icon.createWithResource(this, R.drawable.ic_launcher_foreground))
                        .setIntent(bubbleIntent)
                        .setAutoExpandBubble(true)
                        .build();

        Person chatBot = new Person.Builder()
                .setBot(true)
                .setName("TodoBubble")
                .setImportant(true)
                .build();

        Notification.Builder builder =
                new Notification.Builder(this, CHANNEL_ID)
                        .setContentTitle("Your to-do list is here")
                        .setContentText("Click to open to-do list")
                        .setContentIntent(bubbleIntent)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setBubbleMetadata(bubbleData)
                        .setAutoCancel(true)
                        .addPerson(chatBot);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1002, builder.build());

    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "name";
            String description = "descript";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            channel.setAllowBubbles(true);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
