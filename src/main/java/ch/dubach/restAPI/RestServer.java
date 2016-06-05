package ch.dubach.restAPI;

import java.io.IOException;
import java.net.URI;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class RestServer {

	public static void main(String[] args) throws IOException {
		
		ResourceConfig rc = new ResourceConfig().packages("ch.dubach.restAPI.resource");
		JdkHttpServerFactory.createHttpServer(URI.create("http://localhost:"+args[0]+"/api"), rc);
		System.out.println("RestServer is Running mit Port " + args[0]);
		
	}

}
