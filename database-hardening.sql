-- Run after the original database.sql on an existing database.
-- This removes the known plaintext seed credentials. Existing users should
-- reset their password; legacy SHA-256 values are migrated on next login.
USE exam;
UPDATE admins SET password='240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9'
WHERE email='admin@example.com' AND password='admin123';
UPDATE students SET password=SHA2('student123',256)
WHERE email='student@example.com' AND password='student123';
