package ch.dubach.restAPI.control;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.NotFoundException;

import ch.dubach.restAPI.entity.Message;

public class MessageService {
	
	private final Map<Long, Message> messages = new ConcurrentHashMap<Long, Message>();
	private final AtomicLong sequenz = new AtomicLong(0);
	
	public Message writeMessage(Message message) {
		Long id = sequenz.incrementAndGet();
		message.setId(id);
		messages.put(id, message);
		return message;	
	}
	
	public Collection<Message> readMessages() {
		return messages.values();
	}
	
	public Message readMessage(Long id) {
		if (!messages.containsKey(id)) {
			throw new NotFoundException("Keinen Datensatz mit dieser ID gefunden");
		}
		return messages.get(id);
	}
	
	public void updateMessage(Message message) {
		Long id = message.getId();
		if (!messages.containsKey(id)) {
			throw new NotFoundException("Keinen Datensatz mit dieser ID gefunden");
		}
		messages.put(id,message);
		
	}
	
	public void deleteMessage(Long id) {
		if (!messages.containsKey(id)) {
			throw new NotFoundException("Keinen Datensatz mit dieser ID gefunden");
		}
		messages.remove(id);
		
	}

}
