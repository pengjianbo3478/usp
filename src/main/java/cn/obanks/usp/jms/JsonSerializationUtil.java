package cn.obanks.usp.jms;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializationUtil {
	private static final ObjectMapper MAPPER = new ObjectMapper();

	public static <T> String serialize(T value) throws IOException {
		return MAPPER.writeValueAsString(value);
	}

	public static <T> T deserialzie(String value, Class<T> clazz) throws IOException {
		return MAPPER.readValue(value, clazz);
	}
}
