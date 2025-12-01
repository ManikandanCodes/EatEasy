# DEBUGGING STEPS - Restaurant Owner Login Issue

## Current Status
- ✅ Backend is restarted with updated code
- ✅ `RestaurantRepository.findByOwnerId()` now returns List<Restaurant>
- ✅ `RestaurantController.getMyRestaurant()` returns first restaurant from list
- ✅ Frontend dashboard has debug logging added
- ❓ Still need to test if it works

## What to Check Now

### Step 1: Open Browser Console
1. Open the frontend (http://localhost:4200)
2. Press F12 to open Developer Tools
3. Go to the "Console" tab
4. Keep it open while you login

### Step 2: Login
1. Go to /login
2. Enter credentials:
   - Email: `owner@eateasy.com`
   - Password: `owner123`
3. Click Login

### Step 3: Check Console Output
Look for these messages:
```
"Restaurant data received:" <should show the restaurant object>
```

If you see:
```
"No restaurant found, redirecting to registration"
```
That means the API returned null.

If you see:
```
"Error fetching restaurant:" <error details>
```
That means the API call failed completely.

###Step 4: Check Network Tab
1. In Developer Tools, go to "Network" tab
2. Look for a request to `/api/restaurant/my`
3. Click on it
4. Check:
   - Status Code (should be 200)
   - Response (should show restaurant JSON)
   - Headers (check if Authorization token is present)

## Possible Issues

### Issue 1: Token Not Sent
If the Authorization header is missing from the request:
- The JWT token is not being attached to the request
- Check localStorage for 'token' key
- Check api.service.ts for interceptor

### Issue 2: API Returns Null
If the API returns 200 but response is null:
- The database might not have the restaurants-owner association
- Need to clear and reseed database

### Issue 3: API Returns 401/403
If the API returns unauthorized:
- Token might be invalid
- Token might not contain correct user info
- Check backend JWT configuration

### Issue 4: API Returns 500
If the API returns server error:
- Check backend console for Java stack trace
- Might be a database query issue

## How to Clear Database (If Needed)

If you need to reseed the database:

1. Stop the backend (Ctrl+C in the terminal)
2. Run this MySQL command:
   ```sql
   DROP DATABASE IF EXISTS eat_easy;
   CREATE DATABASE eat_easy;
   ```
3. Restart the backend
4. The DataSeeder will automatically create:
   - admin@eateasy.com (ADMIN)
   - owner@eateasy.com (RESTAURANT_OWNER)
   - 4 restaurants assigned to owner@eateasy.com

## Check browser console and network tab, then report back what you see!
