package com.app.GlobalCollateralApp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.app.globalcollateralapp.model.PriceEarningModel;
import com.app.globalcollateralapp.model.RevenueModel;
import com.app.globalcollateralapp.service.OilService;

@SpringBootTest
class GlobalCollateralAppApplicationTests {

	public int port = 8081;

	@Autowired
	OilService oilService;

	// unit test case to test the getRevenue Function of Service Class
	@Test
	public void unitTestGetRevenueYield() {

		System.out.println("*************Start unitTestGetRevenueYield*************");
		float price = (float) 7.0;
		float expRevenueValue = (float) 0.14285715;
		String expectedType = "Standard";
		String expectedOilId = "AAC";

		List<RevenueModel> actualResult = oilService.getRevenueYield(price);
		System.out.println(actualResult.get(0).getRevenue());
		System.out.println(actualResult.get(0).getOilId());
		System.out.println(actualResult.get(0).getType());

		assertEquals(expRevenueValue, actualResult.get(0).getRevenue());
		assertEquals(expectedType, actualResult.get(0).getType());
		assertEquals(expectedOilId, actualResult.get(0).getOilId());

		System.out.println("*************End unitTestGetRevenueYield*************");
	}

	// unit test case to test the getPriceEarningRatio Function of Service Class
	@Test
	public void unitTestGetPriceEarningRatio() {

		System.out.println("*************Start unitTestGetPriceEarningRatio*************");
		float price = (float) 7.0;
		float exppriceRatioValue = (float) 48.999996;
		String expectedType = "Standard";
		String expectedOilId = "AAC";

		List<PriceEarningModel> actualResult = oilService.getPriceEarningRatio(price);

		assertEquals(exppriceRatioValue, actualResult.get(0).getPriceEarningRatio());
		assertEquals(expectedType, actualResult.get(0).getType());
		assertEquals(expectedOilId, actualResult.get(0).getOilId());

		System.out.println("*************End unitTestGetPriceEarningRatio*************");
	}

	// Integration Testing, covers end to end functionality for GetRevenue Rest API
	@Test
	public void testGetRevenue() throws URISyntaxException {
		
		System.out.println("*************start case testGetRevenue*************");
		RestTemplate restTemplate = new RestTemplate();
		String price = "7";
		int expectedCode = 200;
		float revenueVal = (float) 0.14285715;

		final String baseUrl = "http://localhost:" + port + "/api/getReveneue/" + price;

		URI url = new URI(baseUrl);

		ResponseEntity<RevenueModel[]> result = restTemplate.getForEntity(url, RevenueModel[].class);
		List<RevenueModel> resultList = Arrays.asList(result.getBody());
		assertEquals(expectedCode, result.getStatusCodeValue());
		assertEquals(revenueVal, resultList.get(0).getRevenue());
		System.out.println("*************end case testGetRevenue*************");
	}

	// Integration Testing, covers end to end functionality for getPriceEarningRatio Rest API

	@Test
	public void testGetPriceEarningRatio() throws URISyntaxException {

		System.out.println("*************start case testGetPriceEarningRatio*************");
		RestTemplate restTemplate = new RestTemplate();
		String price = "7";
		int expectedCode = 200;
		float priceEarningRatioVal = (float) 48.999996;

		final String baseUrl = "http://localhost:" + port + "/api/getPriceEarningRatio/" + price;

		URI url = new URI(baseUrl);

		ResponseEntity<PriceEarningModel[]> result = restTemplate.getForEntity(url, PriceEarningModel[].class);
		List<PriceEarningModel> resultList = Arrays.asList(result.getBody());

		assertEquals(expectedCode, result.getStatusCodeValue());
		assertEquals(priceEarningRatioVal, resultList.get(0).getPriceEarningRatio());

		System.out.println("*************end case testGetPriceEarningRatio*************");
	}

}
