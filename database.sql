-- ====================================================================
-- Online Exam System - Database Schema & Seed Data
-- ====================================================================

CREATE DATABASE IF NOT EXISTS exam;
USE exam;

-- 1. Admins / Teachers Table
CREATE TABLE IF NOT EXISTS admins (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    adminId VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20) DEFAULT '',
    avatar LONGTEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Students Table
CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    studentId VARCHAR(50) UNIQUE NOT NULL,
    class_grade VARCHAR(50) DEFAULT 'Class 10',
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20) DEFAULT '',
    avatar LONGTEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. Exams Table
CREATE TABLE IF NOT EXISTS exams (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    class_grade VARCHAR(50) NOT NULL,
    subject VARCHAR(100) NOT NULL,
    duration_minutes INT DEFAULT 30,
    total_marks INT DEFAULT 100,
    passing_marks INT DEFAULT 40,
    negative_marking DECIMAL(3,2) DEFAULT 0.00,
    instructions TEXT,
    created_by VARCHAR(100) DEFAULT 'Admin',
    status VARCHAR(20) DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 4. Questions Table
CREATE TABLE IF NOT EXISTS questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    exam_id INT NOT NULL,
    question_text TEXT NOT NULL,
    option_a VARCHAR(255) NOT NULL,
    option_b VARCHAR(255) NOT NULL,
    option_c VARCHAR(255) NOT NULL,
    option_d VARCHAR(255) NOT NULL,
    correct_option CHAR(1) NOT NULL, -- 'A', 'B', 'C', 'D'
    explanation TEXT,
    marks INT DEFAULT 1,
    negative_marks DECIMAL(3,2) DEFAULT 0.00,
    FOREIGN KEY (exam_id) REFERENCES exams(id) ON DELETE CASCADE
);

-- 5. Exam Attempts / Results Table
CREATE TABLE IF NOT EXISTS exam_attempts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    exam_id INT NOT NULL,
    student_id VARCHAR(50) NOT NULL,
    student_name VARCHAR(100) NOT NULL,
    score INT NOT NULL,
    total_marks INT NOT NULL,
    percentage DECIMAL(5,2) NOT NULL,
    status VARCHAR(20) NOT NULL, -- 'PASS', 'FAIL'
    duration_taken INT DEFAULT 0,
    tab_switch_count INT DEFAULT 0,
    answers_json LONGTEXT,
    attempted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (exam_id) REFERENCES exams(id) ON DELETE CASCADE
);

-- 6. Assignments Table
CREATE TABLE IF NOT EXISTS assignments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    class_grade VARCHAR(50) NOT NULL,
    subject VARCHAR(100) NOT NULL,
    due_date DATE NOT NULL,
    description TEXT,
    created_by VARCHAR(100) DEFAULT 'Admin',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 7. Submissions Table
CREATE TABLE IF NOT EXISTS submissions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    assignment_id INT NOT NULL,
    student_name VARCHAR(100) NOT NULL,
    student_id VARCHAR(50) DEFAULT 'S-101',
    file_name VARCHAR(255),
    file_path VARCHAR(500),
    notes TEXT,
    grade VARCHAR(20) DEFAULT 'Pending',
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (assignment_id) REFERENCES assignments(id) ON DELETE CASCADE
);

