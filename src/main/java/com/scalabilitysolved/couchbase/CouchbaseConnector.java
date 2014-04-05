package com.scalabilitysolved.couchbase;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import com.couchbase.client.CouchbaseClient;
import com.sun.jersey.spi.resource.Singleton;

@Singleton
public class CouchbaseConnector {


	private CouchbaseClient client;

	public CouchbaseConnector() throws IOException, URISyntaxException {
		List<URI> hosts = Arrays.asList(new URI("http://127.0.0.1:8091/pools"));

		String bucketName = "users";
		String password = "";
		client = new CouchbaseClient(hosts, bucketName, password);
	}

	public CouchbaseClient getClient() {
		return this.client;
	}

}
