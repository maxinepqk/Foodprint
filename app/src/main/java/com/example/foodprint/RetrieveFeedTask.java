package com.example.foodprint;

import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLStreamHandler;

//need this to add gson
import com.google.gson.Gson;

class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

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
            String API_KEY = "mi3j1qnij304njrktnbxr5v4mlc3io"; // Yeonju's API key (u get 50 calls on free trial)
            String URL_STRING = API_URL + "barcode=" + barcode + "&formatted=y&key=" + API_KEY;
            URL url = new URL(URL_STRING);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //get API info and parse into string
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String str;
            String data = "";
            while (null != (str= bufferedReader.readLine())) {
                data+=str;
            }
                return data;
            }
        catch(Exception e){
                Log.i("ERROR", e.getMessage());
            return "FUCK";
        }

        /*
        try {
            String API_URL = "https://api.barcodelookup.com/v2/products?";
            String API_KEY = "mi3j1qnij304njrktnbxr5v4mlc3io"; // Yeonju's API key (u get 50 calls on free trial)
            String URL_STRING = API_URL + "barcode=" + barcode + "&formatted=y&key=" + API_KEY;
            URL url = new URL(URL_STRING);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String str;
                String data = "";
                while (null != (str= bufferedReader.readLine())) {
                    data+=str;
                }
                return data;
//                StringBuilder stringBuilder = new StringBuilder();
//                String line;
//                while ((line = bufferedReader.readLine()) != null) {
//                    stringBuilder.append(line).append("\n");
//                }
//                bufferedReader.close();
//                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.i("ERROR", e.getMessage());
            return null;
        }
        */
    }
//    @Override
    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        Log.i("INFO", response);
        // modified from barcodelookup api docs
        // use GSON to read JSON file
//        Gson g = new Gson();
//        RootObject value = g.fromJson(response, RootObject.class);
//
//        String barcode = value.products[0].barcode_number;
//        System.out.print("Barcode Number: ");
//        Log.d("barcode", barcode);
//
//        String name = value.products[0].product_name;
//        System.out.print("Product Name: ");
//        Log.d("name", name);
//
//        System.out.println("Entire Response:");
        Log.d("response",response);
        //end of copied code
        //make it actually do stuff instead
    }
}
