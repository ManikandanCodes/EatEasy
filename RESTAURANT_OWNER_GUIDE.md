# Restaurant Owner Login & Menu Management Guide

## ✅ Issue Resolved!

### Problem
When logging in as `owner@eateasy.com`, the system was redirecting to the restaurant registration page instead of the dashboard.

### Root Cause
The backend repository method `findByOwnerId()` was designed to return a single restaurant, but in the DataSeeder, we assigned **all 4 inbuilt restaurants** (Burger King, Pizza Hut, Sushi Palace, Taco Bell) to the same owner. This created a mismatch.

### Solution Applied
1. **Updated `RestaurantRepository.java`**: Changed `findByOwnerId()` to return `List<Restaurant>` instead of single `Restaurant`
2. **Updated `RestaurantController.java`**: Modified the `/api/restaurant/my` endpoint to handle multiple restaurants and return the first one

---

## 🔑 Login Credentials

### Restaurant Owner
- **Email**: `owner@eateasy.com`
- **Password**: `owner123`
- **Assigned Restaurants**: 
  - Burger King
  - Pizza Hut
  - Sushi Palace
  - Taco Bell

When you login, you'll see **Burger King** as the active restaurant (the first one in the list).

---

## 📋 How to Add Menu Items

### Step 1: Login
1. Go to the login page
2. Enter email: `owner@eateasy.com`
3. Enter password: `owner123`
4. Click "Login"

### Step 2: Access Menu Management
After successful login, you'll be redirected to the restaurant owner dashboard. From there:

1. Click on **"Menu"** or **"Menu Management"** in the sidebar/navbar
2. You'll see options for:
   - **View Menu Items**
   - **Add Category**
   - **Add Item**
   - **Category List**
   - **Item List**

### Step 3: Create Menu Categories (if needed)
Before adding items, you need categories:

1. Navigate to **"Add Category"**
2. Fill in:
   - Category Name (e.g., "Burgers", "Sides", "Drinks")
   - Description (optional)
3. Click "Save" or "Create Category"

### Step 4: Add Menu Items
1. Navigate to **"Add Item"** or **"Add Menu Item"**
2. Fill in the form:
   - **Name**: Item name (e.g., "Whopper")
   - **Description**: Details about the item
   - **Price**: Item price
   - **Category**: Select from dropdown
   - **Image**: Upload or provide image URL
   - **Available**: Toggle to mark as available/unavailable
3. Click "Save" or "Add Item"

### Step 5: View & Manage
- Go to **"Menu"** to see all items
- Edit or delete items as needed
- Toggle item availability

---

## 🎯 Quick Navigation

### Restaurant Owner Routes
- `/owner/dashboard` - Main dashboard
- `/owner/profile` - Restaurant profile
- `/owner/menu` - Menu management overview
- `/owner/menu/categories` - View all categories
- `/owner/menu/add-category` - Create new category
- `/owner/menu/items` - View all menu items
- `/owner/menu/add-item` - Create new menu item
- `/owner/orders` - View restaurant orders
- `/owner/analytics` - View analytics

---

## 🔧 Technical Details

### Backend Changes
**File**: `backend/src/main/java/com/example/backend/repository/RestaurantRepository.java`
```java
// Before
Restaurant findByOwnerId(Long ownerId);

// After
List<Restaurant> findByOwnerId(Long ownerId);
```

**File**: `backend/src/main/java/com/example/backend/controller/RestaurantController.java`
```java
@GetMapping("/restaurant/my")
public ResponseEntity<?> getMyRestaurant(Authentication authentication) {
    // ... authentication checks ...
    
    // Find all restaurants by owner ID
    List<Restaurant> restaurants = restaurantRepo.findByOwnerId(user.getId());
    
    if (restaurants == null || restaurants.isEmpty()) {
        return ResponseEntity.ok().body(null);
    }
    
    // Return the first restaurant
    return ResponseEntity.ok(restaurants.get(0));
}
```

### Database
The restaurants are already seeded with the owner:
- Burger King (owner: owner@eateasy.com)
- Pizza Hut (owner: owner@eateasy.com)
- Sushi Palace (owner: owner@eateasy.com)
- Taco Bell (owner: owner@eateasy.com)

All restaurants have `APPROVED` status and are ready for menu management.

---

## ✨ Next Steps

1. **Login** as the restaurant owner
2. **Explore** the dashboard
3. **Create categories** for your menu
4. **Add menu items** to each category
5. **Manage orders** as they come in

---

## 🐛 Troubleshooting

### Still redirecting to registration?
1. Clear your browser cache
2. Make sure the backend is running on port 8080
3. Check browser console for any errors (F12)
4. Verify the token is valid

### Can't see menu items?
1. Make sure you've created categories first
2. Check that items are marked as "available"
3. Verify the restaurant status is "APPROVED"

### Need to clear database?
See `HOW_TO_CLEAR_DATABASE.md` for instructions.

---

**Status**: ✅ Ready to use
**Last Updated**: December 1, 2025
