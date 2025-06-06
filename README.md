🛒 Electronix-World — 
Your One-Stop Electronics Store
Electronix-World is a full-featured e-commerce web application tailored for electronics shopping.
Built using the Spring Boot ecosystem, it provides robust user and admin functionalities, secure authentication, and a scalable backend system. 
This project demonstrates your backend development skills, hands-on experience with Spring technologies,
and the ability to build a complete web application from scratch.

🔥 Why This Project?
This project is not just about buying gadgets—it's about showcasing:

Real-world backend architecture

Spring Boot + Spring Security implementation

MySQL database design

Dynamic product management with CRUD operations

Role-based access control for admin and users

A clean and responsive frontend to support user interaction

✨ Key Highlights
👥 User Features
✅ Sign Up / Login with secure authentication

🔎 Browse electronic items with filters and categories

🛍 Add items to cart

📦 Place mock orders

🔄 View order history (feature-ready)

🛠 Admin Features
✏️ Add / Edit / Delete products and categories

📊 Manage users

🖼 Upload images with file handling support

🔐 Role-based access control (ADMIN, USER)

🛡 Security
Secure login via Spring Security

Protection against unauthorized access

Password encoding with BCrypt

(Planned) JWT implementation for APIs

📦 Tech Stack
Layer	Technology
Language	Java
Framework	Spring Boot, Spring MVC
ORM	Hibernate, Spring Data JPA
Database	MySQL
Security	Spring Security (JWT planned)
View Engine	Thymeleaf (or JSP)
Frontend Styling	HTML5, CSS3, Bootstrap
Build Tool	Maven
IDE	IntelliJ IDEA / Eclipse

🚀 Getting Started
1️⃣ Clone the Repository
bash
Copy
Edit
git clone https://github.com/GurjarMohit/Electronix-World.git
cd electronix-world
2️⃣ Configure the Database
Create a MySQL database named electronix_db

Open src/main/resources/application.properties and update your DB credentials:

properties
Copy
Edit
spring.datasource.url=jdbc:mysql://localhost:3306/electronix_db
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
3️⃣ Run the Application
bash
Copy
Edit
mvn clean install
mvn spring-boot:run
Visit http://localhost:8080

🧩 Folder Overview
bash
Copy
Edit
electronix-world/
├── controller/        # Web layer controllers
├── entity/            # JPA entities for DB tables
├── repository/        # Spring Data JPA Repositories
├── service/           # Business logic layer
├── static/            # CSS, JS, Images
├── templates/         # Thymeleaf views
├── application.properties
🌟 Future Scope
✅ API Layer with JWT Auth

💳 Integrate Razorpay / PayPal payment gateway

📩 Email OTP for signup and order notifications

📈 Admin dashboard with analytics

📱 Convert into Progressive Web App (PWA)

🙋‍♂️ About Me
I'm Mohit Gurjar, a backend developer from NIT Raipur passionate about building scalable Java applications using Spring Boot.

🔗 LinkedIn

📬 Email: mohitgurjar0909@gmail.com

💼 Backend Developer | Spring Boot | MySQL | Hibernate

💬 Contributing
Want to contribute? Great!

bash
Copy
Edit
# Fork the repo
# Create a new branch
git checkout -b feature-name

# Make your changes
# Commit and push
git commit -m "Added new feature"
git push origin feature-name

# Open a Pull Request 🚀
📃 License
This project is licensed under the MIT License. Feel free to use, modify, and distribute it.






