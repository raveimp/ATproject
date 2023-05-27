package substeps;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import constants.Paths;
import reports.Log;
import configs.Params;
import utils.FileUtil;

public class RequestExecutor {
    public static void sendGet(String curlRequest) throws Exception {
        Process execute = null;
        BufferedReader resp = null;
        try {
            String command = "curl -X " + "GET " + "-H " + "accept=application/json " + curlRequest;
            execute = Runtime.getRuntime().exec(command);

            Log.log("Request: " + command);

            resp = new BufferedReader(new InputStreamReader(execute.getInputStream()));
            String respLine;
            StringBuilder respContent = new StringBuilder();
            while ((respLine = resp.readLine()) != null) {
                 respContent.append(respLine);
            }

            if (respContent.length() <= 2) {
                Log.log("Nothing found");
            } else if (respContent.toString().startsWith("{")) {
                FileUtil.writeFile(Paths.INPUT_PATH + File.separator + "RespContent.txt", String.valueOf(respContent));
                Log.log("GET response body:" + "\n" + respContent);
            } else {
                FileUtil.writeFile(Paths.INPUT_PATH + File.separator + "RespContent.txt", String.valueOf(respContent
                        .substring(respContent.indexOf("[") + 1, respContent.lastIndexOf("]"))));
                Log.log("GET response body:" + "\n" + respContent);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (resp != null) {
                resp.close();
            }
            if (execute != null) {
                execute.destroy();
            }
        }
    }

    public static void sendPost(String path, String jsonRequest) throws Exception {
            String protocol = Params.HTTP_PROTOCOL;
            String host = Params.HTTP_HOST;
            HttpURLConnection conn = (HttpURLConnection) new URL(protocol, host, path).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            DataOutputStream req = new DataOutputStream(conn.getOutputStream());
            req.writeBytes(jsonRequest);
            req.flush();
            req.close();

            int responseCode = conn.getResponseCode();

            BufferedReader resp = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String respLine;
            StringBuilder respContent = new StringBuilder();
            while ((respLine = resp.readLine()) != null) {
                respContent.append(respLine);
            }
            Log.log("Response code: " + responseCode + "\n" + "Response body:" + "\n" + respContent);
    }

    public static void sendPut(String path, String jsonRequest) throws Exception {
        String protocol = Params.HTTP_PROTOCOL;
        String host = Params.HTTP_HOST;
        HttpURLConnection conn = (HttpURLConnection) new URL(protocol, host, path).openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        DataOutputStream req = new DataOutputStream(conn.getOutputStream());
        req.writeBytes(jsonRequest);
        req.flush();
        req.close();

        int responseCode = conn.getResponseCode();

        BufferedReader resp = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String respLine;
        StringBuilder respContent = new StringBuilder();
        while ((respLine = resp.readLine()) != null) {
            respContent.append(respLine);
        }

        Log.log("Response code: " + responseCode + "\n" + "Response body:" + "\n" + respContent);

        resp.close();
    }

    public static void sendDelete(String curlRequest) throws Exception {
        Process execute = null;
        BufferedReader resp = null;
        try {
            String command = "curl -X " + "DELETE "  + "-H " + "accept=application/json " + curlRequest;
            execute = Runtime.getRuntime().exec(command);

            Log.log("Request: " + command);

            resp = new BufferedReader(new InputStreamReader(execute.getInputStream()));
            String respLine;
            StringBuilder respContent = new StringBuilder();
            while ((respLine = resp.readLine()) != null) {
                respContent.append(respLine);
            }

            Log.log("DELETE response: " + respContent);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (resp != null) {
                resp.close();
            }
            if (execute != null) {
                execute.destroy();
            }
        }
    }
}