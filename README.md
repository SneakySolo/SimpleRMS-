# 🍽️ Restaurant Management System (Java + MySQL)

![Java](https://img.shields.io/badge/Language-Java-orange?logo=openjdk)
![MySQL](https://img.shields.io/badge/Database-MySQL-blue?logo=mysql)
![Status](https://img.shields.io/badge/Project-Active-brightgreen)
![License](https://img.shields.io/badge/License-MIT-yellow)

A **Restaurant Management System** built using **Core Java** and **MySQL**, designed for administrators and restaurant owners to efficiently manage menu items, orders, inventory, and sales.

---

## 🚀 Features

### 👨‍💼 Admin Panel
- Secure admin login.
- **Menu Management:** Insert, update, and view menu items.
- **Restaurant Management:** Add new restaurants (manually or automatically).
- **Inventory Control:** Insert and update inventory records.
- **Sales Reports:** View and analyze total sales.

### 👨‍🍳 Restaurant Owner Panel
- Owner login and authentication.
- **Order Placement:** Place customer orders with stock validation and billing.
- **Menu Control:** Insert and view menu items.
- **Sales View:** View daily or overall sales records.
- **Inventory Management:** Add, view, and update stock quantities.

---

## 🧱 Project Structure

| File | Description |
|------|--------------|
| `First_Step.java` | Entry point; directs to admin or owner login. |
| `Admin_menu.java` | Provides all admin options. |
| `Owner_login.java` | Handles owner authentication. |
| `Owner_menu.java` | Menu-driven interface for restaurant owners. |
| `Insert_automatic_menu.java` | Automatically inserts predefined menu data. |
| `Insert_automatic_restaurant.java` | Automatically adds restaurant data. |
| `Insert_menu.java` | Manually insert new dishes into the menu. |
| `Manual_insert_menu.java` | Supports manual menu entry. |
| `Manual_insert_owner.java` | Registers a new restaurant owner. |
| `Manual_insert_restaurant.java` | Registers new restaurants manually. |
| `Insert_inv.java` | Inserts inventory items. |
| `Update_inv.java` | Updates stock quantities. |
| `View_inv.java` | Displays inventory data. |
| `View_menu.java` | Displays all menu items. |
| `View_sales.java` | Shows detailed sales reports. |
| `Place_order.java` | Handles customer orders and billing. |
| `JDBC_Connection.java` | Manages MySQL connections. |
| `package-info.java` | Package-level documentation. |

---

## 🗄️ Database Configuration

**Database Name:** `restaurant_management`

Update your credentials in `JDBC_Connection.java`:

```java
String url = "jdbc:mysql://localhost:3306/restaurant_management";
String username = "root";
String password = "your_password";
Connection con = DriverManager.getConnection(url, username, password);
