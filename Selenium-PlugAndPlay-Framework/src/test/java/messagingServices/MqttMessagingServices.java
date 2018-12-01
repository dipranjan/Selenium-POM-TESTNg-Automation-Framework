package messagingServices;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import base.BaseClass;


public class MqttMessagingServices extends BaseClass implements MqttCallback {

    private final static int qos = Integer.parseInt(properties.getProperty("qos"));
    private static String topic = properties.getProperty("topic");
    private static final String serverUri = properties.getProperty("serverUri");;
    private static final String username = properties.getProperty("username");;
    private static final String password = properties.getProperty("password");;
    private static final String clientId = properties.getProperty("clientId");;
    private static MqttClient client;
    
    public static void connect() {
    	MqttConnectOptions conOpt = new MqttConnectOptions();
        conOpt.setCleanSession(true);
        conOpt.setUserName(username);
        conOpt.setPassword(password.toCharArray());
       try {
    	   client = new MqttClient(serverUri, clientId, new MemoryPersistence());
    	   client.connect(conOpt);
	       client.subscribe(topic, qos);
	} catch (MqttException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}      
    	
    }
    
    public static void PublishMessage(String payload) {
    	try {
	    	connect();
	        MqttMessage message = new MqttMessage(payload.getBytes());
	        message.setQos(qos);
	        client.publish(topic, message); // Blocking publish
    	}
    	catch(Exception e) {}
    }
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub		
	}
	public void messageArrived(String topic, MqttMessage message) throws MqttException {
        System.out.println(String.format("[%s] %s", topic, new String(message.getPayload())));
    }
	public void deliveryComplete(IMqttDeliveryToken token) {
		
	}  
}
