import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiClient {

    private static final String BASE_URL = "http://localhost:8080/files/";

    public static void main(String[] args) {
        String fileName = "example.txt";  // 요청할 파일 이름
        try {
            // API 요청
            URL url = new URL(BASE_URL + fileName);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // 응답 처리
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP error code: " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            System.out.println("File content from server:");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
