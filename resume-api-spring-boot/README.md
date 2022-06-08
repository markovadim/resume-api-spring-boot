# Application for work with resumes (Spring boot)
____

### Show all resumes (GET method): ###
>[GET] http://localhost:8080/resume
>>Example:
![image](https://user-images.githubusercontent.com/86801437/172254013-f749f828-7421-467c-bfd5-44b352038635.png)


### Create new resume (PUT method): ###  
>[PUT] http://localhost:8080/resume
>>Example:
![image](https://user-images.githubusercontent.com/86801437/172618181-9b946124-d274-4037-906b-b51939fe40ff.png)

### Delete resume from database by id (DELETE method): ###  
>[DELETE] http://localhost:8080/:id
>>Example:
![image](https://user-images.githubusercontent.com/86801437/172618615-f0a7ef69-7bfd-44d4-8bcc-dc68c63db081.png)

### Update resume in database (PUT method): ###
>[PUT] http://localhost:8080/:id
>>Example:
![image](https://user-images.githubusercontent.com/86801437/172619134-7424b606-6a53-4aa3-b40b-ef6a73da7cb3.png)

### Find resume by id (GET method): ###
>[GET] http://localhost:8080/resume/id/:id
>>Example:
![image](https://user-images.githubusercontent.com/86801437/172619555-63a88c29-b204-49d0-ac12-828bc9fe1ca0.png)

### Find resume by username (GET method): ###  
>[GET] http://localhost:8080/resume/user/:user
>>Example:
![image](https://user-images.githubusercontent.com/86801437/172620008-88a4a198-c79d-4590-8af2-7a5b7ce7627d.png)

### Find resume by another fields (GET method): ###  
>GET http://localhost:8080/search/
>>Example (with @RequestParam ):
![image](https://user-images.githubusercontent.com/86801437/172620642-0dff97c8-916e-43b5-a192-b7ceccca78f0.png)