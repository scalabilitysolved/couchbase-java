package com.scalabilitysolved.couchbase;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.couchbase.client.CouchbaseClient;

public class CouchbaseConnector {

	static final Logger LOG = LoggerFactory.getLogger(CouchbaseConnector.class);

	private static final String DEFAULT_ENVIRONMENT = "localhost";

	private CouchbaseClient client;

	public CouchbaseConnector() throws IOException, URISyntaxException {
		List<URI> hosts = generateNodeInfo();
		String bucketName = "users";
		String password = "";
		LOG.info("Connecting to bucket: {} on nodes of: {}", bucketName, hosts);
		client = new CouchbaseClient(hosts, bucketName, password);
	}

	private List<URI> generateNodeInfo() throws IOException, URISyntaxException {
		InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("/couchbase.properties");
		if (inStream == null) {
			return Arrays.asList(new URI("http://127.0.0.1:8091/pools"));
		}
		Properties properties = new Properties();
		properties.load(inStream);

		String environment = System.getProperty("environment");

		if (environment == null) {
			environment = DEFAULT_ENVIRONMENT;
		}

		LOG.debug("Environment is: {}", environment);
		String[] split = properties.get(environment).toString().split(",");
		List<URI> hosts = new ArrayList<URI>();
		for (String url : split) {
			LOG.info("Adding couchbase node: {}", url);
			hosts.add(new URI(url));
		}
		return hosts;
	}

	public CouchbaseClient getClient() {
		return this.client;
	}

}
