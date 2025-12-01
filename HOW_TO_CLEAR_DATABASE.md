# HOW TO CLEAR THE DATABASE

## Method 1: Using MySQL Workbench (Easiest)
1. Open MySQL Workbench
2. Connect to your local MySQL server (username: root, password: 17072001)
3. In the SQL editor, run this command:
   ```sql
   DROP DATABASE IF EXISTS eat_easy;
   CREATE DATABASE eat_easy;
   ```
4. Click the Execute button (lightning bolt icon)
5. Restart your Spring Boot backend
6. Try registering again

## Method 2: Using Command Line
1. Open Command Prompt or PowerShell
2. Navigate to your MySQL bin directory, usually:
   ```
   cd "C:\Program Files\MySQL\MySQL Server 8.0\bin"
   ```
   or
   ```
   cd "C:\Program Files\MySQL\MySQL Server 5.7\bin"
   ```
3. Run:
   ```
   mysql -u root -p
   ```
4. Enter password: 17072001
5. Run these commands:
   ```sql
   DROP DATABASE IF EXISTS eat_easy;
   CREATE DATABASE eat_easy;
   exit;
   ```
6. Restart your Spring Boot backend
7. Try registering again

## Method 3: Quick Test Without Clearing (Alternative)
If you just want to test quickly:
- Use the "Skip Login & Browse as Guest" button on the login page
- This will let you access the application without authentication
- You can explore the features without needing to register

## What's Happening
Your database has old user data from previous registrations. The email you're trying to use already exists in the database, which is why you're getting the "already registered" error.

## After Clearing the Database
Once you've cleared it and restarted the backend:
1. Go to /register
2. Fill in your details with ANY email (even one you tried before)
3. It should work now!

## Check Browser Console
Open the browser developer tools (F12) and check the Console tab to see detailed error messages and registration attempts.
