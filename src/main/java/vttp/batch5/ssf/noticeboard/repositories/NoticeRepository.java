
package vttp.batch5.ssf.noticeboard.repositories;


import java.io.StringWriter;

import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonWriter;
import redis.clients.jedis.Jedis;
import vttp.batch5.ssf.noticeboard.models.Notice;

@Repository
public class NoticeRepository {
	// TODO: Task 4
	// You can change the signature of this method by adding any number of
	// parameters
	// and return any type
	//
	/*
	 * Write the redis-cli command that you use in this method in the comment.
	 * For example if this method deletes a field from a hash, then write the
	 * following
	 * redis-cli command
	 * hdel myhashmap a_key
	 *
	 *
	 */

	private Jedis jedis;

	public NoticeRepository() {
		this.jedis = new Jedis("localhost", 6379); // Adjust Redis connection details
	}

	
	public void insertNotices(String noticeId, Notice notice) {
		try {
			
			JsonObject noticeJson = Json.createObjectBuilder()
					.add("title", notice.getTitle())
					.add("poster", notice.getPoster())
					.add("postDate", notice.getPostDate().toString()) // LocalDate to String
					.add("categories", String.join(",", notice.getCategories())) // Assuming categories is a list
					.add("text", notice.getText())
					.build();

			
			String jsonString = convertJsonObjectToString(noticeJson);

			
			jedis.set("notice:" + noticeId, jsonString);
			System.out.println("Notice inserted into Redis: " + jsonString);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String convertJsonObjectToString(JsonObject jsonObject) {
		StringWriter stringWriter = new StringWriter();
		try (JsonWriter jsonWriter = Json.createWriter(stringWriter)) {
			jsonWriter.writeObject(jsonObject);
		}
		return stringWriter.toString();
	}

	// Close Jedis connection
	public void closeConnection() {
		if (jedis != null) {
			jedis.close();
		}
	}
}
