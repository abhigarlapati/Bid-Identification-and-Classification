Bid Identification and Classification System

Overeview

This project is an Email-Based Bid Management System that automates the process of identifying, classifying, and managing bid-related emails.
It helps procurement managers efficiently track and organize bids linked to projects and contractors, without manual sorting.

Features

* Automatic identification of bid-related emails
* Classification and tagging of emails by project, contractor, and bid type
* Creation and maintenance of bid records linked to emails
* Support for contract-related emails and documentation

API Endpoints

BidController Mappings
GET     -  /api/bids/getallbids
GET     -  /api/bids/getBidByBidId/{id}
POST    -  /api/bids/updateBid/{id}
DELETE  -  /api/bids/deleteBid/{id}

ContractController Mappings

GET   -  /api/contract/getAll
POST  -  /api/contract/createcontract
PUT   -  /api/contract/{id}/status



