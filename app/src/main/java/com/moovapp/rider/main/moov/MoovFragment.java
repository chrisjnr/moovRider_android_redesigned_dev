package com.moovapp.rider.main.moov;

import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.moovapp.rider.R;
import com.moovapp.rider.utils.LMTFragmentHelper;
import com.moovapp.rider.utils.placesAutocomplete.CustomAutoCompleteTextView;
import com.moovapp.rider.utils.placesAutocomplete.PlaceJSONParser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by Lijo Mathew Theckanal on 18-Jul-18.
 */

public class MoovFragment extends LMTFragmentHelper {

    private static final String GOOGLE_PLACES_KEY = "key=AIzaSyCUhREpmSKJMyF0ZS6EjP2FC1uhwf8dsek";

    @BindView(R.id.autoCompleteDestination)
    CustomAutoCompleteTextView autoCompleteDestination;
    @BindView(R.id.autoCompleteLocation)
    CustomAutoCompleteTextView autoCompleteLocation;
    @BindView(R.id.cardViewRideDetails)
    CardView cardViewRideDetails;
    @BindView(R.id.cardViewMove)
    CardView cardViewMove;
    @BindView(R.id.cardViewNext)
    CardView cardViewNext;
    @BindView(R.id.cbPool)
    CheckBox cbPool;
    @BindView(R.id.layoutCurrentRider)
    View layoutCurrentRider;

    private PlacesTask placesTask;
    private ParserTask parserTask;
    private Geocoder mGeocoder;

