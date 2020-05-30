package timer.talkrecordtransfer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qin
 * @date 2020-05-27
 */
public class TransHelper {

    private final static Logger logger = LoggerFactory.getLogger(TransHelper.class);

    private final static int LINE_TO_SKIP = 2590;

    public static List<Talk> trans() {
        List<Talk> talks = new LinkedList<>();
        File file = new File("C:\\Users\\10444\\Desktop\\＋＋日谈.txt");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String tempTimeAndTeller = "2020-05-22 22:00:00";
//            String tempStory = "";
            String tempStr;
            for (int i = 0; i < LINE_TO_SKIP; i++) {
                bufferedReader.readLine();
            }
            while ((tempStr = bufferedReader.readLine()) != null) {
                if (tempStr.length() < 4) {
                    continue;
                }
                if (tempStr.substring(0, 4).equals("2020")) {
                    tempTimeAndTeller = tempStr;
                    continue;
                }
                if (tempStr.length() < 50) {
                    continue;
                }
                Talk talk = new Talk();

                String regEx = "((20[0-9]{2})-(0?[1-9]|1[0-2])-((0?[1-9])|([12][0-9])|30|31) ([0-9]|[12][0-9]):((0?[1-9])|([1-5][0-9])):((0?[1-9])|([1-5][0-9])))" +
                        " (.*)\\(.*\\)";
//    String regEx = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
                Pattern pattern = Pattern.compile(regEx);
                Matcher matcher = pattern.matcher(tempTimeAndTeller);
//                int splitPoint = matcher.end();
//                String tempTime = tempTimeAndTeller.substring(0,splitPoint);
//                String tempTeller = tempTimeAndTeller.substring(splitPoint+1);
//                System.out.println(tempTimeAndTeller);
                if (matcher.matches()) {
                    String tempTime = matcher.group(1);
                    String tempTeller = matcher.group(14);
//                    System.out.println(tempTime);
//                    System.out.println(tempTeller);
                    if (tempTeller.equals("")) {
                        talk.setTeller("匿名");
//                    System.out.println("匿名");
                    } else {
                        talk.setTeller(tempTeller);
                    }
                    talk.setTime(tempTime);
                }
                talk.setContent(tempStr);
//                System.out.println(tempTime);
//                System.out.println(tempStr);
                talks.add(talk);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String json = JSON.toJSONString(talks);
        System.out.println(json);
//        logger.info(json);
        return talks;
    }
}
