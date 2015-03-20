package net.viotech;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Main class.
 *
 */
public class Main {
	
	// Base URI the Grizzly HTTP server will listen on
	public static final String BASE_URI = "http://localhost:8080/myapp/";

	/**
	 * Main method.
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		ServletContextHandler context = new ServletContextHandler(
				ServletContextHandler.SESSIONS);

		context.setContextPath("/");
		Server jettyServer = new Server(8080);
		jettyServer.setHandler(context);
		ServletHolder jerseyServlet = context.addServlet(
				org.glassfish.jersey.servlet.ServletContainer.class, "/*");
		jerseyServlet.setInitOrder(0);
		jerseyServlet.setInitParameter(
				"jersey.config.server.provider.classnames",
				MyResource.class.getCanonicalName());

		try {
			jettyServer.start();
			jettyServer.join();
		} finally {
			jettyServer.destroy();
		}
	}

}
