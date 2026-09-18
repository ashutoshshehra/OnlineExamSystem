Online Exam System

📌 About the Project



Online Exam System is a Java-based web application developed using NetBeans. The project provides an online platform for conducting examinations and managing students, teachers, questions, assignments, results and other examination-related activities.



The application contains separate web pages for students, teachers and administrators.



✨ Features

👨‍💼 Admin

Admin Login

Admin Authentication

Admin Session Management

Admin Portal

Teacher Management

Examination Management

👨‍🏫 Teacher

Teacher Account

Teacher Registration

Teacher Login

Teacher Home

Assignment Management

Question Management

Examination Management

👨‍🎓 Student

Student Account

Student Registration

Student Login

Student Home

Online Quiz

Online Examination

Assignment

Result

Leaderboard

📊 Other Features

Online Quiz

Assignment Completion

Result Management

Leaderboard

Help and Support

Responsive Web Interface

🛠️ Technologies Used

Java

Java Servlet

JDBC

MySQL

HTML

CSS

JavaScript

NetBeans

GlassFish Server

🔐 Admin Authentication



The Admin Portal provides an authentication system where an administrator can log in using an email address and password.



The login request is handled by the AdminLoginServlet, which receives the login details and verifies them using the MySQL database.



After successful authentication, an administrator session is created and the user is redirected to the teacher home page.



🗄️ Database



The project uses MySQL for storing application data.



Database:



exam



The Admin Login system uses the admins table for authentication.



JDBC is used to establish the connection between the Java application and the MySQL database.



🌐 Web Pages



The project contains multiple web pages for different parts of the Online Exam System.



Admin / Teacher Pages

admin.html

teacher account.html

teacherassign.html

teacherhome.html

teacherassigment.html

teacherhistory.html

Student Pages

student.html

student account.html

studenthome.html

student assignment.html

Examination Pages

start.html

quiztest.html

assignment completion .html

result.html

results.html

leaderboard.html

Other Pages

index.html

help.html

Test.html

teststing.html

📂 Project Structure

OnlineExamSystem/

│

├── nbproject/

├── src/

├── web/

├── test/

├── uploads/

├── build.xml

├── .gitignore

└── README.md

🔄 Application Flow

&#x20;                   Online Exam System

&#x20;                          |

&#x20;            +-------------+-------------+

&#x20;            |             |             |

&#x20;          Admin         Teacher       Student

&#x20;            |             |             |

&#x20;         Login        Registration     Login

&#x20;            |             |             |

&#x20;       Admin Portal   Teacher Home   Student Home

&#x20;                          |             |

&#x20;                   Manage Exams       Quiz

&#x20;                   Assignments       Assignment

&#x20;                   Questions         Examination

&#x20;                                     Result

&#x20;                                     Leaderboard

⚙️ How to Run the Project

Install JDK, NetBeans, GlassFish Server and MySQL.

Open the project in NetBeans.

Select File → Open Project.

Select the project folder.

Create the required MySQL database named exam.

Configure the required database tables and connection.

Configure GlassFish Server in NetBeans.

Run the project from NetBeans.



The starting page is:



start.html



🔗 Backend



The Java backend uses Servlets and JDBC for processing requests and communicating with the MySQL database.



The Admin Login Servlet is:



AdminLoginServlet



🔒 Security Note



Database credentials should not be stored directly inside the source code.



Before deploying this project publicly, database credentials should be moved to a secure configuration or environment variables.



🎯 Project Purpose



This project was developed for educational purposes to demonstrate the development of a Java web application using Java Servlets, JDBC, MySQL and frontend web technologies.



👨‍💻 Author



Ashutosh



📄 License



This project is created for educational purposes.

