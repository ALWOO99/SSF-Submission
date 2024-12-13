package vttp.batch5.ssf.noticeboard.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import vttp.batch5.ssf.noticeboard.models.Constant;
import vttp.batch5.ssf.noticeboard.models.Notice;
import vttp.batch5.ssf.noticeboard.repositories.NoticeRepository;

@Service
public class NoticeService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private NoticeRepository noticeRepository;

    public boolean publishNotice(Notice notice) {
        String apiUrl = Constant.apiURL;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            HttpEntity<Notice> requestEntity = new HttpEntity<>(notice, headers);

            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity,
                    String.class);

            if (response.getStatusCode() == HttpStatus.OK) {

                String noticeId = "someGeneratedId";

                noticeRepository.insertNotices(noticeId, notice);

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // API call failed
    }

    public void saveNotice(String noticeId, vttp.batch5.ssf.noticeboard.models.Notice notice) {

        noticeRepository.insertNotices(noticeId, notice);
    }
}
