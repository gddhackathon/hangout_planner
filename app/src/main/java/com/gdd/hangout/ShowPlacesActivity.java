package com.gdd.hangout;

import android.app.ListActivity;
import android.os.Bundle;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
//import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ShowPlacesActivity extends AppCompatActivity {

    List<GooglePlace> venuesList;
    final String GOOGLE_KEY = "AIzaSyA2pWuAzJ_agDXpISSGDEh1hnk6B7SPMOw";

    // we will need to take the latitude and the logntitude from a certain point
    // this is the center of New York
    final String latitude = "40.7463956";
    final String longtitude = "-73.9852992";

    ArrayAdapter<String> myAdapter;

    ListView placesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_places);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        placesListView = (ListView) findViewById(R.id.placesListView);
        new googleplaces().execute();
    }

    private class googleplaces extends AsyncTask<Void,Void,String> {
        String temp;

       @Override
       protected String doInBackground(Void... params) {
            // make Call to the url
            temp = makeCall("https://maps.googleapis.com/maps/api/place/search/json?location=" + latitude + "," + longtitude + "&radius=100&sensor=true&key=" + GOOGLE_KEY);
            //print the call in the console
            System.out.println("https://maps.googleapis.com/maps/api/place/search/json?location=" + latitude + "," + longtitude + "&radius=100&sensor=true&key=" + GOOGLE_KEY);
           return temp;
        }

        @Override
        protected void onPreExecute() {
            // we can start a progress bar here
        }

        @Override
        protected void onPostExecute(String result) {
            if (temp == null) {
                // we have an error to the call
                // we can also stop the progress bar
            } else {
                // all things went right
                // parse Google places search result
                venuesList = parseGoogleParse(temp);
                List listTitle = new ArrayList();
                for (GooglePlace place: venuesList) {

                    // make a list of the venus that are loaded in the list.
                    // show the name, the category and the city
                    listTitle.add(place.getName() + "\nOpen Now: " + place.getOpenNow() + "\n(" + place.getCategory() + ")");
                }

                // set the results to the list and show them in the xml
                String temp[] = (String[])listTitle.toArray(new String[listTitle.size()]);
               myAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.places_list_item,temp);
                placesListView.setAdapter(myAdapter);
            }
        }
    }

    public static String makeCall(String url) {

        // string buffers the url
        StringBuffer buffer_string = new StringBuffer(url);
        String replyString = "";

        // instanciate an HttpClient
     //   HttpClient httpclient = new DefaultHttpClient();


        // instanciate an HttpGet
       // HttpGet httpget = new HttpGet(buffer_string.toString());
        try {
            URL placesUrl = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) placesUrl.openConnection();
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            System.out.println(buffer.toString());

//
//            // get the responce of the httpclient execution of the url
//            //HttpResponse response = httpclient.execute(httpget);
//           // InputStream is = response.getEntity().getContent();
//            InputStream bis= new BufferedInputStream(urlConnection.getInputStream());
//            System.out.print("Rishi :" + bis);
//
//            // buffer input stream the result
////            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayBuffer baf = new ByteArrayBuffer(20);
            int current = 0;

            while ((current = reader.read()) != -1) {

                baf.append((byte) current);
            }
//
//            // the result as a string is ready for parsing
            replyString = new String(baf.toByteArray());
//            System.out.print("Rishi2 :" + replyString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(replyString);

        // trim the whitespaces
        return replyString.trim();
    }


    private static List<GooglePlace> parseGoogleParse(final String response) {

        List<GooglePlace> temp = new ArrayList<GooglePlace>();

        try {
            // make an jsonObject in order to parse the response
            JSONObject jsonObject = new JSONObject(response);

            if (jsonObject.has("results")) {

                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {
                    GooglePlace poi = new GooglePlace();

                    if (jsonArray.getJSONObject(i).has("name")) {

                        poi.setName(jsonArray.getJSONObject(i).optString("name"));
                        poi.setRating(jsonArray.getJSONObject(i).optString("rating", " "));

                        if (jsonArray.getJSONObject(i).has("opening_hours")) {
                            if (jsonArray.getJSONObject(i).getJSONObject("opening_hours").has("open_now")) {
                                if (jsonArray.getJSONObject(i).getJSONObject("opening_hours").getString("open_now").equals("true")) {
                                    poi.setOpenNow("YES");
                                } else {
                                    poi.setOpenNow("NO");
                                }
                            }
                        } else {
                            poi.setOpenNow("Not Known");
                        }

                        if (jsonArray.getJSONObject(i).has("types")) {
                            JSONArray typesArray = jsonArray.getJSONObject(i).getJSONArray("types");
                            for (int j = 0; j < typesArray.length(); j++) {
                                poi.setCategory(typesArray.getString(j) + ", " + poi.getCategory());
                            }
                        }
                    }
                    temp.add(poi);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<GooglePlace>();
        }
        return temp;
    }
}