-- 8. Announcements Table
CREATE TABLE IF NOT EXISTS announcements (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    target_class VARCHAR(50) DEFAULT 'ALL',
    created_by VARCHAR(100) DEFAULT 'Teacher',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ====================================================================
-- SEED DATA: Default Admin & Student Accounts
-- ====================================================================

-- Default Teacher / Admin (password: admin123 -> SHA-256: 240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9)
INSERT IGNORE INTO admins (id, name, adminId, email, password, phone)
VALUES (1, 'Dr. Rajesh Sharma', 'T-101', 'admin@example.com', 'admin123', '+91 9876543210');

-- Default Student (password: student123 -> SHA-256 or plaintext for easy start)
INSERT IGNORE INTO students (id, name, studentId, class_grade, email, password, phone)
VALUES (1, 'Ashutosh Shehra', 'STU-2026', 'Class 10', 'student@example.com', 'student123', '+91 9123456780');

-- ====================================================================
-- SEED DATA: 27 CURATED EXAMS (3 for each Class from 5 to 12 + College)
-- ====================================================================

-- CLASS 5 EXAMS (1, 2, 3)
INSERT IGNORE INTO exams (id, title, class_grade, subject, duration_minutes, total_marks, passing_marks) VALUES
(1, 'Class 5 Mathematics: Numbers & Basic Operations', 'Class 5', 'Mathematics', 20, 20, 8),
(2, 'Class 5 General Science: Living Things & Habitats', 'Class 5', 'Science', 20, 20, 8),
(3, 'Class 5 English: Nouns, Verbs & Vocabulary', 'Class 5', 'English', 20, 20, 8);

-- CLASS 6 EXAMS (4, 5, 6)
INSERT IGNORE INTO exams (id, title, class_grade, subject, duration_minutes, total_marks, passing_marks) VALUES
(4, 'Class 6 Mathematics: Integers, Decimals & Fractions', 'Class 6', 'Mathematics', 25, 20, 8),
(5, 'Class 6 Science: Food, Components & Fiber', 'Class 6', 'Science', 25, 20, 8),
(6, 'Class 6 Social Studies: Earth in Solar System & History', 'Class 6', 'Social Studies', 25, 20, 8);

-- CLASS 7 EXAMS (7, 8, 9)
INSERT IGNORE INTO exams (id, title, class_grade, subject, duration_minutes, total_marks, passing_marks) VALUES
(7, 'Class 7 Mathematics: Equations, Lines & Angles', 'Class 7', 'Mathematics', 25, 20, 8),
(8, 'Class 7 Science: Nutrition in Plants & Heat', 'Class 7', 'Science', 25, 20, 8),
(9, 'Class 7 English: Active-Passive Voice & Prepositions', 'Class 7', 'English', 25, 20, 8);

-- CLASS 8 EXAMS (10, 11, 12)
INSERT IGNORE INTO exams (id, title, class_grade, subject, duration_minutes, total_marks, passing_marks) VALUES
(10, 'Class 8 Mathematics: Rational Numbers & Linear Equations', 'Class 8', 'Mathematics', 30, 25, 10),
(11, 'Class 8 Science: Force, Pressure & Microorganisms', 'Class 8', 'Science', 30, 25, 10),
(12, 'Class 8 Social Science: Indian Constitution & Modern History', 'Class 8', 'Social Science', 30, 25, 10);

-- CLASS 9 EXAMS (13, 14, 15)
INSERT IGNORE INTO exams (id, title, class_grade, subject, duration_minutes, total_marks, passing_marks) VALUES
(13, 'Class 9 Mathematics: Polynomials & Coordinate Geometry', 'Class 9', 'Mathematics', 30, 25, 10),
(14, 'Class 9 Science: Matter, Cell Structure & Laws of Motion', 'Class 9', 'Science', 30, 25, 10),
(15, 'Class 9 English: Tenses, Modals & Reported Speech', 'Class 9', 'English', 30, 25, 10);

-- CLASS 10 EXAMS (16, 17, 18)
INSERT IGNORE INTO exams (id, title, class_grade, subject, duration_minutes, total_marks, passing_marks) VALUES
(16, 'Class 10 Mathematics: Trigonometry & Quadratic Equations', 'Class 10', 'Mathematics', 35, 25, 10),
(17, 'Class 10 Science: Chemical Reactions & Electricity', 'Class 10', 'Science', 35, 25, 10),
(18, 'Class 10 English: Advanced Grammar, Clauses & Synthesis', 'Class 10', 'English', 35, 25, 10);

-- CLASS 11 EXAMS (19, 20, 21)
INSERT IGNORE INTO exams (id, title, class_grade, subject, duration_minutes, total_marks, passing_marks) VALUES
(19, 'Class 11 Physics: Kinematics, Vectors & Laws of Motion', 'Class 11', 'Physics', 40, 30, 12),
(20, 'Class 11 Chemistry: Atomic Structure & Chemical Bonding', 'Class 11', 'Chemistry', 40, 30, 12),
(21, 'Class 11 Mathematics / CS: Sets, Relations & Python Basics', 'Class 11', 'Computer Science', 40, 30, 12);

-- CLASS 12 EXAMS (22, 23, 24)
INSERT IGNORE INTO exams (id, title, class_grade, subject, duration_minutes, total_marks, passing_marks) VALUES
(22, 'Class 12 Physics: Electrostatics, Optics & Current Electricity', 'Class 12', 'Physics', 45, 30, 12),
(23, 'Class 12 Chemistry: Electrochemistry, Kinetics & Organic Chemistry', 'Class 12', 'Chemistry', 45, 30, 12),
(24, 'Class 12 Computer Science: Python OOPs, Stacks & SQL Queries', 'Class 12', 'Computer Science', 45, 30, 12);

-- COLLEGE / CS EXAMS (25, 26, 27)
INSERT IGNORE INTO exams (id, title, class_grade, subject, duration_minutes, total_marks, passing_marks) VALUES
(25, 'College CS: Core Java, OOPs, Collections & Servlets', 'College / CS', 'Java Programming', 50, 40, 16),
(26, 'College CS: Database Management Systems (DBMS & SQL)', 'College / CS', 'DBMS', 50, 40, 16),
(27, 'College CS: Full-Stack Web Technologies (HTML5, JS, REST)', 'College / CS', 'Web Technologies', 50, 40, 16);

-- ====================================================================
-- SAMPLE QUESTIONS SEED (Exams 1, 16, 22, 25, etc.)
-- ====================================================================

-- Class 5 Math (Exam 1)
INSERT IGNORE INTO questions (exam_id, question_text, option_a, option_b, option_c, option_d, correct_option, explanation, marks) VALUES
(1, 'What is the place value of 7 in the number 47,852?', '70', '700', '7,000', '70,000', 'C', 'In 47,852, 7 is in the thousands place, so its place value is 7 * 1,000 = 7,000.', 5),
(1, 'Which of the following is equivalent to 3/4?', '6/8', '5/8', '9/16', '3/8', 'A', 'Multiplying numerator and denominator by 2 gives (3*2)/(4*2) = 6/8.', 5),
(1, 'A rectangular garden has length 12m and breadth 8m. What is its perimeter?', '96m', '40m', '20m', '48m', 'B', 'Perimeter of rectangle = 2 * (length + breadth) = 2 * (12 + 8) = 40m.', 5),
(1, 'What is the smallest 5-digit number?', '99999', '10001', '10000', '11111', 'C', 'The smallest 5-digit number is 10,000.', 5);

-- Class 10 Math (Exam 16)
INSERT IGNORE INTO questions (exam_id, question_text, option_a, option_b, option_c, option_d, correct_option, explanation, marks) VALUES
(16, 'If sin theta = 3/5, what is the value of cos theta (for acute angle theta)?', '4/5', '5/4', '3/4', '1/5', 'A', 'Using identity cos^2 theta = 1 - sin^2 theta = 1 - (9/25) = 16/25 => cos theta = 4/5.', 5),
(16, 'What are the roots of the quadratic equation x^2 - 5x + 6 = 0?', '2 and 3', '-2 and -3', '1 and 6', '-1 and -6', 'A', 'Factoring gives (x - 2)(x - 3) = 0, so x = 2 or x = 3.', 5),
(16, 'The discriminant (D = b^2 - 4ac) of the equation 2x^2 - 4x + 3 = 0 is:', '-8 (No real roots)', '8 (Two real roots)', '0 (Equal roots)', '16', 'A', 'D = (-4)^2 - 4(2)(3) = 16 - 24 = -8. Since D < 0, there are no real roots.', 5),
(16, 'What is the 10th term of the Arithmetic Progression: 2, 7, 12, 17...?', '47', '52', '45', '50', 'A', 'a = 2, d = 5. a_10 = a + 9d = 2 + 9(5) = 47.', 5),
(16, 'If the mean of 5 observations is 15, what is the sum of all observations?', '75', '60', '90', '45', 'A', 'Sum = Mean * Count = 15 * 5 = 75.', 5);

-- Class 12 Physics (Exam 22)
INSERT IGNORE INTO questions (exam_id, question_text, option_a, option_b, option_c, option_d, correct_option, explanation, marks) VALUES
(22, 'What is the SI unit of Electric Flux?', 'N/C', 'Volt * meter (V m)', 'Farad', 'Tesla', 'B', 'Electric flux phi = E * A = (V/m) * m^2 = V m (Volt-meter) or N m^2 / C.', 6),
(22, 'The capacity of a parallel plate capacitor increases when:', 'Plate separation increases', 'Plate area increases', 'Dielectric constant decreases', 'Voltage decreases', 'B', 'Capacitance C = (k * epsilon_0 * A) / d. Increasing area A increases C.', 6),
(22, 'Which phenomenon proves the transverse wave nature of light?', 'Refraction', 'Interference', 'Diffraction', 'Polarization', 'D', 'Polarization can only occur in transverse waves, proving light is a transverse wave.', 6),
(22, 'According to Lenz Law, the polarity of induced EMF is such that it:', 'Supports the cause producing it', 'Opposes the cause producing it', 'Is always zero', 'Accelerates the magnet', 'B', 'Lenz law states that the direction of induced current opposes the change in magnetic flux producing it.', 6),
(22, 'The drift velocity of free electrons in a conductor is proportional to:', 'Electric field E', 'E^2', '1/E', 'Independent of E', 'A', 'Drift velocity v_d = (e * E * tau) / m, which is directly proportional to electric field E.', 6);

-- College / CS Java (Exam 25)
INSERT IGNORE INTO questions (exam_id, question_text, option_a, option_b, option_c, option_d, correct_option, explanation, marks) VALUES
(25, 'Which Java Collection class guarantees elements are stored in insertion order and allows fast indexed access?', 'HashSet', 'ArrayList', 'TreeSet', 'HashMap', 'B', 'ArrayList maintains insertion order and allows O(1) random indexed access.', 8),
(25, 'In Java Servlet lifecycle, which method is called only once when the servlet is first loaded into memory?', 'service()', 'doGet()', 'init()', 'destroy()', 'C', 'The init() method is executed once by the servlet container during initialization.', 8),
(25, 'Which principle of OOP allows a subclass to provide a specific implementation of a method defined in its superclass?', 'Abstraction', 'Method Overriding (Runtime Polymorphism)', 'Encapsulation', 'Method Overloading', 'B', 'Method overriding is runtime polymorphism where a child class overrides parent method.', 8),
(25, 'What is the purpose of the PreparedStatement in JDBC?', 'Faster execution via pre-compilation & SQL Injection prevention', 'Only for reading XML', 'To manage database transactions automatically', 'To replace MySQL server', 'A', 'PreparedStatement pre-compiles SQL and parameterizes inputs, preventing SQL Injection.', 8),
(25, 'Which keyword in Java is used to prevent a class from being inherited?', 'static', 'abstract', 'final', 'synchronized', 'C', 'A final class cannot be subclassed in Java.', 8);

-- Seed Sample Announcements
INSERT IGNORE INTO announcements (title, content, target_class, created_by) VALUES
('Mid-Term Examination Schedule Announced', 'Dear Students, the mid-term examinations for all classes (Class 5 to 12 and College) will commence next week. Please review your syllabi.', 'ALL', 'Dr. Rajesh Sharma'),
('Proctored Exam Guidelines', 'Full-screen mode and camera proctoring are enforced during examinations. Switching tabs more than 3 times will trigger auto-submission.', 'ALL', 'Examination Controller');
