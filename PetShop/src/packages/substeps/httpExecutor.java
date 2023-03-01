package packages.substeps;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import packages.utils.JsonGenerator;
import packages.reports.Log;
public class httpExecutor {
    httpExecutor http = new httpExecutor();
    http.sendGet();
}
private void sendGet(String path) throws Exception {
    protocol = Params.HTTP_PROTOCOL;
    host = Params.HTTP_HOST;
    HttpURLConnection conn = (HttpURLConnection) new URL(protocol, host, path).openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Content-Type","application/json");
    conn.setDoOutput(true);

    DataOutputStream req = new DataOutputSrtream(conn.getOutputStream());
    req.writeBytes(JsonGenerator.GetPets(composeRequest));
    req.flush();
    req.close();

    int responseCode = conn.getResponseCode();

    BufferedReader resp = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String respLine;
    StringBuffer respContent = new StringBuffer();
    while ((respLine = resp.readLine()) != null){
        respContent.append(respLine);
    }

    Log.log(respContent);

    resp.close();
}
