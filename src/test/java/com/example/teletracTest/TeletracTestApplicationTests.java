package com.example.teletracTest;

import com.example.teletracTest.Data.Record;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.swing.*;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class TeletracTestApplicationTests {

	String testBody = "{\"recordType\": \"xxx\"," +
						"\"deviceId\": \"357370040159770\"," +
						"\"eventDateTime\": \"2014-05-12T05:09:48Z\"," +
						"\"fieldA\": 68," +
						"\"fieldB\": \"xxx\"," +
						"\"fieldC\": 123.45}";


	@Autowired
	private MockMvc mvc;

	@Autowired
	private RecordRepository repository;

	@Test
	public void expectedUnauthTest() throws Exception {

		mvc.perform(post("/echo").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());
	}

	@Test
	public void ExpectedEchoTest() throws Exception {
		MvcResult authResult = mvc.perform(post("/login?user=something&password=somethingelse"))
				.andExpect(status().isOk())
				.andReturn();

		String authToken = authResult.getResponse().getContentAsString();

		Assert.assertTrue(authToken.contains("Bearer"));

		MvcResult postResult = mvc.perform(post("/echo").header("Authorization", authToken).contentType(MediaType.APPLICATION_JSON)
				.content(testBody))
				.andExpect(status().isOk())
				.andReturn();

		String recordAsString = postResult.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		Record recordReturned = mapper.readValue(recordAsString, Record.class);

		Record recordExpected = mapper.readValue(testBody, Record.class);

		// They will be the same other than internal unique ID
		Assert.assertEquals(recordExpected.getRecordType(), "xxx");
		Assert.assertEquals(recordExpected.getEventDateTime(), "2014-05-12T05:09:48Z");
		Assert.assertEquals(recordExpected.getFieldA(), 68);
		Assert.assertEquals(recordExpected.getFieldB(), "xxx");
	}

	@Test
	public void expectedNoContentTest() throws Exception {

		MvcResult authResult = mvc.perform(post("/login?user=something&password=somethingelse"))
				.andExpect(status().isOk())
				.andReturn();

		String authToken = authResult.getResponse().getContentAsString();

		Assert.assertTrue(authToken.contains("Bearer"));

		mvc.perform(post("/nocontent").header("Authorization", authToken).contentType(MediaType.APPLICATION_JSON)
						.content(testBody))
				.andExpect(status().isNoContent());

	}

	@Test
	public void ExpectedDeviceTest() throws Exception {
		MvcResult authResult = mvc.perform(post("/login?user=something&password=somethingelse"))
				.andExpect(status().isOk())
				.andReturn();

		String authToken = authResult.getResponse().getContentAsString();

		Assert.assertTrue(authToken.contains("Bearer"));

		MvcResult postResult = mvc.perform(post("/device").header("Authorization", authToken).contentType(MediaType.APPLICATION_JSON)
						.content(testBody))
				.andExpect(status().isOk())
				.andReturn();

		String deviceId = postResult.getResponse().getContentAsString();

		Assert.assertEquals("357370040159770", deviceId);
	}


}
