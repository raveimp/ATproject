package stepshelpers;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.RequestExecutorException;
import reports.Log;
import configs.Params;

import static org.junit.Assert.assertEquals;

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
        } finally {
            try {
                resp.close();
                execute.destroy();
            } catch (IOException Ex) {
                throw new RequestExecutorException(Ex);
            }
        }
        if (Memory.checkMap() != 0) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                String reqContent = Memory.get();
                JsonNode reqCompare = mapper.readTree(reqContent);
                JsonNode respCompare = mapper.readTree(String.valueOf(respContent));
                assertEquals(reqCompare.asText(),respCompare.asText());
            } catch (JsonProcessingException Ex) {
                throw new RequestExecutorException(Ex);
            }
        } else if (String.valueOf(respContent).startsWith("[")) {
            Memory.put(respContent.substring(respContent.indexOf("[") + 1, respContent.lastIndexOf("]")));
        } else if (respContent.length() <= 4) {
            throw new RequestExecutorException("Bad request.");
        } else if (String.valueOf(respContent).contains("code")) {
            throw new RequestExecutorException("You got response code other then 200.");
        } else {
            Memory.put(String.valueOf(respContent));
        }
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
        } finally {
            try {
                req.flush();
                req.close();
                resp.close();
            } catch (IOException Ex) {
                throw new RequestExecutorException(Ex);
            }
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
            } finally {
                try {
                    req.flush();
                    req.close();
                    resp.close();
                } catch (IOException Ex) {
                    throw new RequestExecutorException(Ex);
                }
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
        } finally {
            try {
                execute.destroy();
                resp.close();
            } catch (IOException Ex) {
                throw new RequestExecutorException(Ex);
            }
        }
        Log.log("DELETE response body: " + respContent);
        if (String.valueOf(respContent).contains("Pet not found")) {
            System.out.println("Pet was deleted from the store.");
        }
    }
}