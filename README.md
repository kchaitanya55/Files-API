# FILES API

## Overview
The API will scan the Entire System and store the Metadata of only Image Files in H2 Database.
The API has endpoint through which We can search the Files. 

## Guidelines for Developer

1. Clone this Project

2.Run the Application as Java Application.

## Guidelines for User

1.Hit the API using the path "/search" and valid search Parameters as shown below. We will be able to get the Files Metadata.
    
```java
	    /search?location=d

            /search?name=IMG_9151.JPG
```
    


2.If the user send the invalid parameter which is not available .Error response will be sent to the user.

3.Basic Authentication is required for all the Requests with credentials(admin/admin);
