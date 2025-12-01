# SOLUTION: Clear Database to Fix Owner Login

## The Problem
The database has old restaurant data that was created before we assigned the owner. The DataSeeder only runs if the database is empty, so it hasn't created the properly configured restaurants.

## The Solution
Clear the database and restart the backend to let the DataSeeder create fresh data with proper owner assignments.

## Steps to Fix

### Option 1: Using MySQL Workbench (Easiest)

1. **Open MySQL Workbench**
2. **Connect to your local server**  
   - Username: `root`
   - Password: `17072001`

3. **Run these commands** in the SQL editor:
   ```sql
   DROP DATABASE IF EXISTS eat_easy;
   CREATE DATABASE eat_easy;
   ```

4. **Click the Execute button** (⚡ lightning bolt icon)

5. **Restart the backend**:
   - In your terminal running the backend, press `Ctrl+C`
   - Then run: `cd backend; ./mvnw spring-boot:run`

6. **Wait for the backend to start** (you'll see "Started BackendApplication")

7. **Try logging in again** with `owner@eateasy.com` / `owner123`

### Option 2: Using PowerShell/Command Line

1. **Stop the backend** (Ctrl+C in the backend terminal)

2. **Run this command**:
   ```powershell
   & "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -u root -p17072001 -e "DROP DATABASE IF EXISTS eat_easy; CREATE DATABASE eat_easy;"
   ```
   
   (Adjust the path if your MySQL is in a different location)

3. **Restart the backend**:
   ```powershell
   cd backend; ./mvnw spring-boot:run
   ```

4. **Wait for it to start**, then try logging in

## What Will Happen After Clearing

When the backend restarts with an empty database, the `DataSeeder` will automatically create:

### Users Created:
1. **Admin User**
   - Email: `admin@eateasy.com`
   - Password: `admin123`
   - Role: ADMIN

2. **Restaurant Owner**
   - Email: `owner@eateasy.com`  
   - Password: `owner123`
   - Role: RESTAURANT_OWNER

### Restaurants Created (All owned by owner@eateasy.com):
1. **Burger King**
   - Address: 123 Main St
   - Cuisines: American, Fast Food
   - Rating: 4.5
   - Hours:10:00 AM - 10:00 PM
   - Status: APPROVED ✅
   - Owner: owner@eateasy.com

2. **Pizza Hut**
   - Address: 456 Elm St
   - Cuisines: Italian, Pizza
   - Rating: 4.2
   - Hours: 11:00 AM - 11:00 PM
   - Status: APPROVED ✅
   - Owner: owner@eateasy.com

3. **Sushi Palace**
   - Address: 789 Oak St
   - Cuisines: Japanese, Sushi
   - Rating: 4.8
   - Hours: 12:00 PM - 10:00 PM
   - Status: APPROVED ✅
   - Owner: owner@eateasy.com

4. **Taco Bell**
   - Address: 321 Pine St
   - Cuisines: Mexican, Tacos
   - Rating: 4.0
   - Hours: 10:00 AM - 02:00 AM
   - Status: APPROVED ✅
   - Owner: owner@eateasy.com

## After Database is Cleared

1. **Login** as `owner@eateasy.com` / `owner123`
2. **You should see** the dashboard for Burger King (the first restaurant)
3. **You can now** add menus to any of the 4 restaurants!

## Quick Check
After the backend restarts, you should see these messages in the terminal:
```
✅ Admin user created successfully!
📧 Email: admin@eateasy.com
🔑 Password: admin123

✅ Restaurant Owner created successfully!
📧 Email: owner@eateasy.com
🔑 Password: owner123

✅ Sample restaurants seeded successfully (All APPROVED)!
```

If you see these messages, you're good to go! 🎉
