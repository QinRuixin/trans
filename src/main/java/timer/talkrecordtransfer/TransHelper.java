package timer.talkrecordtransfer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author qin
 * @date 2020-05-27
 */
public class TransHelper {

    private final static Logger logger = LoggerFactory.getLogger(TransHelper.class);

    public static List<Talk> trans(){
        List<Talk> talks = new LinkedList<>();
        File file = new File("C:\\Users\\10444\\Desktop\\＋＋日谈.txt");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String tempTimeAndTeller="2020-05-22 22:00:00";
//            String tempStory = "";
            String tempStr;
            while ((tempStr = bufferedReader.readLine()) != null) {
                if (tempStr.length() < 4) {
                    continue;
                }
                if (tempStr.substring(0, 4).equals("2020")) {
                    tempTimeAndTeller = tempStr;
                    continue;
                }
                if(tempStr.length()<50){
                    continue;
                }
                Talk talk = new Talk();
                String tempTime = tempTimeAndTeller.substring(0,19);
                String tempTeller = tempTimeAndTeller.substring(20);

                if(tempTeller.equals("(80000000)")){
                    talk.setTeller("匿名");
//                    System.out.println("匿名");
                }else {
                    int endIdx = tempTeller.indexOf('(');
                    talk.setTeller(tempTeller.substring(0,endIdx));
//                    System.out.println(tempTeller.substring(0,endIdx));
                }
                talk.setTime(tempTime);
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