    private boolean isDropDownSelected = false;
    private boolean isDropDownSelectedLocation = false;
    private boolean isTypingOnDestination = true;
    private int currentStep=1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.moov_fragment, container, false);
        ButterKnife.bind(this, view);
        inItAutoCompleteLocation();
        setAutoCompleteTextViewListners();
        return view;
    }

    @OnClick(R.id.cardViewNext)
    public void cardViewNextClick(){
        currentStep=2;
        cardViewNext.setVisibility(View.GONE);
        cbPool.setVisibility(View.GONE);
        cardViewRideDetails.setVisibility(View.VISIBLE);
        cardViewMove.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.cardViewMove)
    public void cardViewMoveClick(){
        currentStep=3;
        cardViewMove.setVisibility(View.GONE);
        cardViewRideDetails.setVisibility(View.GONE);
        layoutCurrentRider.setVisibility(View.VISIBLE);
    }

    public void inItAutoCompleteLocation() {
        autoCompleteDestination.setThreshold(1);
        autoCompleteDestination.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isTypingOnDestination = true;
                placesTask = new PlacesTask();
                placesTask.execute(s.toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        autoCompleteLocation.setThreshold(1);
        autoCompleteLocation.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isTypingOnDestination = false;
                placesTask = new PlacesTask();
                placesTask.execute(s.toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void setAutoCompleteTextViewListners() {
        autoCompleteDestination.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                try {
//                    autoCompleteDestination.setText(autoCompleteDestination.getText().toString().substring(0, edCity.getText().toString().indexOf(",")));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    edCity.setText(edCity.getText().toString());
//                }
                autoCompleteDestination.setSelection(autoCompleteDestination.getText().toString().length());
                isDropDownSelected = true;
            }
        });
        autoCompleteDestination.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() < 1) {
                    isDropDownSelected = false;
                }
            }
        });

        autoCompleteLocation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                try {
//                    autoCompleteDestination.setText(autoCompleteDestination.getText().toString().substring(0, edCity.getText().toString().indexOf(",")));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    edCity.setText(edCity.getText().toString());
//                }
                autoCompleteLocation.setSelection(autoCompleteLocation.getText().toString().length());
                isDropDownSelectedLocation = true;
            }
        });
        autoCompleteLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() < 1) {
                    isDropDownSelectedLocation = false;
                }
            }
        });
    }

    /**
     * Fetches all places from GooglePlaces AutoComplete Web Service
     */
    public class PlacesTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... place) {
            String data = "";
            // Obtain browser key from https://code.google.com/apis/console
            String input = "";
            try {
                input = "input=" + URLEncoder.encode(place[0], "utf-8");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            // place type to be searched
//            String types = "types=(regions)";
            String types = "";
//            String components = "components=country:us";
            String components = "";
            // Sensor enabled
            String sensor = "sensor=false";
            // Building the parameters to the web service
            String parameters = input + "&" + types + "&" + components + "&" + sensor + "&" + GOOGLE_PLACES_KEY;
//            String parameters = input + "&" + types + "&" + sensor + "&" + GOOGLE_PLACES_KEY;
            // Output format
            String output = "json";
            // Building the url to the web service
            String url = "https://maps.googleapis.com/maps/api/place/autocomplete/" + output + "?" + parameters;
            try {
                data = downloadUrl(url);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // Creating ParserTask
            parserTask = new ParserTask();
            // Starting Parsing the JSON string returned by Web Service
            parserTask.execute(result);
        }
    }

    /**
     * A class to parse the Google Places in JSON format
     */
    public class ParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> {

        JSONObject jObject;

        @Override
        protected List<HashMap<String, String>> doInBackground(String... jsonData) {

            List<HashMap<String, String>> places = null;
            PlaceJSONParser placeJsonParser = new PlaceJSONParser();
            try {
                jObject = new JSONObject(jsonData[0]);
                System.out.println("json places:" + jObject);
                places = placeJsonParser.parse(jObject);
                System.out.println("placessss:" + places);
            } catch (Exception e) {
                Log.d("Exception", e.toString());
            }
            return places;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> result) {
            SimpleAdapter adapter = null;
            try {
                String[] from = new String[]{"description"};
                int[] to = new int[]{android.R.id.text1};
                adapter = new SimpleAdapter(getContext(), result, R.layout.simple_spinner_dropdown_item, from, to);
                if (isTypingOnDestination) {
                    autoCompleteDestination.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    System.out.println("atvvv" + autoCompleteDestination.getText().toString());
                } else {
                    autoCompleteLocation.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    System.out.println("atvvv" + autoCompleteLocation.getText().toString());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
//                edCityDialog.setAdapter(adapter);
                adapter.notifyDataSetChanged();
//                System.out.println("atvvv" + edCityDialog.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            iStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
            br.close();
        } catch (Exception e) {
            Log.d("Exception while", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    /**
     * Get Location lat & long
     *
     * @param location is the location name
     */
//    public void getLatLng(String location, String firstAddress) {
//        if (Geocoder.isPresent()) {
//            try {
//                Geocoder gc = new Geocoder(this);
//                List<Address> addresses = gc.getFromLocationName(firstAddress + location, 5); // get the found Address Objects
//                List<LatLng> ll = new ArrayList<LatLng>(addresses.size()); // A list to save the coordinates if they are available
//                for (Address a : addresses) {
//                    if (a.hasLatitude() && a.hasLongitude()) {
//                        ll.add(new LatLng(a.getLatitude(), a.getLongitude()));
//                        System.out.println("HAHAHA  loc lat: " + a.getLatitude() + " " + a.getLongitude());
//                        latitudeStr = a.getLatitude() + "";
//                        longitudeStr = a.getLongitude() + "";
////                        latitudeStrImmediate = a.getLatitude() + "";
////                        longitudeStrImmediate = a.getLongitude() + "";
//                        try {
//                            getCityNameByCoordinates(a.getLatitude(), a.getLongitude());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            getLatLng(location, "");
//                        }
//                    }
//                }
//                if (addresses.size() < 1) {
//                    getLatLng(location, "");
//                }
//            } catch (Exception e) {
//                getLatLng(location, "");
//            }
//        }
//    }

    /**
     * Get Location details
     */
//    private String getCityNameByCoordinates(double lat, double lon) throws IOException {
//        mGeocoder = new Geocoder(this, Locale.getDefault());
//        List<Address> addresses = mGeocoder.getFromLocation(lat, lon, 1);
//        if (addresses != null && addresses.size() > 0) {
//            System.out.println("HAHAHA place details: country " + addresses.get(0).getCountryName() + " PostalCode " + addresses.get(0).getPostalCode() + " getLocality " + addresses.get(0).getLocality() + " getLocale " + addresses.get(0).getLocale() + " getSubLocality " + addresses.get(0).getAdminArea());
//            edCity.setText(addresses.get(0).getLocality());
//            edCity.setSelection(edCity.getText().toString().length());
//            edState.setText(addresses.get(0).getAdminArea());
//            edState.setSelection(edState.getText().toString().length());
//            edZipCode.setText(addresses.get(0).getPostalCode());
//            edZipCode.setSelection(edZipCode.getText().toString().length());
//            edCity.setError(null);
//            edState.setError(null);
//            edZipCode.setError(null);
//            try {
////                edCityDialog.setText(addresses.get(0).getLocality());
////                edCityDialog.setSelection(edCity.getText().toString().length());
////                edStateDialog.setText(addresses.get(0).getAdminArea());
////                edStateDialog.setSelection(edState.getText().toString().length());
////                edZipCodeDialog.setText(addresses.get(0).getPostalCode());
////                edZipCodeDialog.setSelection(edZipCode.getText().toString().length());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//
//            return addresses.get(0).getLocality();
//        }
//        return null;
//    }

}
