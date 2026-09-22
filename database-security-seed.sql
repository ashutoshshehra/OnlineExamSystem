-- Never store application credentials in source control.
-- Configure SMARTEXAM_DB_PASSWORD before importing/running the application.
-- Demo hashes below use legacy SHA-256 only so the first successful login can be
-- transparently upgraded to PBKDF2 by the login servlet.
INSERT IGNORE INTO admins (id, name, adminId, email, password, phone)
VALUES (1, 'Dr. Rajesh Sharma', 'T-101', 'admin@example.com', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', '+91 9876543210');
