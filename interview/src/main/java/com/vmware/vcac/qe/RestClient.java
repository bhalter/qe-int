/**
 * https://hc.apache.org/httpcomponents-client-ga/httpclient/apidocs/
 */
package com.vmware.vcac.qe;

import java.io.Closeable;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



/**
 * Rest Client for performing requests
 * @author willwallace
 *
 */
public class RestClient implements Closeable{
	private Log log = LogFactory.getLog(RestClient.class);
	final CloseableHttpClient client;

	public RestClient() {
		client = HttpClientBuilder.create().disableContentCompression().build();
	}
	
	public void execute(final Quotable quote) throws IOException {

		ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            @Override
            public String handleResponse(
                    final HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
	                    final String responseBody = EntityUtils.toString(entity);
	                    EntityUtils.consume(entity);
	                    return responseBody;
                    } 
                    return null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }
        };
   
		final String responseBody = client.execute(quote.getRequest(), responseHandler);
		log.debug(responseBody);
		quote.process(responseBody);
	}
	
	@Override
	public void close() throws IOException {
		client.close();
	}
}
