package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class Main_RestfulApi {
	
	private final String YOUR_CLIENT_ID = "fYdCyoNH2M1WXNkUISBB"; // 작성자의 애플리케이션 클라이언트 아이디값 입력";
	private final String YOUR_CLIENT_SECRET = "1HJmjfoovP"; // 작성자의 애플리케이션 클라이언트 시크릿값 입력";
	
	private String searchWord = "왕좌의게임";
	
	
	public Main_RestfulApi() {
		
        try {
        	String text = URLEncoder.encode(searchWord, "UTF-8");
        	
//    		String apiURL = "https://openapi.naver.com/v1/search/blog.json?query="+ text; // json 결과
    		String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
            
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", YOUR_CLIENT_ID);
            con.setRequestProperty("X-Naver-Client-Secret", YOUR_CLIENT_SECRET);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
	}
	
	public static void main(String[] args) {
		
		new Main_RestfulApi();		
    }
}
