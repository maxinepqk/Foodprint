import android.os.AsyncTask;
import android.util.Log;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//public abstract class AsyncTask extends Object // i guess i dont need this line?

class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

    private Exception exception;

    protected void onPreExecute() {
        //do some stuff here
    }

    protected String doInBackground(Void... urls) {
        String barcode = "9780140157376"; //put the the barcode ID here
        // Do some validation here <== idk tf this means it came with the tutorial

        try {
            String API_URL = "https://api.barcodelookup.com/v2/products?";
            String API_KEY = "mi3j1qnij304njrktnbxr5v4mlc3io"; // Yeonju's API key (u get 50 calls on free trial)
            URL url = new URL(API_URL + "barcode=" + barcode + "&formatted=y" + "&key=" + API_KEY);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        Log.i("INFO", response);
        //ListView.(response);
    }
}