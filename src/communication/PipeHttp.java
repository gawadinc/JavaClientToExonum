package communication;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PipeHttp {

public void send () throws IOException {
    String url = "http://localhost:8000/api/services/cryptocurrency/v1/wallets/transfer";

    HttpClient client = HttpClientBuilder.create().build();
    HttpPost post = new HttpPost(url);

    StringEntity entity = new StringEntity("{\n" +
            "    \"body\": {\n" +
            "        \"from\": \"03e657ae71e51be60a45b4bd20bcf79ff52f0c037ae6da0540a0e0066132b472\",\n" +
            "        \"to\": \"d1e877472a4585d515b13f52ae7bfded1ccea511816d7772cb17e1ab20830819\",\n" +
            "        \"amount\": \"10\",\n" +
            "        \"seed\": \"12623766328194547469\"\n" +
            "    },\n" +
            "    \"network_id\": 0,\n" +
            "    \"protocol_version\": 0,\n" +
            "    \"service_id\": 1,\n" +
            "    \"message_id\": 2,\n" +
            "    \"signature\": \"2c5e9eee1b526299770b3677ffd0d727f693ee181540e1914f5a84801dfd410967fce4c22eda621701c2b9c676ed62bc48df9c973462a8514ffb32bec202f103\"\n" +
            "}\n");
    entity.setContentEncoding("UTF-8");

    post.setEntity(entity);

    HttpResponse response = null;
    response = client.execute(post);
    System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

    BufferedReader rd = null;
    rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

    StringBuffer result = new StringBuffer();
    String line = "";
    while ((line = rd.readLine()) != null)

    {
        result.append(line);
    }

    System.out.println(result);

}
    public void sendGet () throws IOException {
        String url = "http://localhost:8000/api/services/cryptocurrency/v1/wallets";

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        // add request header
        //request.addHeader("User-Agent", USER_AGENT);
        HttpResponse response = client.execute(request);

        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result);
    }

    public static void main(String[] args) {
        try {
            PipeHttp pipeTcp = new PipeHttp();
            pipeTcp.send();
            pipeTcp.sendGet();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}