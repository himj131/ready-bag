package mj.readybag.starbucks;

import com.google.gson.Gson;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BagCountService {
    final String BAG_COUNT_URL = "https://www.starbucks.co.kr/store/getStoreStockList.do?stock_data%5B0%5D%5Bsku_no%5D=9400000000975&stock_data%5B0%5D%5Bsku_nm%5D=bag_green&stock_data%5B1%5D%5Bsku_no%5D=9400000000976&stock_data%5B1%5D%5Bsku_nm%5D=bag_pink&gugun_cd=0101";
    final String LOGIN_URL = "https://www.starbucks.co.kr/interface/loginMember.do";

    Map<String, String> loginTryCookie;

    public List<StockInfo> getCount(String id, String pwd) throws IOException {
        Map<String, String> loginPageCookie = goLoginPage();
        loginStarbucks(id, pwd, loginPageCookie);
        String data = getReadyBagCount();
        System.out.println(data);
        Gson gson = new Gson();
        Info storeInfo = gson.fromJson(data, Info.class);
        List<StockInfo> results = new ArrayList<>();
        for(int i = 0; i < storeInfo.stockList.size(); i++) {
            BagStock bagStock = storeInfo.stockList.get(0);
            StockInfo stockInfo = new StockInfo();

            if(bagStock.getBAG_GREEN_COUNT() > 0 &&
                bagStock.getBAG_PINK_COUNT() > 0) {
                for (Store store : storeInfo.storeList) {
                    if(bagStock.getSTORE_CD() == store.getStore_cd()) {
                        stockInfo.setGreenCount(bagStock.getBAG_GREEN_COUNT());
                        stockInfo.setPinkCount(bagStock.getBAG_PINK_COUNT());
                        stockInfo.setStoreAddr(store.getAddress());
                        stockInfo.setStoreName(store.getStore_nm());
                        results.add(stockInfo);
                        break;
                    }
                }
            }
        }

        return results;
    }

    private String getReadyBagCount() throws IOException {
        String countResponse = Jsoup.connect(BAG_COUNT_URL)
                .timeout(3000)
                .method(Connection.Method.GET)
                .ignoreContentType(true)
                .cookies(loginTryCookie)
                .execute().body();
        return countResponse;
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
        loginTryCookie = loginPageResponse.cookies();
        return loginTryCookie;
    }

    private void loginStarbucks(String id, String pwd, Map loginPageCookie) throws IOException {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36";
        Connection.Response response = Jsoup.connect(LOGIN_URL)
                .userAgent(userAgent)
                .timeout(3000)
                .header("Origin", "https://www.starbucks.co.kr/")
                .header("Referer", "https://www.starbucks.co.kr/login/login.do")
                .header("Accept", "application/json, text/html, */*; q=0.7")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
                .data("user_id", id)
                .data("user_pwd", pwd)
                .cookies(loginPageCookie)
                .method(Connection.Method.POST)
                .ignoreContentType(true)
                .execute();
    }
}
