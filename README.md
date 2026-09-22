# 🎓 SmartExam - Advanced AI-Powered Online Examination & Assessment System

[![Java](https://img.shields.io/badge/Java-1.8%20(Java%208)-orange.svg)](https://www.oracle.com/java/)
[![Servlets](https://img.shields.io/badge/Java%20Servlets-3.1-blue.svg)](https://javaee.github.io/servlet-spec/)
[![Database](https://img.shields.io/badge/MySQL-8.0-informational.svg)](https://www.mysql.com/)
[![Server](https://img.shields.io/badge/Server-GlassFish%204.1.1-brightgreen.svg)](https://javaee.github.io/glassfish/)
[![IDE](https://img.shields.io/badge/IDE-Apache%20NetBeans-blueviolet.svg)](https://netbeans.apache.org/)
[![UI](https://img.shields.io/badge/UI-Dark%20Glassmorphism-purple.svg)](web/css/style.css)
[![Zero-Error](https://img.shields.io/badge/Architecture-Dual--Mode%20Zero--Error-success.svg)](web/js/app.js)

---

## 📌 Project Overview

**SmartExam** is an enterprise-grade, comprehensive online examination and assessment platform built using **Java (Servlets & JDBC)**, **MySQL**, and modern **HTML5 / CSS3 / JavaScript**. 

The system provides a **Dual-Mode Zero-Error Architecture**:
1. **Standalone Offline Mode (`file:///`)**: Allows opening any `.html` page directly in any web browser without needing GlassFish, Tomcat, or MySQL. It runs with complete client-side simulation, curriculum retrieval, AI test synthesis, anti-cheating proctoring, native canvas analytics, and certificate generation.
2. **Enterprise Server Mode (`http://`)**: Fully integrates with **GlassFish Server 4.1.1** and **MySQL 8.0** using secure prepared statements, salted SHA-256 password hashing, and REST-style servlets.

---

## 🚀 Key Features & Capabilities

### 1. 🤖 Built-in AI Question Generator
- **Teacher AI Exam Assistant** ([`web/Test.html`](web/Test.html)):
  - Input any academic topic (e.g., *Trigonometry, Cell Biology, Laws of Motion, Java OOPs, DBMS Normalization*).
  - Select target grade (Class 5 to 12 & College/CS), question count (1 to 15), and difficulty (*Easy, Medium, Hard*).
  - Generates 4 plausible multiple-choice options, defines the correct answer key, and creates pedagogical step-by-step explanations.
  - 1-click **"Publish Exam"** makes the test immediately accessible to students.
- **Student Quick Revision Quiz** ([`web/studenthome.html`](web/studenthome.html)):
  - Allows students to enter any concept keyword and immediately take an interactive 5-question diagnostic quiz.

---

### 2. 📚 27 Curated Pre-Configured Test Papers
Contains authentic, syllabus-aligned questions across **9 academic tiers** (3 complete test papers each):

| Academic Level | Test 1 | Test 2 | Test 3 |
|---|---|---|---|
| **Class 5** | Mathematics (Numbers & Operations) | General Science (Living Things) | English (Nouns, Verbs & Vocabulary) |
| **Class 6** | Mathematics (Integers & Fractions) | General Science (Food & Fiber) | Social Studies (Solar System & History) |
| **Class 7** | Mathematics (Algebra & Geometry) | General Science (Nutrition & Heat) | English (Active/Passive & Prepositions) |
| **Class 8** | Mathematics (Linear Equations) | Science (Force & Microorganisms) | Social Science (Indian Constitution) |
| **Class 9** | Mathematics (Polynomials & Coordinate) | Science (Cell Biology & Motion) | English Grammar (Tenses & Modals) |
| **Class 10** | Mathematics (Trigonometry & Quadratics)| Science (Chemical Reactions & Current)| English (Clauses, Reported Speech) |
| **Class 11** | Physics (Kinematics & Vectors) | Chemistry (Atomic Structure & Bonding)| Computer Science (Python Fundamentals)|
| **Class 12** | Physics (Electrostatics & Optics) | Chemistry (Electrochemistry & Kinetics)| Computer Science (Python OOPs & SQL) |
| **College / CS**| Java Programming (OOPs, Collections, Servlets)| DBMS (SQL, Normalization & ACID) | Web Technologies (HTML5, JS & REST) |

---

### 3. 🛡️ Proctored Anti-Cheating Exam Engine ([`web/givetest.html`](web/givetest.html))
- **Enforced Fullscreen Mode**: Mandates fullscreen mode upon examination commencement.
- **Tab-Switch Strike Monitor**: Detects window blur, tab switching, and minimizing (`Strike 1/3`, `Strike 2/3`). On 3 strikes, the test automatically submits.
- **Lockdown Security**: Disables right-click context menu, developer inspect shortcuts (`F12`, `Ctrl+Shift+I`, `Ctrl+Shift+J`, `Ctrl+U`), and text copy-paste.
- **Interactive Question Palette**: Real-time status indicators for *Answered* (Green), *Marked for Review* (Purple), and *Unvisited* (Gray).
- **Live Countdown Timer**: Visual warnings when under 5 minutes; auto-submits on time expiration.
- **Instant Solution Review**: Detailed question-by-question breakdown showing user choice, correct answer, and explanation.

---

### 4. 📊 100% Offline Native Canvas Analytics ([`web/results.html`](web/results.html))
- **Zero External CDN Dependencies**: Eliminates reliance on external libraries like Chart.js.
- **Native 2D Canvas Visualizations**:
  - **Pass / Fail Doughnut Chart**: Interactive emerald (Pass) and rose (Fail) segments with center percentage.
  - **Score Tier Bar Chart**: Categorizes scores into `<40%`, `40–59%`, `60–79%`, and `80–100%`.
  - Automatically re-renders on window resize.
- **Master CSV Export**: 1-click download of all class examination attempts.

---

### 5. 📜 Verifiable Achievement Certificate ([`web/result.html`](web/result.html))
- Automatically unlocked for students achieving passing marks (40%+).
- Includes student name, examination title, score, percentage, date, and Controller of Examinations signature.
- **Canvas Dynamic QR Code**: Generates a verifiable QR pattern natively on HTML5 Canvas.
- **Print & PDF**: Ready for 1-click printing or PDF export.

---

### 6. 🔒 Enterprise Security & Database
- **Salted SHA-256 Hashing** ([`PasswordUtil.java`](src/java/PasswordUtil.java)): Passwords are cryptographically salted and hashed.
- **SQL Injection Prevention**: All servlets utilize parameterized `PreparedStatement`.
- **Relational Schema** ([`database.sql`](database.sql)): Tables for `admins`, `students`, `exams`, `questions`, `exam_attempts`, `assignments`, `submissions`, and `announcements`.

---

## 🛠️ Technology Stack

| Layer | Technology |
|---|---|
| **Backend Language** | Java 8 (JDK 1.8.0_301) |
| **Server Technology** | Java Servlet 3.1, Java EE |
| **Database** | MySQL 8.0 / MySQL Connector Java 8.0.11 |
| **Application Server**| GlassFish Server 4.1.1 |
| **Development IDE** | NetBeans IDE 8.2 / Apache NetBeans |
| **Frontend Core** | HTML5, CSS3, Modern ES6+ JavaScript |
| **Styling & Design** | Custom Dark Glassmorphism, CSS Grid, Flexbox |
| **Graphics & Charts** | Pure HTML5 2D Canvas API (Zero-CDN) |
| **Icons** | Font Awesome 6.5 |

---

## 📂 Project Structure

```
exam/
├── .gitignore
├── README.md                           <-- Comprehensive Documentation
├── build.xml                           <-- NetBeans Ant Build Script
├── database.sql                        <-- Full MySQL Schema & 27 Test Seed Data
├── src/java/                           <-- Java Source Code
│   ├── Announcement.java               <-- Domain Models
│   ├── Assignment.java
│   ├── Exam.java
│   ├── Question.java
│   ├── Result.java
│   ├── Teacher.java
│   ├── DBConnection.java               <-- Centralized JDBC Connection
│   ├── PasswordUtil.java               <-- SHA-256 Hashing with Salt
│   ├── AdminLoginServlet.java          <-- Servlets
│   ├── AdminRegisterServlet.java
│   ├── StudentLoginServlet.java
│   ├── StudentRegisterServlet.java
│   ├── ExamServlet.java
│   ├── SubmitExamServlet.java
│   ├── ResultServlet.java
│   ├── AssignmentServlet.java
│   ├── AnnouncementServlet.java
│   ├── AIQuestionServlet.java
│   └── UpdateTeacherProfileServlet.java
├── web/                                <-- Web Application Root
│   ├── WEB-INF/
│   │   ├── web.xml                     <-- Servlet Mappings & Configurations
│   │   └── lib/
│   │       └── mysql-connector-java-8.0.11.jar
│   ├── css/
│   │   └── style.css                   <-- SaaS Dark Theme & Design System
│   ├── js/
│   │   └── app.js                      <-- AI Engine, 27 Tests, Canvas Charts, Proctor
│   ├── start.html                      <-- Main Portal Landing Page
│   ├── index.html                      <-- Automatic redirect to start.html
│   ├── student.html                    <-- Student Authentication (Login / Register)
│   ├── studenthome.html                <-- Student Dashboard & Class Switcher
│   ├── givetest.html                   <-- Proctored Online Examination Engine
│   ├── result.html                     <-- Result History & QR Certificate
│   ├── leaderboard.html                <-- Student Ranking & Top-3 Podium
│   ├── studentassignment.html          <-- Assignment Submission Portal
│   ├── studentaccount.html             <-- Student Profile & Avatar Settings
│   ├── admin.html                      <-- Teacher Authentication (Login / Register)
│   ├── teacherhome.html                <-- Teacher Dashboard & Announcements
│   ├── Test.html                       <-- AI Exam Builder & CSV Question Import
│   ├── results.html                    <-- Teacher Visual Analytics & CSV Export
│   ├── teacherassignment.html          <-- Assignment Management & Review
│   ├── teacheraccount.html             <-- Teacher Profile Settings
│   └── help.html                       <-- Instructions, Anti-Cheat Rules & FAQ
└── uploads/                            <-- File storage for assignment attachments
```

---

## ⚡ How to Run the Project

### Option 1: Instant Browser Mode (No Installation Needed)
The project is built to work immediately in any browser without needing servers or databases:
1. Navigate to `web/` and double-click [`start.html`](web/start.html) (or open `file:///c:/Users/Ashutosh/Documents/NetBeansProjects/exam/web/start.html`).
2. **Student Flow**:
   - Click **Student Portal** &rarr; click **"Auto-Fill Demo"** &rarr; click **Login to Dashboard**.
   - Switch between **Class 5 to 12 & College/CS** using the class dropdown.
   - Click **Start Examination** on any test paper to test the proctoring engine, countdown timer, question palette, and instant score certificate.
   - Test the **"AI Quick Practice Quiz"** by typing any topic.
3. **Teacher Flow**:
   - Click **Teacher Portal** &rarr; click **"Use Demo Teacher"** &rarr; click **Login to Teacher Dashboard**.
   - Click **AI Exam Builder** &rarr; click **"Launch AI Question Assistant"** to generate new questions and publish tests.
   - Click **Analytics & CSV** to view the native canvas Pass/Fail doughnut chart and score bar graph.

---

### Option 2: Full Server Mode (NetBeans + GlassFish + MySQL)
1. **Database Setup**:
   - Open MySQL Workbench or MySQL CLI:
     ```sql
     CREATE DATABASE exam;
     USE exam;
     SOURCE c:/Users/Ashutosh/Documents/NetBeansProjects/exam/database.sql;
     ```
   - Verify connection settings in [`src/java/DBConnection.java`](src/java/DBConnection.java):
     ```java
     private static final String URL = "jdbc:mysql://localhost:3306/exam?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
     private static final String USER = "root";
     private static final String PASS = "ashu0811"; // your MySQL password
     ```
2. **Open in NetBeans**:
   - Launch **NetBeans IDE**.
   - Click **File &rarr; Open Project** &rarr; select the `exam` folder.
3. **Server Configuration**:
   - Ensure **GlassFish Server 4.1.1** is registered under the *Services* tab in NetBeans.
   - Right-click project &rarr; **Properties** &rarr; **Run** &rarr; set Server to *GlassFish Server*.
4. **Build & Run**:
   - Press **Shift + F11** (Clean and Build).
   - Press **F6** (Run Project).
   - NetBeans will launch `http://localhost:8080/exam/start.html`.

---

## 🔑 Demo Credentials

| Role | Email | Password |
|---|---|---|
| **Student** | `student@example.com` | `student123` |
| **Teacher / Admin** | `admin@example.com` | `admin123` |

*(Quick 1-click **"Auto-Fill Demo"** buttons are provided on both login pages for immediate testing).*

---

## 👨‍💻 Author & Contributions
- **Lead Developer**: Ashutosh Shehra
- **Project**: Online Examination & AI Assessment System
- **Year**: 2026

## 📄 License
This project is open for educational, academic, and demonstration purposes.
