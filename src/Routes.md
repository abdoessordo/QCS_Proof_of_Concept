# API ROUTES

Base URL: `http://localhost:8080`

Base endpoint: `/api/v1`

## Routes

-------------------
### 1. Users
* `GET /api/v1/users` - returns a list of all users
* `GET /api/v1/users/deleted` - returns a list of all soft deleted users
* `POST /api/v1/users/add` - adds a new user to the database
* `DELETE /api/v1/users/{SSO}` - deletes a user from the database
* `POST /api/v1/users/{SSO}/restore` - restores a deleted user from the database

### 2. Roles


 