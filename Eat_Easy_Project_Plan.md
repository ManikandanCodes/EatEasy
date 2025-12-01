# Eat Easy - Full Stack Food Ordering Platform
## Project Plan Document

**Project Title:** Eat Easy  
**Type:** Multi-Restaurant Food Ordering & Kitchen Dashboard Platform  
**Frontend:** Angular 19.2.0  
**Backend:** Java 17, Spring Boot  
**Database:** MySQL  

---

## Table of Contents

1. [Project Overview](#project-overview)
2. [Project Structure](#project-structure)
3. [Backend Implementation](#backend-implementation)
4. [Frontend Implementation](#frontend-implementation)
5. [Database Schema](#database-schema)
6. [Key Features](#key-features)
7. [Technical Decisions](#technical-decisions)
8. [Setup Instructions](#setup-instructions)
9. [API Endpoints](#api-endpoints)

---

## Project Overview

Eat Easy is a comprehensive food ordering platform that connects customers, restaurants, kitchen staff, and administrators. The system supports multiple restaurants, real-time order tracking, and complete order management workflow.

### Technology Stack

- **Frontend:** Angular 19.2.0 with TypeScript
- **Backend:** Java 17 with Spring Boot 3.x
- **Database:** MySQL 8.0+
- **Authentication:** JWT (JSON Web Tokens)
- **Styling:** Custom CSS (no frameworks)
- **Build Tools:** Maven (Backend), npm (Frontend)

---

## Project Structure

```
eat-easy/
├── backend/                           # Spring Boot Application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/eateasy/
│   │   │   │   ├── EatEasyApplication.java
│   │   │   │   ├── config/           # Configuration classes
│   │   │   │   │   ├── SecurityConfig.java
│   │   │   │   │   ├── CorsConfig.java
│   │   │   │   │   └── JwtAuthenticationFilter.java
│   │   │   │   ├── controller/       # REST Controllers
│   │   │   │   │   ├── AuthController.java
│   │   │   │   │   ├── RestaurantController.java
│   │   │   │   │   ├── MenuController.java
│   │   │   │   │   ├── CartController.java
│   │   │   │   │   ├── OrderController.java
│   │   │   │   │   ├── CouponController.java
│   │   │   │   │   ├── AdminController.java
│   │   │   │   │   ├── KitchenController.java
│   │   │   │   │   └── FileController.java
│   │   │   │   ├── service/          # Business logic
│   │   │   │   │   ├── UserService.java
│   │   │   │   │   ├── JwtService.java
│   │   │   │   │   ├── RestaurantService.java
│   │   │   │   │   ├── MenuService.java
│   │   │   │   │   ├── CartService.java
│   │   │   │   │   ├── OrderService.java
│   │   │   │   │   ├── CouponService.java
│   │   │   │   │   ├── PaymentService.java
│   │   │   │   │   └── FileUploadService.java
│   │   │   │   ├── repository/       # JPA Repositories
│   │   │   │   │   ├── UserRepository.java
│   │   │   │   │   ├── RestaurantRepository.java
│   │   │   │   │   ├── MenuCategoryRepository.java
│   │   │   │   │   ├── MenuItemRepository.java
│   │   │   │   │   ├── CartRepository.java
│   │   │   │   │   ├── CartItemRepository.java
│   │   │   │   │   ├── OrderRepository.java
│   │   │   │   │   ├── OrderItemRepository.java
│   │   │   │   │   └── CouponRepository.java
│   │   │   │   ├── model/            # Entity classes
│   │   │   │   │   ├── User.java
│   │   │   │   │   ├── Restaurant.java
│   │   │   │   │   ├── MenuCategory.java
│   │   │   │   │   ├── MenuItem.java
│   │   │   │   │   ├── Cart.java
│   │   │   │   │   ├── CartItem.java
│   │   │   │   │   ├── Order.java
│   │   │   │   │   ├── OrderItem.java
│   │   │   │   │   └── Coupon.java
│   │   │   │   ├── dto/              # Data Transfer Objects
│   │   │   │   │   ├── LoginRequest.java
│   │   │   │   │   ├── LoginResponse.java
│   │   │   │   │   ├── RegisterRequest.java
│   │   │   │   │   ├── RestaurantDTO.java
│   │   │   │   │   ├── MenuItemDTO.java
│   │   │   │   │   ├── CartDTO.java
│   │   │   │   │   ├── OrderDTO.java
│   │   │   │   │   └── CouponDTO.java
│   │   │   │   ├── exception/        # Exception handling
│   │   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   │   └── UnauthorizedException.java
│   │   │   │   └── util/             # Utilities
│   │   │   │       └── JwtUtil.java
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── application-dev.properties
│   │   └── test/                     # Test files
│   ├── pom.xml
│   └── README.md
│
├── frontend/                          # Angular 19.2.0 Application
│   ├── src/
│   │   ├── app/
│   │   │   ├── core/                 # Core services and guards
│   │   │   │   ├── services/
│   │   │   │   │   ├── auth.service.ts
│   │   │   │   │   ├── api.service.ts
│   │   │   │   │   └── storage.service.ts
│   │   │   │   ├── guards/
│   │   │   │   │   ├── auth.guard.ts
│   │   │   │   │   └── role.guard.ts
│   │   │   │   └── interceptors/
│   │   │   │       └── http.interceptor.ts
│   │   │   ├── features/
│   │   │   │   ├── auth/             # Authentication module
│   │   │   │   │   ├── login/
│   │   │   │   │   │   ├── login.component.ts
│   │   │   │   │   │   ├── login.component.html
│   │   │   │   │   │   └── login.component.css
│   │   │   │   │   ├── register/
│   │   │   │   │   │   ├── register.component.ts
│   │   │   │   │   │   ├── register.component.html
│   │   │   │   │   │   └── register.component.css
│   │   │   │   │   └── profile/
│   │   │   │   │       ├── profile.component.ts
│   │   │   │   │       ├── profile.component.html
│   │   │   │   │       └── profile.component.css
│   │   │   │   ├── customer/         # Customer features
│   │   │   │   │   ├── restaurant-list/
│   │   │   │   │   ├── restaurant-detail/
│   │   │   │   │   ├── cart/
│   │   │   │   │   ├── checkout/
│   │   │   │   │   ├── order-history/
│   │   │   │   │   └── order-tracking/
│   │   │   │   ├── restaurant/       # Restaurant owner dashboard
│   │   │   │   │   ├── dashboard/
│   │   │   │   │   ├── restaurant-profile/
│   │   │   │   │   ├── menu-management/
│   │   │   │   │   ├── orders/
│   │   │   │   │   └── analytics/
│   │   │   │   ├── kitchen/          # Kitchen dashboard
│   │   │   │   │   ├── kitchen-dashboard/
│   │   │   │   │   └── order-details/
│   │   │   │   └── admin/            # Admin dashboard
│   │   │   │       ├── admin-dashboard/
│   │   │   │       ├── user-management/
│   │   │   │       ├── restaurant-management/
│   │   │   │       ├── analytics/
│   │   │   │       └── coupon-management/
│   │   │   ├── shared/               # Shared components
│   │   │   │   ├── components/
│   │   │   │   │   ├── navbar/
│   │   │   │   │   ├── footer/
│   │   │   │   │   ├── loading-spinner/
│   │   │   │   │   └── rating-star/
│   │   │   │   ├── models/
│   │   │   │   │   ├── user.model.ts
│   │   │   │   │   ├── restaurant.model.ts
│   │   │   │   │   ├── menu-item.model.ts
│   │   │   │   │   ├── order.model.ts
│   │   │   │   │   └── cart.model.ts
│   │   │   │   ├── pipes/
│   │   │   │   │   ├── currency.pipe.ts
│   │   │   │   │   └── time-ago.pipe.ts
│   │   │   │   └── validators/
│   │   │   │       └── custom.validators.ts
│   │   │   ├── app.component.ts
│   │   │   ├── app.component.html
│   │   │   ├── app.component.css
│   │   │   ├── app-routing.module.ts
│   │   │   └── app.module.ts
│   │   ├── assets/
│   │   │   ├── images/
│   │   │   └── icons/
│   │   ├── environments/
│   │   │   ├── environment.ts
│   │   │   └── environment.prod.ts
│   │   ├── styles.css                # Global styles
│   │   └── index.html
│   ├── angular.json
│   ├── package.json
│   ├── tsconfig.json
│   └── README.md
│
├── database/
│   └── schema.sql                    # MySQL schema script
│
├── docs/                             # Documentation
│   ├── API_DOCUMENTATION.md
│   └── USER_GUIDE.md
│
└── README.md                         # Main project README
```

---

## Database Schema

### Tables

#### 1. users
```sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    role ENUM('CUSTOMER', 'RESTAURANT_OWNER', 'ADMIN', 'KITCHEN_STAFF') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

#### 2. restaurants
```sql
CREATE TABLE restaurants (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    owner_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    address TEXT NOT NULL,
    cuisine VARCHAR(100),
    rating DECIMAL(3,2) DEFAULT 0.00,
    opening_time TIME,
    closing_time TIME,
    status ENUM('OPEN', 'CLOSED') DEFAULT 'CLOSED',
    image_url VARCHAR(500),
    is_approved BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (owner_id) REFERENCES users(id)
);
```

#### 3. menu_categories
```sql
CREATE TABLE menu_categories (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    restaurant_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    display_order INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);
```

#### 4. menu_items
```sql
CREATE TABLE menu_items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    category_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    description TEXT,
    ingredients TEXT,
    image_url VARCHAR(500),
    is_veg BOOLEAN DEFAULT TRUE,
    is_best_seller BOOLEAN DEFAULT FALSE,
    stock_status ENUM('IN_STOCK', 'OUT_OF_STOCK') DEFAULT 'IN_STOCK',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES menu_categories(id) ON DELETE CASCADE
);
```

#### 5. carts
```sql
CREATE TABLE carts (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    restaurant_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);
```

#### 6. cart_items
```sql
CREATE TABLE cart_items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cart_id BIGINT NOT NULL,
    menu_item_id BIGINT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    price DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cart_id) REFERENCES carts(id) ON DELETE CASCADE,
    FOREIGN KEY (menu_item_id) REFERENCES menu_items(id)
);
```

#### 7. orders
```sql
CREATE TABLE orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    restaurant_id BIGINT NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    discount_amount DECIMAL(10,2) DEFAULT 0.00,
    final_amount DECIMAL(10,2) NOT NULL,
    status ENUM('PENDING', 'ACCEPTED', 'REJECTED', 'PREPARING', 'READY', 'OUT_FOR_DELIVERY', 'DELIVERED', 'CANCELLED') DEFAULT 'PENDING',
    delivery_address TEXT NOT NULL,
    phone VARCHAR(20) NOT NULL,
    delivery_instructions TEXT,
    payment_status ENUM('PENDING', 'PAID', 'FAILED') DEFAULT 'PENDING',
    payment_id VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);
```

#### 8. order_items
```sql
CREATE TABLE order_items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT NOT NULL,
    menu_item_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (menu_item_id) REFERENCES menu_items(id)
);
```

#### 9. coupons
```sql
CREATE TABLE coupons (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(50) UNIQUE NOT NULL,
    discount_type ENUM('PERCENTAGE', 'FIXED') NOT NULL,
    discount_value DECIMAL(10,2) NOT NULL,
    min_amount DECIMAL(10,2) DEFAULT 0.00,
    max_discount DECIMAL(10,2),
    valid_from TIMESTAMP NOT NULL,
    valid_to TIMESTAMP NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    usage_limit INT,
    used_count INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

#### 10. order_coupons
```sql
CREATE TABLE order_coupons (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT NOT NULL,
    coupon_id BIGINT NOT NULL,
    discount_amount DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (coupon_id) REFERENCES coupons(id)
);
```

---

## Backend Implementation

### 1. Entity Classes (JPA Entities)

#### User.java
- Fields: id, email, password, name, phone, role (enum), timestamps
- Relationships: OneToMany with Restaurant (owner), Cart, Order

#### Restaurant.java
- Fields: id, owner, name, address, cuisine, rating, openingTime, closingTime, status, imageUrl, isApproved
- Relationships: ManyToOne with User, OneToMany with MenuCategory, Cart, Order

#### MenuCategory.java
- Fields: id, restaurant, name, displayOrder
- Relationships: ManyToOne with Restaurant, OneToMany with MenuItem

#### MenuItem.java
- Fields: id, category, name, price, description, ingredients, imageUrl, isVeg, isBestSeller, stockStatus
- Relationships: ManyToOne with MenuCategory

#### Cart.java & CartItem.java
- Cart: id, user, restaurant, cartItems, timestamps
- CartItem: id, cart, menuItem, quantity, price

#### Order.java & OrderItem.java
- Order: id, user, restaurant, totalAmount, discountAmount, finalAmount, status, deliveryAddress, phone, deliveryInstructions, paymentStatus, paymentId, orderItems, timestamps
- OrderItem: id, order, menuItem, quantity, price

#### Coupon.java
- Fields: id, code, discountType, discountValue, minAmount, maxDiscount, validFrom, validTo, isActive, usageLimit, usedCount

### 2. DTOs (Data Transfer Objects)

All DTOs will be created to transfer data between layers:
- Request DTOs for input validation
- Response DTOs for API responses
- Mapper methods to convert between Entity and DTO

### 3. Services

#### UserService
- `register(RegisterRequest)` - User registration
- `login(LoginRequest)` - User authentication
- `getProfile(Long userId)` - Get user profile
- `updateProfile(Long userId, UpdateProfileRequest)` - Update profile

#### JwtService
- `generateToken(User user)` - Generate JWT token
- `validateToken(String token)` - Validate token
- `getUsernameFromToken(String token)` - Extract username
- `getRolesFromToken(String token)` - Extract roles

#### RestaurantService
- `createRestaurant(RestaurantCreateDTO, Long ownerId)` - Create restaurant
- `updateRestaurant(Long id, RestaurantUpdateDTO)` - Update restaurant
- `getRestaurant(Long id)` - Get restaurant details
- `getAllRestaurants()` - List all restaurants
- `searchRestaurants(String query, String cuisine, String status)` - Search and filter
- `toggleAvailability(Long id)` - Open/close restaurant
- `approveRestaurant(Long id)` - Admin approval

#### MenuService
- `createCategory(MenuCategoryCreateDTO)` - Create category
- `updateCategory(Long id, MenuCategoryUpdateDTO)` - Update category
- `deleteCategory(Long id)` - Delete category
- `createMenuItem(MenuItemCreateDTO)` - Create menu item
- `updateMenuItem(Long id, MenuItemUpdateDTO)` - Update menu item
- `deleteMenuItem(Long id)` - Delete menu item
- `getMenuByRestaurant(Long restaurantId)` - Get restaurant menu

#### CartService
- `getOrCreateCart(Long userId, Long restaurantId)` - Get/create cart
- `addItemToCart(Long cartId, Long menuItemId, Integer quantity)` - Add item
- `updateCartItem(Long cartItemId, Integer quantity)` - Update quantity
- `removeCartItem(Long cartItemId)` - Remove item
- `clearCart(Long cartId)` - Clear cart
- `getCart(Long userId)` - Get user cart

#### OrderService
- `createOrder(OrderCreateDTO, Long userId)` - Create order
- `getOrder(Long orderId)` - Get order details
- `getUserOrders(Long userId)` - Get user order history
- `getRestaurantOrders(Long restaurantId)` - Get restaurant orders
- `updateOrderStatus(Long orderId, OrderStatusUpdateDTO)` - Update status
- `acceptOrder(Long orderId, Long restaurantId)` - Accept order
- `rejectOrder(Long orderId, Long restaurantId)` - Reject order

#### CouponService
- `validateCoupon(String code, BigDecimal orderAmount)` - Validate coupon
- `applyCoupon(String code, BigDecimal orderAmount)` - Apply coupon
- `createCoupon(CouponCreateDTO)` - Create coupon (Admin)
- `getAllCoupons()` - List all coupons
- `updateCoupon(Long id, CouponUpdateDTO)` - Update coupon

#### PaymentService
- `processPayment(PaymentRequest)` - Mock payment processing
- `verifyPayment(String paymentId)` - Verify payment

#### FileUploadService
- `uploadImage(MultipartFile file, String folder)` - Upload image
- `deleteImage(String imagePath)` - Delete image
- `getImagePath(String filename)` - Get image path

### 4. Controllers

All controllers will follow RESTful conventions:

#### AuthController
- `POST /api/auth/register` - User registration
- `POST /api/auth/login` - User login
- `GET /api/auth/profile` - Get current user profile
- `PUT /api/auth/profile` - Update profile

#### RestaurantController
- `GET /api/restaurants` - List all restaurants (with filters)
- `GET /api/restaurants/{id}` - Get restaurant details
- `POST /api/restaurants` - Create restaurant (Restaurant Owner)
- `PUT /api/restaurants/{id}` - Update restaurant
- `DELETE /api/restaurants/{id}` - Delete restaurant
- `PUT /api/restaurants/{id}/toggle-status` - Toggle open/close
- `GET /api/restaurants/search` - Search restaurants

#### MenuController
- `GET /api/restaurants/{restaurantId}/menu` - Get restaurant menu
- `POST /api/menu/categories` - Create category
- `PUT /api/menu/categories/{id}` - Update category
- `DELETE /api/menu/categories/{id}` - Delete category
- `POST /api/menu/items` - Create menu item
- `PUT /api/menu/items/{id}` - Update menu item
- `DELETE /api/menu/items/{id}` - Delete menu item

#### CartController
- `GET /api/cart` - Get user cart
- `POST /api/cart/items` - Add item to cart
- `PUT /api/cart/items/{id}` - Update cart item
- `DELETE /api/cart/items/{id}` - Remove item
- `DELETE /api/cart` - Clear cart

#### OrderController
- `POST /api/orders` - Create order
- `GET /api/orders/{id}` - Get order details
- `GET /api/orders` - Get user orders
- `PUT /api/orders/{id}/status` - Update order status
- `POST /api/orders/{id}/accept` - Accept order (Restaurant)
- `POST /api/orders/{id}/reject` - Reject order (Restaurant)

#### CouponController
- `POST /api/coupons/validate` - Validate coupon
- `GET /api/coupons` - List all coupons
- `POST /api/coupons` - Create coupon (Admin)
- `PUT /api/coupons/{id}` - Update coupon (Admin)
- `DELETE /api/coupons/{id}` - Delete coupon (Admin)

#### AdminController
- `GET /api/admin/users` - List all users
- `PUT /api/admin/users/{id}/block` - Block/unblock user
- `GET /api/admin/restaurants/pending` - Get pending restaurants
- `PUT /api/admin/restaurants/{id}/approve` - Approve restaurant
- `GET /api/admin/analytics` - Get analytics
- `GET /api/admin/reports` - Get reports

#### KitchenController
- `GET /api/kitchen/orders` - Get kitchen orders
- `GET /api/kitchen/orders/{id}` - Get order details
- `PUT /api/kitchen/orders/{id}/status` - Update order status

#### FileController
- `POST /api/files/upload` - Upload image
- `GET /api/files/{filename}` - Get image

### 5. Security Configuration

#### SecurityConfig.java
- Configure Spring Security
- JWT authentication filter
- Role-based access control
- Password encoder (BCrypt)
- CORS configuration

#### JwtAuthenticationFilter.java
- Extract JWT token from request header
- Validate token
- Set authentication in SecurityContext

---

## Frontend Implementation

### 1. Core Services

#### auth.service.ts
```typescript
- login(email: string, password: string): Observable<LoginResponse>
- register(userData: RegisterRequest): Observable<any>
- logout(): void
- getCurrentUser(): User | null
- isAuthenticated(): boolean
- getToken(): string | null
```

#### api.service.ts
- Base HTTP service with error handling
- API endpoint constants

#### storage.service.ts
- Local storage management
- Token storage/retrieval
- User data storage

### 2. Guards

#### auth.guard.ts
- Protect routes requiring authentication
- Redirect to login if not authenticated

#### role.guard.ts
- Protect routes based on user role
- Check if user has required role

### 3. Interceptors

#### http.interceptor.ts
- Add JWT token to request headers
- Handle 401 errors (unauthorized)
- Handle common errors

### 4. Feature Modules

#### Auth Module
**Login Component:**
- Email and password form
- Form validation
- Error handling
- Redirect after login

**Register Component:**
- Registration form (name, email, password, phone, role)
- Form validation
- Success/error messages

**Profile Component:**
- Display user information
- Edit profile form
- Change password option

#### Customer Module
**Restaurant List Component:**
- Grid/list view of restaurants
- Search bar
- Filters (cuisine, rating, status)
- Sort options
- Pagination

**Restaurant Detail Component:**
- Restaurant information
- Menu categories tabs
- Menu items with images
- Add to cart functionality
- Veg/Non-Veg indicators
- Best seller badges

**Cart Component:**
- Display cart items
- Update quantities
- Remove items
- Apply coupon code
- Calculate totals
- Proceed to checkout button

**Checkout Component:**
- Delivery address form
- Phone number
- Delivery instructions
- Order summary
- Coupon application
- Payment method selection
- Place order button

**Order History Component:**
- List of past orders
- Order status
- Order details
- Reorder functionality

**Order Tracking Component:**
- Real-time order status
- Status timeline
- Estimated delivery time
- Order details

#### Restaurant Module
**Dashboard Component:**
- Overview statistics
- Recent orders
- Quick actions
- Revenue chart

**Restaurant Profile Component:**
- Restaurant information form
- Image upload
- Opening/closing times
- Toggle availability

**Menu Management Component:**
- Category management (CRUD)
- Menu item management (CRUD)
- Image upload for items
- Bulk operations

**Orders Component:**
- List of orders
- Order details
- Accept/Reject buttons
- Status updates
- Filter by status

**Analytics Component:**
- Sales charts
- Popular items
- Order statistics
- Revenue reports

#### Kitchen Module
**Kitchen Dashboard Component:**
- Real-time order list
- Filter by status
- Order cards with timer
- Status update buttons
- Auto-refresh (polling)

**Order Details Component:**
- Order information
- Item list with quantities
- Preparation timer
- Status update buttons
- Notes section

#### Admin Module
**Admin Dashboard Component:**
- System overview
- Key metrics
- Charts and graphs
- Quick actions

**User Management Component:**
- User list with filters
- User details
- Block/unblock functionality
- Role management

**Restaurant Management Component:**
- Restaurant list
- Pending approvals
- Approve/block actions
- Restaurant details

**Analytics Component:**
- Daily orders chart
- Top restaurants
- Revenue analytics
- Cancellation reports
- User statistics

**Coupon Management Component:**
- Coupon list
- Create/edit coupon form
- Activate/deactivate
- Usage statistics

### 5. Shared Components

#### Navbar Component
- Logo
- Navigation links (role-based)
- User menu
- Logout button

#### Footer Component
- Footer information
- Links
- Copyright

#### Loading Spinner Component
- Reusable loading indicator

#### Rating Star Component
- Display rating with stars
- Interactive rating (if needed)

### 6. Models/Interfaces

TypeScript interfaces for:
- User
- Restaurant
- MenuItem
- MenuCategory
- Cart
- CartItem
- Order
- OrderItem
- Coupon
- LoginRequest/Response
- etc.

### 7. Pipes

#### Currency Pipe
- Format prices with currency symbol

#### Time Ago Pipe
- Display relative time (e.g., "2 hours ago")

### 8. Routing

```typescript
Routes:
- /login - Login page
- /register - Registration page
- /restaurants - Restaurant list (public)
- /restaurants/:id - Restaurant details (public)
- /customer/cart - Shopping cart
- /customer/checkout - Checkout
- /customer/orders - Order history
- /customer/orders/:id - Order tracking
- /restaurant/dashboard - Restaurant dashboard
- /restaurant/profile - Restaurant profile
- /restaurant/menu - Menu management
- /restaurant/orders - Restaurant orders
- /kitchen/dashboard - Kitchen dashboard
- /kitchen/orders/:id - Kitchen order details
- /admin/dashboard - Admin dashboard
- /admin/users - User management
- /admin/restaurants - Restaurant management
- /admin/analytics - Analytics
- /admin/coupons - Coupon management
```

### 9. Styling

#### Global Styles (styles.css)
- CSS variables for colors, fonts, spacing
- Reset/normalize CSS
- Utility classes
- Dark/light mode variables
- Responsive breakpoints

#### Component Styles
- Component-specific CSS files
- Mobile-first responsive design
- Modern UI with smooth transitions
- Consistent color palette
- Clean and simple design

---

## Key Features

### 1. User Management
- ✅ User registration with role selection
- ✅ Login with JWT authentication
- ✅ Profile management
- ✅ Role-based access control (CUSTOMER, RESTAURANT_OWNER, ADMIN, KITCHEN_STAFF)

### 2. Restaurant Management
- ✅ Add/edit/delete restaurants
- ✅ Upload restaurant images
- ✅ Manage restaurant details (name, address, cuisine, timing)
- ✅ Toggle availability (open/close)
- ✅ Admin approval system

### 3. Menu & Categories
- ✅ CRUD operations for categories
- ✅ CRUD operations for menu items
- ✅ Image upload for items
- ✅ Veg/Non-Veg indicators
- ✅ Best Seller badges
- ✅ Out of Stock status

### 4. Customer Features
- ✅ Browse restaurants with search and filters
- ✅ View restaurant menu
- ✅ Add items to cart
- ✅ Apply coupon codes
- ✅ Checkout with address and instructions
- ✅ Mock payment integration
- ✅ Order tracking

### 5. Order Management Workflow
- ✅ Customer places order
- ✅ Restaurant receives order notification
- ✅ Restaurant accepts/rejects order
- ✅ Accepted orders sent to Kitchen Dashboard
- ✅ Kitchen updates status (Preparing → Ready)
- ✅ Real-time status updates (polling)
- ✅ Order history

### 6. Kitchen Dashboard
- ✅ Real-time order list
- ✅ Item-level preparation view
- ✅ Timer for each order
- ✅ Status update buttons
- ✅ Auto-refresh functionality

### 7. Admin Dashboard
- ✅ User management (view, block/unblock)
- ✅ Restaurant approval/blocking
- ✅ Analytics and reports
- ✅ Coupon management
- ✅ System overview

---

## Technical Decisions

### Academic Project Focus

1. **Payment Gateway:** Mock payment service (simulates payment without real gateway integration)
2. **Real-time Updates:** Polling mechanism (every 5-10 seconds) instead of WebSockets
3. **File Storage:** Local file system storage in `backend/uploads/` directory
4. **Architecture:** Monolithic Spring Boot application (easier to understand and demonstrate)
5. **Database:** MySQL with JPA/Hibernate ORM
6. **Security:** JWT tokens, BCrypt password hashing, role-based access control
7. **Styling:** Custom CSS (no UI frameworks like Bootstrap or Angular Material)
8. **API Communication:** RESTful APIs with JSON

### Performance Considerations
- Database indexing on frequently queried fields
- Pagination for lists
- Image optimization
- Lazy loading for images
- Efficient database queries

### Security Measures
- Password encryption (BCrypt)
- JWT token expiration
- CORS configuration
- Input validation
- SQL injection prevention (JPA)
- XSS prevention

---

## Setup Instructions

### Prerequisites
- Java 17 or higher
- Node.js 18+ and npm
- MySQL 8.0+
- Maven 3.6+
- IDE (IntelliJ IDEA / Eclipse / VS Code)

### Backend Setup

1. **Database Setup:**
   ```bash
   # Create MySQL database
   mysql -u root -p
   CREATE DATABASE eat_easy;
   exit
   
   # Run schema script
   mysql -u root -p eat_easy < database/schema.sql
   ```

2. **Configure Application:**
   - Update `application.properties` with database credentials
   - Set JWT secret key
   - Configure file upload path

3. **Run Backend:**
   ```bash
   cd backend
   mvn clean install
   mvn spring-boot:run
   ```
   Backend will run on `http://localhost:8080`

### Frontend Setup

1. **Install Dependencies:**
   ```bash
   cd frontend
   npm install
   ```

2. **Configure Environment:**
   - Update `environment.ts` with API base URL

3. **Run Frontend:**
   ```bash
   ng serve
   ```
   Frontend will run on `http://localhost:4200`

### Default Admin Account
Create admin user manually in database or through registration endpoint.

---

## API Endpoints Summary

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - User login
- `GET /api/auth/profile` - Get user profile
- `PUT /api/auth/profile` - Update profile

### Restaurants
- `GET /api/restaurants` - List restaurants (with filters)
- `GET /api/restaurants/{id}` - Get restaurant details
- `POST /api/restaurants` - Create restaurant
- `PUT /api/restaurants/{id}` - Update restaurant
- `DELETE /api/restaurants/{id}` - Delete restaurant
- `PUT /api/restaurants/{id}/toggle-status` - Toggle availability

### Menu
- `GET /api/restaurants/{id}/menu` - Get restaurant menu
- `POST /api/menu/categories` - Create category
- `PUT /api/menu/categories/{id}` - Update category
- `DELETE /api/menu/categories/{id}` - Delete category
- `POST /api/menu/items` - Create menu item
- `PUT /api/menu/items/{id}` - Update menu item
- `DELETE /api/menu/items/{id}` - Delete menu item

### Cart
- `GET /api/cart` - Get user cart
- `POST /api/cart/items` - Add item to cart
- `PUT /api/cart/items/{id}` - Update cart item
- `DELETE /api/cart/items/{id}` - Remove item
- `DELETE /api/cart` - Clear cart

### Orders
- `POST /api/orders` - Create order
- `GET /api/orders/{id}` - Get order details
- `GET /api/orders` - Get user orders
- `PUT /api/orders/{id}/status` - Update order status
- `POST /api/orders/{id}/accept` - Accept order
- `POST /api/orders/{id}/reject` - Reject order

### Coupons
- `POST /api/coupons/validate` - Validate coupon
- `GET /api/coupons` - List coupons
- `POST /api/coupons` - Create coupon (Admin)
- `PUT /api/coupons/{id}` - Update coupon (Admin)
- `DELETE /api/coupons/{id}` - Delete coupon (Admin)

### Admin
- `GET /api/admin/users` - List users
- `PUT /api/admin/users/{id}/block` - Block/unblock user
- `GET /api/admin/restaurants/pending` - Pending restaurants
- `PUT /api/admin/restaurants/{id}/approve` - Approve restaurant
- `GET /api/admin/analytics` - Get analytics

### Kitchen
- `GET /api/kitchen/orders` - Get kitchen orders
- `GET /api/kitchen/orders/{id}` - Get order details
- `PUT /api/kitchen/orders/{id}/status` - Update status

### Files
- `POST /api/files/upload` - Upload image
- `GET /api/files/{filename}` - Get image

---

## Development Timeline

### Phase 1: Project Setup & Database
- Initialize Spring Boot project
- Initialize Angular project
- Create database schema
- Set up basic configuration

### Phase 2: Backend Core
- Implement entities and repositories
- Create DTOs and mappers
- Implement services
- Set up security and JWT

### Phase 3: Backend APIs
- Implement all REST controllers
- Add exception handling
- File upload functionality
- Testing APIs

### Phase 4: Frontend Core
- Set up Angular project structure
- Create core services and guards
- Implement routing
- Set up HTTP interceptors

### Phase 5: Frontend Features
- Authentication module
- Customer features
- Restaurant dashboard
- Kitchen dashboard
- Admin dashboard

### Phase 6: Integration & Testing
- Connect frontend with backend
- Test all features
- Fix bugs
- Performance optimization

### Phase 7: Styling & Polish
- Apply CSS styling
- Responsive design
- UI/UX improvements
- Final testing

---

## Testing Strategy

### Backend Testing
- Unit tests for services
- Integration tests for controllers
- Repository tests
- Security tests

### Frontend Testing
- Component unit tests
- Service tests
- E2E tests (optional)

### Manual Testing
- User flows
- Role-based access
- Order workflow
- Payment simulation

---

## Deployment Considerations

### Backend Deployment
- Build JAR file: `mvn clean package`
- Run with: `java -jar target/eat-easy-backend.jar`
- Configure production database
- Set environment variables

### Frontend Deployment
- Build: `ng build --prod`
- Deploy `dist/` folder to web server
- Configure API base URL

### Database
- Production MySQL database
- Regular backups
- Migration scripts

---

## Future Enhancements (Optional)

- Real payment gateway integration
- WebSocket for real-time updates
- Push notifications
- Email notifications
- SMS notifications
- Delivery partner management
- Rating and review system
- Advanced analytics
- Mobile app (React Native/Flutter)

---

## Documentation

- API Documentation (Swagger/OpenAPI)
- User Guide
- Developer Guide
- Database Schema Documentation
- Deployment Guide

---

**Document Version:** 1.0  
**Last Updated:** 2024  
**Project Status:** Planning Phase


