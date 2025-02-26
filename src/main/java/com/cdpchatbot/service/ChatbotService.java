package com.cdpchatbot.service;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatbotService {
    private final Map<String, String> faqDatabase;
    private final LevenshteinDistance levenshtein;
    private final RestTemplate restTemplate;

    public ChatbotService() {
        faqDatabase = new HashMap<>();
        faqDatabase.put("How do I set up a new source in Segment?", "To set up a new source in Segment, go to the Segment dashboard, click 'Add Source', select the source type, and follow the configuration steps.");
        faqDatabase.put("How can I create a user profile in mParticle?", "In mParticle, user profiles are created automatically when an event is associated with a new user identity.");
        faqDatabase.put("How do I build an audience segment in Lytics?", "To build an audience segment in Lytics, navigate to the Audience Builder, define your conditions, and save your segment.");
        faqDatabase.put("How can I integrate my data with Zeotap?", "To integrate your data with Zeotap, use the Zeotap API to upload your data and configure your segments.");

        levenshtein = new LevenshteinDistance();
        restTemplate = new RestTemplate();
    }

    public String getAnswer(String question) {
        String bestMatch = findClosestMatch(question);
        return bestMatch != null ? faqDatabase.get(bestMatch) : fetchFromDocumentation(question);
    }

    private String findClosestMatch(String question) {
        String bestMatch = null;
        int minDistance = Integer.MAX_VALUE;

        for (String key : faqDatabase.keySet()) {
            int distance = levenshtein.apply(question, key);
            if (distance < minDistance && distance <= key.length() / 2) { // Threshold for similarity
                minDistance = distance;
                bestMatch = key;
            }
        }
        return bestMatch;
    }

    private String fetchFromDocumentation(String question) {
        String searchUrl = "https://segment.com/docs/search?q=" + question.replace(" ", "+");

        try {
            Document doc = Jsoup.connect(searchUrl).get();
            Element firstResult = doc.select("a[href]").first(); // Get first relevant link

            if (firstResult != null) {
                return "Check this link for more info: " + firstResult.attr("abs:href");
            }
            return "No relevant documentation found.";
        } catch (IOException e) {
            return "Error fetching from Segment documentation.";
        }
    }
}
