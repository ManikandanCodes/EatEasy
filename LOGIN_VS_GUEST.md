# Login vs Guest Access

## Why can guests see the menu?
It is standard practice for food delivery apps to allow **Guest Browsing**. This lets potential customers see what food is available before they commit to signing up.

## What is the difference?

| Feature | Guest (Not Logged In) | Customer (Logged In) |
|---------|-----------------------|----------------------|
| **View Restaurants** | ✅ Yes | ✅ Yes |
| **View Menus** | ✅ Yes | ✅ Yes |
| **Add to Cart** | ✅ Yes (Temporary) | ✅ Yes |
| **Checkout** | ❌ **No** (Redirects to Login) | ✅ **Yes** |
| **Order History** | ❌ **No** | ✅ **Yes** |
| **Track Order** | ❌ **No** | ✅ **Yes** |

## What I Changed
I have updated the application security to enforce these rules:

1. **Backend**: The API already protects the "Place Order" endpoint.
2. **Frontend**: I added an `AuthGuard` to the following pages:
   - `/checkout`
   - `/orders` (History)
   - `/track/:id` (Tracking)

Now, if a guest adds items to the cart and clicks "Checkout", they will be automatically redirected to the **Login Page**.
