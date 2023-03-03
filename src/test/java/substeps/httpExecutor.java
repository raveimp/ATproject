package substeps;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import utils.JsonGenerator;
import reports.Log;
import configs.Params;

public class httpExecutor {

    public static void sendGet(String path, String jsonRequest) throws Exception {
        String protocol = Params.HTTP_PROTOCOL;
        String host = Params.HTTP_HOST;
        HttpURLConnection conn = (HttpURLConnection) new URL(protocol, host, path).openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        DataOutputStream req = new DataOutputStream(conn.getOutputStream());
        req.writeBytes(jsonRequest);
        req.flush();
        req.close();

        int responseCode = conn.getResponseCode();

        BufferedReader resp = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String respLine;
        StringBuffer respContent = new StringBuffer();
        while ((respLine = resp.readLine()) != null) {
            respContent.append(respLine);
        }

        Log.log("Response code: " + responseCode + "\n" + respContent);

        resp.close();
    }
}