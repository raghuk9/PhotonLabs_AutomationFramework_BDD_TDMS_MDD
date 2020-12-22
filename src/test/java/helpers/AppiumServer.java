package helpers;

import java.io.File;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumServer {

	private AppiumDriverLocalService service;
	private AppiumServiceBuilder builder;	
	private String curdir = System.getProperty("user.dir");
	public String serverUrl;
	Map<String, String> env = new HashMap<>(System.getenv());	
	
	public Integer getPort() throws Exception {
		ServerSocket socket = new ServerSocket(0);
		socket.setReuseAddress(true);
		int port = socket.getLocalPort();
		socket.close();
		System.out.println("Available Port is : "+Integer.toString(port));
		return port;
	}
	
	public void startServer(String host) throws Exception{
//		env.put("PATH", "/usr/local/bin:" + env.get("PATH"));
		builder = new AppiumServiceBuilder();
		builder.withEnvironment(env);
		builder.withIPAddress(host);
		int port = getPort();
		builder.usingPort(port);
		builder.withArgument(() -> "--allow-insecure","chromedriver_autodownload");
		builder.withLogFile(new File(curdir+"/Logs/log.txt"));
		builder.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"));
		service = AppiumDriverLocalService.buildService(builder);
		System.out.println("Appium Server is starting.");
		service.start();
		System.out.println("Appium Server Started");
		serverUrl = "http://"+host+":"+port+"/wd/hub";
	}
	
	public void stopServer(){		
		service.stop();
		System.out.println("Appium Server Stopped");
	}
}