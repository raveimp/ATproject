package stepshelpers;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import exceptions.RequestExecutorException;
import reports.Log;
import configs.Params;

public class RequestExecutor {

    public static void sendGet(String curlRequest) {
        Process execute;
        BufferedReader resp;
        String command = "curl -X " + "GET " + "-H " + "accept=application/json " + curlRequest;
        Log.log("GET request: " + command);
        try {
            execute = Runtime.getRuntime().exec(command);
            resp = new BufferedReader(new InputStreamReader(execute.getInputStream()));
        } catch (IOException Ex) {
            throw new RequestExecutorException(Ex);
        }
        String respLine;
        StringBuilder respContent = new StringBuilder();
        try {
            respLine = resp.readLine();
            respContent.append(respLine);
        } catch (IOException Ex) {
            throw new RequestExecutorException(Ex);
        }
        try {
            resp.close();
            execute.destroy();
        } catch (IOException Ex) {
            throw new RequestExecutorException(Ex);
        }
        Memory.put("response", String.valueOf(respContent));
        Log.log("GET response body:" + "\n" + respContent);
    }

    public static void sendPost(String path, String jsonRequest) {
        String protocol = Params.HTTP_PROTOCOL;
        String host = Params.HTTP_HOST;
        HttpURLConnection conn;
        int responseCode;
        try {
            conn = (HttpURLConnection) new URL(protocol, host, path).openConnection();
            conn.setRequestMethod("POST");
        } catch (IOException Ex) {
            throw new RequestExecutorException(Ex);
        }
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        DataOutputStream req;
        try {
            req = new DataOutputStream(conn.getOutputStream());
            req.writeBytes(jsonRequest);
            responseCode = conn.getResponseCode();
        } catch (IOException Ex) {
            throw new RequestExecutorException(Ex);
        }
        String respLine;
        BufferedReader resp;
        try {
            resp = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } catch (IOException Ex) {
            throw new RequestExecutorException(Ex);
        }
        StringBuilder respContent = new StringBuilder();
        try {
            respLine = resp.readLine();
            respContent.append(respLine);
        } catch (IOException Ex) {
                throw new RequestExecutorException(Ex);
        }
        try {
            req.flush();
            req.close();
            resp.close();
        } catch (IOException Ex) {
            throw new RequestExecutorException(Ex);
        }
        Log.log("POST response code: " + responseCode + "\n" + "POST response body:" + "\n" + respContent);
    }

    public static void sendPut(String path, String jsonRequest) {
        String protocol = Params.HTTP_PROTOCOL;
        String host = Params.HTTP_HOST;
        HttpURLConnection conn;
        int responseCode;
        try {
            conn = (HttpURLConnection) new URL(protocol, host, path).openConnection();
            conn.setRequestMethod("PUT");
        } catch (IOException Ex) {
            throw new RequestExecutorException(Ex);
        }
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        DataOutputStream req;
        try {
            req = new DataOutputStream(conn.getOutputStream());
            req.writeBytes(jsonRequest);
            responseCode = conn.getResponseCode();
        } catch (IOException Ex) {
            throw new RequestExecutorException(Ex);
        }
        String respLine;
        BufferedReader resp;
        try {
            resp = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } catch (IOException Ex) {
            throw new RequestExecutorException(Ex);
        }
        StringBuilder respContent = new StringBuilder();
        try {
            respLine = resp.readLine();
            respContent.append(respLine);
            } catch (IOException Ex) {
                throw new RequestExecutorException(Ex);
            }
            try {
                req.flush();
                req.close();
                resp.close();
            } catch (IOException Ex) {
                throw new RequestExecutorException(Ex);
            }
        Log.log("PUT response code: " + responseCode + "\n" + "PUT response body:" + "\n" + respContent);
    }

    public static void sendDelete(String curlRequest) {
        Process execute;
        BufferedReader resp;
        String command = "curl -X " + "DELETE "  + "-H " + "accept=application/json " + curlRequest;
        Log.log("DELETE request: " + command);
        try {
            execute = Runtime.getRuntime().exec(command);
            resp = new BufferedReader(new InputStreamReader(execute.getInputStream()));
        } catch (IOException Ex) {
            throw new RequestExecutorException(Ex);
        }
        String respLine;
        StringBuilder respContent = new StringBuilder();
        try {
            respLine = resp.readLine();
            respContent.append(respLine);
        } catch (IOException Ex) {
            throw new RequestExecutorException(Ex);
        }
        try {
            execute.destroy();
            resp.close();
        } catch (IOException Ex) {
            throw new RequestExecutorException(Ex);
        }
        Log.log("DELETE response body: " + respContent);
    }
}