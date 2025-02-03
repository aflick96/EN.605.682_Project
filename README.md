This is a personal finance management application developed using **Java Spring Boot**, **JSP**, and **H2 Database**

### Projects
1. **`finance`** (client): Manages user interface, sessions, and interactions with the backend API.
2. **`finance-server`** (server): Handles API requests, business logic, and database interactions. 

### **Server Dependencies**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **H2 Database**
- **Jakarta Persistence API**
- **Maven** for build automation

### **Client Dependencies**
- **Spring MVC (JSP-based)**
- **JSTL (JavaServer Pages Standard Tag Library)**
- **RestTemplate** for API communication
- **Maven** for build automation

### How to run
1. Clone the repository
2. Build & run Server (runs on port 8081)
- cd ~/finance-server
- ```mvn clean install spring-boot:run```
3. Build & run Client (runs on port 8080)
- cd ~/finance
- ```mvn clean install spring-boot:run```
4. navigate to localhost:8080/ in browser