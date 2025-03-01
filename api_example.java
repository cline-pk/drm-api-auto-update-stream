import java.net.*;
import java.io.*;

public class ApiExample {
    public static void main(String[] args) throws Exception {
        String url = "https://drm.xtream-masters.com/api.php";
        String jsonData = "{"
            + "\"Channel_Unique_ID_1\":{"
            + "\"channel_name\":\"Channel 1\","
            + "\"mpd\":\"http://domain.xyz/channel_link_1.mpd\","
            + "\"keys\":\"d9729feb74992cc3482b350163a1a010:a9060abf27cc347eff242813880a1c16\""
            + "}"
            + "}";

        // Disable SSL validation (for testing only!)
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) {}
                public void checkServerTrusted(X509Certificate[] chain, String authType) {}
                public X509Certificate[] getAcceptedIssuers() { return null; }
            }
        };
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer YOUR_AUTH_TOKEN");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Provider-ID", "1");
        conn.setRequestProperty("Import-Type", "ImportParse");
        conn.setRequestProperty("Server-ID", "480");
        conn.setDoOutput(true);

        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonData.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(
            new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response);
        }
    }
}
