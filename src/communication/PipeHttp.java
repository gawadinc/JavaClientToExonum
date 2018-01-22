package communication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import trasfer.object.CreateWalletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PipeHttp {

    static String from = "03e657ae71e51be60a45b4bd20bcf79ff52f0c037ae6da0540a0e0066132b472";
    static String to = "d1e877472a4585d515b13f52ae7bfded1ccea511816d7772cb17e1ab20830819";
    static long seed = 126237663281945474l;
    ObjectMapper objectMapper = new ObjectMapper();

    static String commadTransfer = "{\n" +
            "    \"body\": {\n" +
            "        \"from\": \"%s\",\n" +
            "        \"to\": \"%s\",\n" +
            "        \"amount\": \"%s\",\n" +
            "        \"seed\": \"%s\"\n" +
            "    },\n" +
            "    \"network_id\": 0,\n" +
            "    \"protocol_version\": 0,\n" +
            "    \"service_id\": 1,\n" +
            "    \"message_id\": 2,\n" +
            "    \"signature\": \"2c5e9eee1b526299770b3677ffd0d727f693ee181540e1914f5a84801dfd410967fce4c22eda621701c2b9c676ed62bc48df9c973462a8514ffb32bec202f103\"\n" +
            "}\n";

    static String commandCreateWallet = "{\n" +
            "    \"body\": {\n" +
            "        \"pub_key\": \"%s\",\n" +
            "        \"name\": \"Johnny Doe\"\n" +
            "    },\n" +
            "    \"network_id\": 0,\n" +
            "    \"protocol_version\": 0,\n" +
            "    \"service_id\": 1,\n" +
            "    \"message_id\": 1,\n" +
            "    \"signature\": \"ad5efdb52e48309df9aa582e67372bb3ae67828c5eaa1a7a5e387597174055d315eaa7879912d0509acf17f06a23b7f13f242017b354f682d85930fa28240402\"\n" +
            "}";

    static String transferEndpoint = "http://localhost:8000/api/services/cryptocurrency/v1/wallets/transfer";
    static String getWalletInfo = "http://localhost:8000/api/services/cryptocurrency/v1/wallets";


private String sendPost(String command, String endpoint) throws IOException {

    HttpClient client = HttpClientBuilder.create().build();
    HttpPost post = new HttpPost(endpoint);

    StringEntity entity = new StringEntity(command);
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
    return result.toString();

}

    private String sendGet (String endpoint) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(endpoint);

        // add request header
        //request.addHeader("User-Agent", USER_AGENT);
        HttpResponse response = client.execute(request);
        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result);
        return result.toString();
    }

    public void createWallet (String publicKey) {
    //03e657ae71e51be60a45b4bd20bcf79ff52f0c037ae6da0540a0e0066132b472
        String command = String.format(PipeHttp.commandCreateWallet, publicKey);
        try {
            CreateWalletResponse hash = objectMapper.readValue(this.sendPost(command, getWalletInfo), CreateWalletResponse.class);
            System.out.println(hash.getTx_hash());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFunds (String from, String to, String amount) {

        try {
            seed = seed + 1000;
            String command = String.format(PipeHttp.commadTransfer, from, to, amount, seed);
            this.sendPost(command, transferEndpoint);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Manual test
    public static void main(String[] args) {
        try {
            PipeHttp pipeTcp = new PipeHttp();
            String command = String.format(PipeHttp.commadTransfer, from, to, "10", seed);
            System.out.println(command);
            pipeTcp.sendPost(command, transferEndpoint);
            pipeTcp.sendGet(getWalletInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}