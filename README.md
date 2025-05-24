## Bid Identification and Classification System

**Overeview**

This project is an Email-Based Bid Management System that automates the process of identifying, classifying, and managing bid-related emails.
It helps procurement managers efficiently track and organize bids linked to projects and contractors, without manual sorting.

**Features**

* Automatic identification of bid-related emails
* Classification and tagging of emails by project, contractor, and bid type
* Creation and maintenance of bid records linked to emails
* Support for contract-related emails and documentation

**NOTE : Our Project is not having frontend or any html pages so kindly test the project endpoints using Swagger UI or Postman API**

         
##  API Endpoints

###  BidController
| Method | Endpoint                          | Description                             |
|--------|-----------------------------------|-----------------------------------------|
| GET    | `/api/bids/getallbids`            | Retrieve all bids                       |
| GET    | `/api/bids/getBidByBidId/{id}`    | Get a specific bid by ID                |
| POST   | `/api/bids/updateBid/{id}`        | Update bid details                      |
| DELETE | `/api/bids/deleteBid/{id}`        | Delete a bid by ID                      |
###  ContractController

| Method | Endpoint                                 | Description                        |
|--------|------------------------------------------|------------------------------------|
| GET    | `/api/contract/getAll`                   | Fetch all contract entries         |
| POST   | `/api/contract/createcontract`           | Create a new contract              |
| PUT    | `/api/contract/{id}/status`              | Update status of a contract        |

---

###  EmailController

| Method | Endpoint                               | Description                             |
|--------|----------------------------------------|-----------------------------------------|
| POST   | `/api/processemail/`                   | Process and classify an incoming email  |
| GET    | `/api/processemail/search/emails`      | Search processed emails                 |

---

###  ProjectController

| Method | Endpoint                        | Description                            |
|--------|---------------------------------|----------------------------------------|
| GET    | `/api/projects/search-bids`     | Search bids by project name or status  |

---

### ReportController

| Method | Endpoint                     | Description                          |
|--------|------------------------------|--------------------------------------|
| GET    | `/api/report/bid-status`     | View status report of all bids       |

##  Data Relationships

- **Bid ↔ Email:** One-to-One (each bid is created from a specific email)
- **Project ↔ Bids:** One-to-Many (a project can have multiple bids)
- **Bid ↔ Classification:** Many-to-Many (a bid can have multiple classification tags)
- **Contract ↔ Bid:** Many-to-One (each contract is linked to a specific bid)

---

##  Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/bid-classification-system.git
   
## 2. Update the application.properties:
	spring.datasource.url=jdbc:postgresql://localhost:5432/" yourdb "
	spring.datasource.username=" yourusername "
	spring.datasource.password=" yourpassword "

## Swagger UI: http://localhost:8080/swagger-ui/index.html
   **After running the project in your machine , simply access the link http://localhost:8080/swagger-ui/index.html to test all mmy end points**

---

## Tech Stack

| Layer              | Technology         |
|-------------------|--------------------|
| Language           | Java               |
| Framework          | Spring Boot        |
| ORM                | JPA + Hibernate    |
| Database           | H2 (dev) / PostgreSQL (prod) |
| API Type           | RESTful APIs       |
| Build Tool         | Maven              |
| Email Processing   | Java Mail + Custom Rules Engine |
| Testing            | Postman, Swagger   |
















