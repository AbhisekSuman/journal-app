package personal.abhisek.journalApp.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import personal.abhisek.journalApp.entity.JournalEntry;
import personal.abhisek.journalApp.entity.User;
import personal.abhisek.journalApp.enums.Sentiment;
import personal.abhisek.journalApp.service.EmailService;
import personal.abhisek.journalApp.service.SentimentAnalysisService;
import personal.abhisek.journalApp.service.UserRepositoryImpl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

//    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void getUserAndSendMail() {
        List<User> users = userRepository.getUsersForSA();

        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();

            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).toList();

            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();

            for (Sentiment sentiment: sentiments){
                if (sentiment != null) {
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0)+ 1);
                }
            }

            Sentiment mostFrequentSentiments = null;
            int max = 0;

            for (Map.Entry<Sentiment, Integer> entry: sentimentCounts.entrySet()) {
                if (entry.getValue() > max) {
                    mostFrequentSentiments = entry.getKey();
                    max = entry.getValue();
                }
            }

            if (mostFrequentSentiments != null) {
            emailService.sendMail(user.getEmail(), "Sentiment for last 7 days", mostFrequentSentiments.toString());
            }
        }
    }
}
