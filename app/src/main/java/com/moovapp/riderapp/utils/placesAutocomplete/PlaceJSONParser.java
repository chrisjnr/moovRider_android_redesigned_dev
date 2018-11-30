package com.moovapp.riderapp.utils.placesAutocomplete;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PlaceJSONParser {
	
	/** Receives a JSONObject and returns a list */
	public List<HashMap<String,String>> parse(JSONObject jObject){
		
		JSONArray jPlaces = null;
		try {			
			/** Retrieves all the elements in the 'places' array */
			jPlaces = jObject.getJSONArray("predictions");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		/** Invoking getPlaces with the array of json object
		 * where each json object represent a place
		 */		
		return getPlaces(jPlaces);
	}
	
	
	private List<HashMap<String, String>> getPlaces(JSONArray jPlaces){
		int placesCount = jPlaces.length();
		List<HashMap<String, String>> placesList = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> place = null;

		/** Taking each place, parses and adds to list object */
		for(int i=0; i<placesCount;i++){
			try {
				/** Call getPlace with place JSON object to parse the place */
				place = getPlace((JSONObject)jPlaces.get(i));
				placesList.add(place);

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		for(int i=0;i<placesCount;i++){
			for(int j=i+1;j<placesCount;j++){
				String reference = placesList.get(i).get("reference");
				String id = placesList.get(i).get("_id");
				String description = placesList.get(i).get("description");
				if(placesList.get(i).get("description").compareTo(placesList.get(j).get("description"))>0){
					placesList.get(i).putAll(placesList.get(j));
					placesList.get(j).put("reference",reference);
					placesList.get(j).put("_id",id);
					placesList.get(j).put("description",description);
				}
			}
		}
		
		return placesList;
	}
	
	/** Parsing the Place JSON object */
	private HashMap<String, String> getPlace(JSONObject jPlace){

		HashMap<String, String> place = new HashMap<String, String>();
		
		String id="";
		String reference="";
		String description="";
		
		try {
			
			description = jPlace.getString("description");			
			id = jPlace.getString("id");
			reference = jPlace.getString("reference");
			
			place.put("description", description);
			place.put("_id",id);
			place.put("reference",reference);			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		return place;
	}
}
