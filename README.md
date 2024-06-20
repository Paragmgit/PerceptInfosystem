# Stock Managment For Grocery Store
##Table of Contents
Introduction
Features
Entities
Controllers
Services
Installation

###Introduction
The Grocery Management System is a comprehensive application designed to streamline the operations of a grocery store. This system includes functionalities for managing items, categories, vendors, and administrators, providing an efficient way to handle various aspects of grocery store management.

###Features
CRUD Operations: Create, Read, Update, and Delete functionalities for Items, Categories, Vendors, and Admins.
Secure Password Handling: Secure password encryption and validation mechanisms.
File Upload Support: Allows uploading files for item management.
PDF Generation: Generate PDF reports for inventory and sales.
Error Handling and Validation: Comprehensive error handling and data validation.

###Entities
Item
Represents an item in the grocery store, including attributes such as name, brand, category, description, price, quantity, and timestamps for creation and updates.

Admin
Represents an administrator of the system with attributes like name, email, password, and timestamps for creation and updates.

Category
Defines a category for items with attributes such as name, description, list of items, and timestamps for creation and updates.

Vendor
Represents a vendor with attributes such as name, email, phone number, address, location, and timestamps for creation and updates.

###Controllers
VendorController
Manages vendor-related operations, including creating, retrieving, updating, and deleting vendors.

ItemController
Handles item-related operations, such as creating items, uploading files, generating PDF reports, and performing CRUD operations on items.

CategoryController
Manages category-related operations, including creating, retrieving, updating, and deleting categories.

AdminController
Handles admin-related operations, such as creating admins, retrieving admin details, updating admin information, deleting admins, and changing passwords.

###Services
AdminServices
Provides business logic for managing administrators, including creation, retrieval, updating, deletion, and password changes.

VendorService
Handles the business logic for managing vendors, ensuring proper validation and handling of vendor data.

ItemService
Manages item-related business logic, including file uploads, PDF generation, and CRUD operations.

CategoryService
Handles the business logic for managing categories, ensuring proper validation and handling of category data.

###Installation
Clone the repository:
git clone https://github.com/yourusername/grocery-management-system.git

