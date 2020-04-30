GET: /products
Returns the full list of products include the average rating of the product. As this could
be a long list, implement options for pagination, and a filter option to the product code.

PARAMETERS 	REQUIRED 	DESCRIPTION
page	   	optional 	Current  page number
size	   	optional	How many products appear on a page
productCode optional	Filter to the product code

Response
{
            "id": 1,
            "code": "123456",
            "name": "Product1",
            "description": "Description1",
            "price": 0.1,
            "avgRating": 4.0
        },
        {
            "id": 2,
            "code": "654321",
            "name": "Product2",
            "description": "Description2",
            "price": 1.0,
            "avgRating": 5.2
        }
}

GET: /products/{productID}
Returns information about the product with the given productID. Include all ratings in a
separate property in the response.

PARAMETERS 	REQUIRED 	TYPE	 DESCRIPTION
productId	required 	Long  	 Returns all information about the product

Response
//Product with ratings
{
    "id": 1,
    "code": "123456",
    "name": "Product1",
    "description": "Description1",
    "price": 0.1,
    "avgRating": 4.0,
    "ratings": [
        {
            "id": 1,
            "value": 5,
            "productid": 1
        },
        {
            "id": 4,
            "value": 6,
            "productid": 1
        },
        {
            "id": 6,
            "value": 1,
            "productid": 1
        }
    ]
}

//Product without ratings
{
    "id": 10,
    "code": "147852",
    "name": "Product10",
    "description": "Description10",
    "price": 125.0,
    "ratings": []
}

//If product id not found throw exception
{
    "message": "Product not found by id: 101",
    "httpStatus": "NOT_FOUND",
    "timestamp": "2020-04-29T18:21:24.719+0000"
}


PUT: /products/{productID}
Updates product information

PARAMETERS 		REQUIRED 	TYPE	 DESCRIPTION
productId		required 	Long  	 Selected product to upgrade
code			required	string	 Product code length must be 6 character, just numbers 
name			required	string	 Product name, not be embty
description 	optional	string	 
price			required	double   Product price must be grather than 0
user			required	User	 User needed, because just owner can update product

{
	"code": "123416",
    "name": "fgh",
    "description": "",
    "price": 10,
    "user": {
        "id": 2,
        "name": "Máté",
        "email": "kunecz1@gmail.com"
    }
	
Response
HttpStatus: 200 - OK

//If product id not found throw exception
{
    "message": "Product not found by id: 101",
    "httpStatus": "NOT_FOUND",
    "timestamp": "2020-04-29T18:21:24.719+0000"
}

//If product code length not equal 6
{
    "message": "Product code length must be 6 character, your length is: 0",
    "httpStatus": "BAD_REQUEST",
    "timestamp": "2020-04-29T18:36:59.054+0000"
}

//If product name is empty
{
    "message": "Product name is empty",
    "httpStatus": "BAD_REQUEST",
    "timestamp": "2020-04-29T18:39:24.252+0000"
}

//If product price is less or equal 0
{
    "message": "Product price must be grather than 0",
    "httpStatus": "BAD_REQUEST",
    "timestamp": "2020-04-29T18:39:48.437+0000"
}

//If user is not owner
{
    "message": "You have no authority to perform the operation",
    "httpStatus": "BAD_REQUEST",
    "timestamp": "2020-04-29T18:40:48.020+0000"
}

POST: /rate/{productID}
Saves rating for a products

PARAMETERS 		REQUIRED 	TYPE	 DESCRIPTION
productId		required 	Long  	 Selected product to save rate
value			required	int		 Rate value must be between 1 and 10

Response
HttpStatus: 200 - OK

//If product id not found throw exception
{
    "message": "Product not found by id: 101",
    "httpStatus": "NOT_FOUND",
    "timestamp": "2020-04-29T18:21:24.719+0000"
}

//If value less than 0 or grather than 10 throw exception
{
    "message": "Rate value must be between 1 and 10",
    "httpStatus": "BAD_REQUEST",
    "timestamp": "2020-04-29T18:43:31.742+0000"
}

POST: /authenticate

PARAMETERS 		REQUIRED 	TYPE	 	DESCRIPTION
name			required 	string  	user name 
password		required	string		password

Response
JWT Token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKw6Fub3MiLCJleHAiOjE1ODgxODg0MzEsImlhdCI6MTU4ODE1MjQzMX0.hDQjm04IhmH-bf9GhG8yRlCKIHpTOaoDerdGS3RT3DQ

Throw exception next cases
	invalid username/password
	Invalid JWT signature
	Expired JWT token
	Unsupported JWT token
	JWT token compact of handler are invalid