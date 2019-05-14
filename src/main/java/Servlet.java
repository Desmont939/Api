import Entity.Resume;
import Excel.ExcelWriter;
import Json.JsonReader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@WebServlet("/Search")
public class Servlet extends HttpServlet {
    private static final String SECRET_KEY = "v3.h.3657257.9c37496a036663c18ae28a254222c4e43e10ad1a.3ba6aa0e62bb63f8353909217ec1bcb65d89dee8";
    private static final String url = "https://api.superjob.ru/2.0/vacancies/?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String getQuery = req.getQueryString();


        JsonReader jsonReader = new JsonReader();
        ArrayList<Resume> resumesList;
        resumesList = jsonReader.getResumes(executeGet(url + getQuery, 2000));
        try {
            Class.forName("org.sqlite.JDBC");
            Datasource datasource = new Datasource();
            datasource.open();
            for (Resume resume : resumesList) {
                datasource.insert(resume.resumeToArray());
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        ExcelWriter excelWriter = new ExcelWriter();
        excelWriter.writeDataToExcel(resumesList);

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println("<h3>Данные загружены в БД</h3>");
        out.close();

    }

    private static String executeGet(String url, int timeout) throws IOException {


        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");


        //добавляем заголовок в запрос
        con.setRequestProperty("X-Api-App-Id", SECRET_KEY);
        con.setUseCaches(false);
        con.setAllowUserInteraction(false);
        con.setConnectTimeout(timeout);
        con.setReadTimeout(timeout);
        con.connect();
        int responseCode = con.getResponseCode();
        System.out.println("Response code - " + responseCode);

        //Если ответ 200 || 201, всё ок забираем строку
        switch (responseCode) {
            case 200:
            case 201:
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                //Посмотреть в консоли саму строку Json
                System.out.println(sb.toString());
                return sb.toString();

        }
        return null;

    }
//Хотел парсить все входные параметры из GET в Map
//    public Map<String, String> queryToMap(String query) {
//        Map<String, String> result = new HashMap<String, String>();
//        for (String param : query.split("&")) {
//            String pair[] = param.split("=");
//            if (pair.length > 1) {
//                result.put(pair[0], pair[1]);
//            } else {
//                result.put(pair[0], "");
//            }
//        }
//        return result;
//    }
}
