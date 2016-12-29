package com.societatis.chat;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class ChatEndpoint {
	private static final Set<Session> peers = Collections
			.synchronizedSet(new HashSet<Session>());

	@OnOpen
	public void onOpen(Session session) {
		peers.add(session);
	}

	@OnClose
	public void onClose(Session session) {
		peers.remove(session);
	}

	@OnMessage
	public void message(String message, Session session) throws IOException,
			EncodeException {
		String msg = "";

		String username = (String) session.getUserProperties().get("username");
		if (username == null) {
			session.getUserProperties().put("username", message);
			session.getBasicRemote().sendText(
					buildJsonData("System", "you are connected as " + message));
			System.out.println("User :" + message);
		} else {
			for (Session peer : peers) {

				String usrName = (String) peer.getUserProperties().get(
						"username");
				
				if (message.substring(0, message.lastIndexOf("#")).equals(usrName)) {

					msg = message.substring(message.lastIndexOf("#") + 1);
					peer.getBasicRemote().sendText(
							buildJsonData(
									msg.substring(0, msg.lastIndexOf(":")),
									msg.substring(msg.lastIndexOf(":") + 1)));
				}
			}
		}
	}

	public String buildJsonData(String username, String message) {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add("message", username + ": " + message).build();
		StringWriter stringWriter = new StringWriter();
		try (JsonWriter jsonWriter = Json.createWriter(stringWriter)) {
			jsonWriter.write(jsonObject);
		}
		return stringWriter.toString();
	}
}
