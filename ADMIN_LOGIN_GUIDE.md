# 🔐 How to Login as Admin

## ✅ **Admin User Credentials**

I've updated the `DataSeeder.java` to automatically create an admin user when the backend starts.

### **Admin Login Credentials:**
```
📧 Email:    admin@eateasy.com
🔑 Password: admin123
```

---

## 🚀 **Steps to Login as Admin**

### **Option 1: Restart Backend (Recommended)**

The admin user will be created automatically when you restart the backend:

1. **Stop the current backend** (press Ctrl+C in the backend terminal)

2. **Restart the backend:**
   ```bash
   cd "d:\Project Folder New\Food App Curser"
   mvn spring-boot:run
   ```

3. **Look for this message in the console:**
   ```
   ✅ Admin user created successfully!
   📧 Email: admin@eateasy.com
   🔑 Password: admin123
   ```

4. **Go to login page:**
   - Navigate to: `http://localhost:4200/login`
   - Enter email: `admin@eateasy.com`
   - Enter password: `admin123`
   - Click **Login**

5. **You'll be redirected to:**
   - `http://localhost:4200/admin/dashboard`
   - You'll see the beautiful admin dashboard! 🎨

---

### **Option 2: Manual Database Insert (If backend is already running)**

If you don't want to restart, you can manually insert the admin user into MySQL:

```sql
-- Connect to your database first
USE eat_easy;

-- Insert admin user (password is bcrypt hash of "admin123")
INSERT INTO users (name, email, phone, password, role) 
VALUES (
    'Admin User',
    'admin@eateasy.com',
    '9999999999',
    '$2a$10$N9qo8uLOickgx2ZMRZoMe.q2tCST0FW2J3H.PNNZj7s7W3DLNqY6a',
    'ADMIN'
);
```

---

## 📝 **What Changed in DataSeeder.java**

### **Before:**
```java
@Component
public class DataSeeder implements CommandLineRunner {
    private final RestaurantRepository restaurantRepository;
    
    // Only seeded restaurants
}
```

### **After:**
```java
@Component
public class DataSeeder implements CommandLineRunner {
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // ✅ Creates admin user FIRST
        if (userRepository.findByEmail("admin@eateasy.com").isEmpty()) {
            User admin = new User();
            admin.setName("Admin User");
            admin.setEmail("admin@eateasy.com");
            admin.setPhone("9999999999");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(User.Role.ADMIN);
            userRepository.save(admin);
        }
        
        // Then seeds restaurants
        // ...
    }
}
```

---

## 🎯 **After Login**

Once logged in as admin, you can access:

1. **Admin Dashboard** (`/admin/dashboard`)
   - View all system statistics
   - Quick action cards
   - Recent activity

2. **Restaurant Management** (`/admin/restaurants`)
   - Search and filter restaurants
   - Approve pending restaurants
   - Block/activate restaurants

3. **User Management** (`/admin/users`)
   - View all users
   - Filter by role (Customer, Owner, Admin)
   - Block/activate users

4. **Analytics** (`/admin/analytics`)
   - System analytics and reports

5. **Coupon Management** (`/admin/coupons`)
   - Manage discount coupons

---

## ⚠️ **Important Notes**

1. **The admin user is created only once**
   - If the email `admin@eateasy.com` already exists, it won't create a duplicate
   - Safe to restart the backend multiple times

2. **Password is encrypted**
   - Uses BCrypt hashing (Spring Security default)
   - Never stored in plain text

3. **Role-based access**
   - The `ADMIN` role gives access to all admin routes
   - Make sure your SecurityConfig allows admin endpoints

---

## 🔍 **Troubleshooting**

### **Issue: Can't see admin user created message**
- Check the backend console output
- Look for: `✅ Admin user created successfully!`

### **Issue: Login fails with 401**
- Verify the email/password are correct
- Check if user exists in database:
  ```sql
  SELECT * FROM users WHERE email = 'admin@eateasy.com';
  ```

### **Issue: Redirects to wrong page after login**
- Check `AuthService.login()` in frontend
- Should check role and redirect to `/admin/dashboard` for ADMIN role

### **Issue: 403 Forbidden on admin pages**
- Check `SecurityConfig.java`
- Ensure admin endpoints are not blocked
- Verify JWT token includes the ADMIN role

---

## 🎉 **Quick Start**

**TL;DR:**
1. Restart backend (it will create admin user)
2. Go to `http://localhost:4200/login`
3. Login with: `admin@eateasy.com` / `admin123`
4. Enjoy your beautiful admin dashboard! 🎨

---

**Created:** December 1, 2025  
**Admin Email:** admin@eateasy.com  
**Admin Password:** admin123
