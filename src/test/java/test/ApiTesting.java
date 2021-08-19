package test;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiTesting {
	
	String url = "https://restful-booker.herokuapp.com";
	
	@Test(priority = 1)
	//request with firstname & Lastname as a parameter filter
	public void getBookingIdsNameFilter() {
		
		RestAssured.baseURI = url;
		RequestSpecification request = RestAssured.given();
		
		
		Response response = request.queryParam("firstname","sally")
                					.queryParam("lastname", "brown")
                					.get("/booking");
		System.out.println("Response data Name Filter "+response.getBody().asString());
		System.out.println("Response Name Filter status code "+response.getStatusCode());
		System.out.println("Response Name Filter Time "+response.getTime());
		System.out.println("Response Name Filter Header content-type"+ response.getHeader("content-type"));
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	

	@Test(priority = 2)
	//request for getting all the booking ids
	public void getBookingIds() {
		
		RestAssured.baseURI = url;
		RequestSpecification request = RestAssured.given();
		Response response = request.get("/booking");
		System.out.println("Response data "+response.getBody().asString());
		System.out.println("Response status code "+response.getStatusCode());
		System.out.println("Response Time "+response.getTime());
		System.out.println("Header content-type"+ response.getHeader("content-type"));
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	//request for getting booking ids with date filter
	public void getBookingIdsDateFilter() {
		
		RestAssured.baseURI = url;
		RequestSpecification request = RestAssured.given();
		Response response = request.queryParam("checkin","2014-03-13")
									.queryParam("checkout", "2014-05-21")
									.get("/booking");
		System.out.println("Response DateFilter data "+response.getBody().asString());
		System.out.println("Response DateFilter status code "+response.getStatusCode());
		System.out.println("Response DateFilter Time "+response.getTime());
		System.out.println("Response DateFilter Header content-type"+ response.getHeader("content-type"));
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 4)
	//request to get booking details for particular id
	public void getBookings() {
		
		RestAssured.baseURI = url;
		RequestSpecification request = RestAssured.given();
		Response response = request.pathParam("id", "2")
									.get("/booking/{id}");
	
		System.out.println("Get Booking Response "+response.getBody().asString());
		System.out.println("Get Booking Response status code "+response.getStatusCode());
		System.out.println("Get Booking Response Time "+response.getTime());
		System.out.println("Get Booking Response Header content-type"+ response.getHeader("content-type"));
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 5)
	// create the data for booking
	public void createBooking() {
		
		RestAssured.baseURI = url;
		RequestSpecification request = RestAssured.given();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstname", "hina");
		map.put("lastname", "Murdhani");
		map.put("totalprice", 130);
		map.put("depositpaid", true);
		Map<String,String> bookingDatesMap = new HashMap<String, String>();
		bookingDatesMap.put("checkin", "2021-07-01");
		bookingDatesMap.put("checkout", "2021-07-01");
		map.put("bookingdates", bookingDatesMap);
		map.put("additionalneeds", "Breakfast");
		
		JSONObject jsonobj = new JSONObject(map);
		System.out.println(jsonobj.toJSONString());
		Response response = request.header("Content-Type", "application/json").body(jsonobj.toJSONString())
		.post("/booking");
		System.out.println("Get Booking Response "+response.getBody().asString());
		System.out.println("Get Booking Response status code "+response.getStatusCode());
		System.out.println("Get Booking Response Time "+response.getTime());
		System.out.println("Get Booking Response Header content-type"+ response.getHeader("content-type"));
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	

	@Test(priority = 6)
	//request for updating booking data for particular id
	public void updateBooking() {
		
		RestAssured.baseURI = url;
		RequestSpecification request = RestAssured.given();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstname", "hina");
		map.put("lastname", "Murdhani");
		map.put("totalprice", 150);
		map.put("depositpaid", true);
		Map<String,String> bookingDatesMap = new HashMap<String, String>();
		bookingDatesMap.put("checkin", "2021-07-01");
		bookingDatesMap.put("checkout", "2021-07-01");
		map.put("bookingdates", bookingDatesMap);
		map.put("additionalneeds", "Breakfast");
		
		JSONObject jsonobj = new JSONObject(map);
		System.out.println(jsonobj.toJSONString());
		Response response = request.header("Content-Type", "application/json").body(jsonobj.toJSONString())
		.put("/booking/11");
		System.out.println("Update Booking Response "+response.getBody().asString());
		System.out.println("Update Booking Response status code "+response.getStatusCode());
		System.out.println("Update Booking Response Time "+response.getTime());
		System.out.println("Update Booking Response Header content-type"+ response.getHeader("content-type"));
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 7)
	//request for partially updating booking data
	public void partialUpdateBooking() {
		
		RestAssured.baseURI = url;
		RequestSpecification request = RestAssured.given();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstname", "hina");
		map.put("lastname", "Murdhani");
		
		JSONObject jsonobj = new JSONObject(map);
		System.out.println(jsonobj.toJSONString());
		Response response = request.header("Content-Type", "application/json").body(jsonobj.toJSONString())
		.put("/booking/1");
		System.out.println("Update Booking Response "+response.getBody().asString());
		System.out.println("Update Booking Response status code "+response.getStatusCode());
		System.out.println("Update Booking Response Time "+response.getTime());
		System.out.println("Update Booking Response Header content-type"+ response.getHeader("content-type"));
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 8)
	//request for deleting booking data
	public void deleteBooking() {
		
		RestAssured.baseURI = url;
		RequestSpecification request = RestAssured.given();
		Response response = request.pathParam("id", 1).header("Content-Type", "application/json")
		.delete("/booking/{id}");
		System.out.println("Delete Booking Response "+response.getBody().asString());
		System.out.println("Delete Booking Response status code "+response.getStatusCode());
		System.out.println("Delete Booking Response Time "+response.getTime());
		System.out.println("Delete Booking Response Header content-type"+ response.getHeader("content-type"));
		Assert.assertEquals(response.getStatusCode(), 200);
	}


}
