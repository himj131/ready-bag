package mj.readybag.starbucks;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class BagCountService {
    final String BAG_COUNT_URL = "https://www.starbucks.co.kr/store/getStoreStockList.do?stock_data%5B0%5D%5Bsku_no%5D=9400000000975&stock_data%5B0%5D%5Bsku_nm%5D=bag_green&stock_data%5B1%5D%5Bsku_no%5D=9400000000976&stock_data%5B1%5D%5Bsku_nm%5D=bag_pink&gugun_cd=0101";
    final String LOGIN_URL = "https://www.starbucks.co.kr/interface/loginMember.do";
    public ApiResponse<StoreInfo> getCount(String id, String pwd) throws IOException {

        Map<String, String> loginPageCookie = goLoginPage();
        loginStarbucks(id, pwd, loginPageCookie);


        return null;
    }

    private Map<String, String> goLoginPage() throws IOException {
        final String LOGIN_PAGE_URL = "https://www.starbucks.co.kr/login/login.do";
        Connection.Response loginPageResponse = Jsoup.connect(LOGIN_PAGE_URL)
                .timeout(3000)
                .header("Origin", "https://www.starbucks.co.kr/")
                .header("Referer", "https://www.starbucks.co.kr/login/login.do")
                .header("Accept", "application/json, text/javascript, */*; q=0.01")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
                .method(Connection.Method.GET)
                .execute();

        Map<String, String> loginTryCookie = loginPageResponse.cookies();
        return loginTryCookie;
    }

    private void loginStarbucks(String id, String pwd, Map loginPageCookie) throws IOException {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36";
//        Map<String, String> loginParam = new HashMap<>();
//        loginParam.put("user_id", id);
//        loginParam.put("user_pwd", pwd);

        Connection.Response response = Jsoup.connect(LOGIN_URL)
                .userAgent(userAgent)
                .timeout(3000)
                .header("Origin", "https://www.starbucks.co.kr/")
                .header("Referer", "https://www.starbucks.co.kr/login/login.do")
                .header("Accept", "text/*, application/xhtml+xml, application/xml, */*; q=0.7")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
                .data("user_id", id)
                .data("user_pwd", pwd)
                .cookies(loginPageCookie)
                .method(Connection.Method.POST)
                .execute();

//        Document page = Jsoup.connect();

        System.out.println(response);
    }
}