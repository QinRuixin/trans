package timer.talkrecordtransfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TalkRecordTransferApplication {

    public static void main(String[] args) {
        TransHelper.trans();
        SpringApplication.run(TalkRecordTransferApplication.class, args);
    }

}
