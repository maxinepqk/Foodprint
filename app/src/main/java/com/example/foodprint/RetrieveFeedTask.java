package com.example.foodprint;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//need this to add gson
import com.google.gson.Gson;

class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

    public static String barcode;
    public static String productName;

    //copied from here from barcodelookup.com api documentation
    public class Product
    {
        public String barcode_number;
        public String barcode_type;
        public String barcode_formats;
        public String mpn;
        public String model;
        public String asin;
        public String product_name;
        public String title;
        public String category;
        public String manufacturer;
        public String brand;
        public String label;
        public String author;
        public String publisher;
        public String artist;
        public String actor;
        public String director;
        public String studio;
        public String genre;
        public String audience_rating;
        public String ingredients;
        public String nutrition_facts;
        public String color;
        public String format;
        public String package_quantity;
        public String size;
        public String length;
        public String width;
        public String height;
        public String weight;
        public String release_date;
        public String description;
        public Object[] features;
        public String[] images;
//        public Store[] stores;
//        public Review[] reviews;
    }
    public class RootObject
    {
        public Product[] products;
    }

    private Exception exception;
//    Button btnAddItem;

//    @Override
    protected void onPreExecute() {
        //do some stuff here
    }
//    @Override
    protected String doInBackground(Void... urls) {
        String barcode = ScannerFragment.codeContent; //put the the barcode ID here
        // Do some validation here <== idk tf this means it came with the tutorial
        try {
            //create URL to access API
            String API_URL = "https://api.barcodelookup.com/v2/products?";
//            String API_KEY = "mi3j1qnij304njrktnbxr5v4mlc3io"; // yeonjuk@andrew.cmu.edu API key (u get 50 calls on free trial)
            String API_KEY = "rxrrloizrjppkg0mhxke78vr8qii0x"; //yeonjukim98@gmail.com API key
            String URL_STRING = API_URL + "barcode=" + barcode + "&formatted=y&key=" + API_KEY;

            //use static URL for debugging
//            URL_STRING = "https://api.barcodelookup.com/v2/products?barcode=9780140157376&formatted=y&key=mi3j1qnij304njrktnbxr5v4mlc3io";
//            URL_STRING = "https://api.barcodelookup.com/v2/products?barcode=9780140157376&formatted=y&key=rxrrloizrjppkg0mhxke78vr8qii0x";

            URL url = new URL(URL_STRING);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //need these according to :https://stackoverflow.com/questions/40702774/httpurlconnection-getinputstream-stop-working
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setDoInput(true);
            int statusCode = urlConnection.getResponseCode();
            urlConnection.connect();

            //check if the url even works with status code
            InputStream is = null;
            if (statusCode >= 200 && statusCode < 400) {
                // Create an InputStream in order to extract the response object
                is = urlConnection.getInputStream();
            }
            else {
                //status code is error!!
                is = urlConnection.getErrorStream();
            }
            try {
                //get API info and parse into string
                InputStreamReader i = new InputStreamReader(is); //this line didn't work

                BufferedReader br = new BufferedReader(i);
                String str = "";
                String data = "";
//                int count = 0;
//                while (null != (str = br.readLine()) && (count < 10)) {
                  while (null != (str = br.readLine())) {

                        data += str + "\n";
//                    count ++;
                }
//                br.close();
                return data;
            }
            catch(Exception e){
                //if you get an exception from the try above
                return e.toString();
            }
            finally{
                //if everything fails
                urlConnection.disconnect();
            }
            }
        catch(Exception e){
            return "FucK OH NOOo";
        }
    }
//    @Override
    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        Log.i("INFO", response);
        // modified from barcodelookup api docs
        // use GSON to read JSON file
        Gson g = new Gson();
        RootObject value = g.fromJson(response, RootObject.class);

        barcode = value.products[0].barcode_number;
        productName = value.products[0].product_name;
//        System.out.println("Entire Response:");
//        Log.d("response",response);

        //display produt name on scanner tab after scanning
//        TextView text = (TextView)findViewById(R.id.resultText);
//        text.setText("you just scanned:\n"+productName);

    }
}
