package com.tom.async;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void go1(View view) {
        new Job1Task().execute();
    }

    public void go2(View view) {
        new Job2Task().execute(3);
    }

    public void go3(View view) {
        new Job3Task().execute(6);
    }

    class Job1Task extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TextView info = findViewById(R.id.info);
            info.setText("DONE");
        }
    }

    class Job2Task extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            try {
                Thread.sleep(integers[0] * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TextView info = findViewById(R.id.info);
            info.setText("DONE");
        }
    }

    class Job3Task extends AsyncTask<Integer, Integer, Void> {
        private TextView info;

        public Job3Task() {
            info = findViewById(R.id.info);
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            for (int i = 0; i < integers[0]; i++) {
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            info.setText(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            info.setText("DONE");
        }
    }
}
