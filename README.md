---

# 🛒 **KiranaStore: Online Grocery System**

KiranaStore is a complete, fast-track **Online Grocery Web Application** built using **Java, Servlets, JSP, Apache Derby**, and **Tomcat**.
It features a robust backend for inventory management and provides a secure, seamless user experience for customers.

---

## ✨ **Core Features**

KiranaStore separates functionalities clearly for both **Administrators** and **Customers**.

---

## 💼 **Admin Panel — Inventory & Customer Management**

| Icon  | Feature                | Description                                                               |
| ----- | ---------------------- | ------------------------------------------------------------------------- |
| 📦    | **Product Management** | Full CRUD operations — Add, Update (price/quantity), and Delete products. |
| 🧑‍💻 | **Customer Insights**  | View all registered customers and search details by ID or Name.           |

---

## 🛍️ **Customer Experience — Shop & Purchase**

| Icon | Feature                   | Description                                                      |
| ---- | ------------------------- | ---------------------------------------------------------------- |
| 🔐   | **Secure Authentication** | Quick Registration and Login using unique ID & secure password.  |
| 🍎   | **Product Browsing**      | Explore the entire catalog of available grocery items.           |
| 🛒   | **Cart & Checkout**       | Add products to cart and complete purchases smoothly.            |
| 🧾   | **Transaction Log**       | Every purchase stores a detailed transaction entry for tracking. |

---

## 🛠️ **Tech Stack Spotlight**

| Category | Technology                  | Purpose                                        |
| -------- | --------------------------- | ---------------------------------------------- |
| Backend  | **Java (Servlets/JSP)**     | Core business logic & dynamic content handling |
| Database | **Apache Derby (Embedded)** | Lightweight & transactional data storage       |
| Server   | **Apache Tomcat**           | Servlet container & runtime environment        |
| Frontend | **HTML5 / CSS3**            | Clean UI using *Roboto* font family            |

---

# ⚙️ **Getting Started: Local Setup**

Follow these steps to run **KiranaStore** on your local system.

---

## ✅ **Prerequisites**

* **JDK 17+**
* **Apache Tomcat 9 or 10**
* **IDE** (Eclipse / IntelliJ) with Dynamic Web Project support
* Apache Derby client JAR on classpath

---

## 📥 **Clone the Repository**

```bash
git clone https://github.com/your-username/KiranaStore.git
cd KiranaStore
```

---

## 🗄️ **Database Configuration (Important!)**

Set your Derby DB path correctly in `DAO` or `DBUtility` classes:

```java
jdbc:derby:C:/Users/hp/MyDB;create=true
```

### ▶️ Initialize the Database

Run the following utility **once**:

```java
public class DBSetup {
    public static void main(String[] args) {
        // Creates tables and inserts initial records
    }
}
```

This will automatically create:

* `CUSTOMER`
* `PRODUCT`
* `TRANSACTION`
* other necessary tables.

---

# 🚀 **Run the Application**

### Import project → Add Derby driver JAR → Run project on Tomcat

**Eclipse Path:**

```
Right Click Project → Run As → Run on Server
```

Then open:

```
http://localhost:8080/KiranaStore/LoginPage.jsp
```

---

# 🛑 **Common Developer Warning — Derby Lock Issue**

Apache Derby (Embedded Mode) allows **only one JVM** to access the DB at a time.

If you see:

```
ERROR XJ040: Failed to start database...
```

### ✔ Steps to Fix:

1. **Stop Tomcat Server completely.**
2. Open **Task Manager → End task:**
   `java.exe` / `javaw.exe`
3. Navigate to your DB folder:

```
C:/Users/hp/MyDB
```

4. Delete the file:

```
db.lck
```

5. Restart Tomcat.

---

# 🧩 **Project Structure**

```plaintext
KiranaStore/
│── src/
│   ├── servlets/
│   ├── dao/
│   ├── utils/
│   ├── models/
│── WebContent/
│   ├── JSP/
│   ├── CSS/
│   ├── Images/
│── DBSetup.java
│── pom.xml (if using Maven)
```

---

# ❤️ **Contributions**

Pull requests are welcome!
For major changes, open an issue first to discuss what you would like to improve.

---
## 📌 UI Demo

![KiranaStore UI Preview](https://github.com/Siddesh272/KiranaStore/blob/main/ezgif.com-optimize.gif)



