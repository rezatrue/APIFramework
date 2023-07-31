package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;
import pojo.Types;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String phone, String address) {
		AddPlace addPlace =  new AddPlace();
		Location loca = new Location();
		loca.setLat(-91.383494);
		loca.setLng(45.427362);
		addPlace.setLocation(loca);
		addPlace.setAccuracy(45);
		addPlace.setName(name);
		addPlace.setPhone_number(phone);
		addPlace.setAddress(address);
		Types types = new Types();
		List<String> myList = new ArrayList<String>();
		myList.add("Lost bookes");
		myList.add("lost pen");
		types.setTypes(myList);
		addPlace.setTypes(types);
		addPlace.setWebsite("www.lostsomething.com");
		addPlace.setLanguage("English_NG");
		return addPlace;
	}
	
	public String deleatePlacePayload(String placeid) {
		return "{\r\n    \"place_id\": \""+placeid+"\"\r\n}";
	}

}
