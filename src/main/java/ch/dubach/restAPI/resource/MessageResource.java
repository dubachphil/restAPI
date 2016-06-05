package ch.dubach.restAPI.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.dubach.restAPI.control.MessageService;
import ch.dubach.restAPI.entity.Message;

@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource extends MessageService {
	
	public MessageResource() {
		// Nur Test zum befüllen
		Message message = new Message();
		message.setName("Phil");
		message.setMessage("Hallo ich bin ein RestService");
		writeMessage(message);
		// -----------------------------
	}
	
	@GET
	@Path("/{id}")
	public Response getMessage(@PathParam("id") Long id) {
		Message message = readMessage(id);
		return Response.ok(message).build();
	}
	
	@GET
	public Response getMessages() {
		Collection<Message> messages = readMessages();
		return Response.ok(messages).build();
	}
	
	@POST
	public Response postMessage(Message message) throws URISyntaxException {
		Message newMessage = writeMessage(message);
		URI uri = new URI("/api/message/" + newMessage.getId());
		return Response.ok(uri).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delMessage(@PathParam("id") Long id) {
		deleteMessage(id);
		return Response.noContent().build();
	}
	
	@PUT
	@Path("/{id}")
	public Response putMessage(@PathParam("id") Long id, Message message) {
		message.setId(id);
		updateMessage(message);
		return Response.ok(message).build();
		
	}


}
