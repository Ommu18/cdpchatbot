CDP Chatbot
A chatbot that answers "how-to" questions related to Customer Data Platforms (CDPs): Segment, mParticle, Lytics, and Zeotap. It uses predefined FAQs and fetches data from documentation when needed.

Tech Stack Used
Java (Spring Boot) - Backend framework
RestTemplate - To fetch external documentation
Levenshtein Distance - To find closest question match
Postman - For API testing


cdp-chatbot/
│── src/main/java/com/example/cdpchatbot/
│   ├── CdpChatbotApplication.java        # Main application file
│   ├── ChatbotController.java            # Handles chatbot API logic
│── pom.xml                                # Maven dependencies
│── README.md                              # Project documentation


1. Clone the Repository
   git clone https://github.com/yourusername/cdp-chatbot.git
cd cdp-chatbot

2. Build & Run the Project
mvn spring-boot:run

1. Ask a Question
Send a GET request to:
http://localhost:8080/api/chatbot/ask?question=How do I set up a new source in Segment?

2. Sample Response
{
    "answer": "To set up a new source in Segment, go to the Segment dashboard, click 'Add Source', select the source type, and follow the configuration steps."
}


Enhancements & Future Improvements
1.Improve NLP processing for better question handling
2.Add caching for faster responses
3.Expand to more CDP platforms

