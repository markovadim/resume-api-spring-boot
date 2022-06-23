# Application for work with resumes (Spring boot)
____

### Show all resumes (GET method): ###
>[GET] http://localhost:8080/resumes
>>Example:
![image](https://user-images.githubusercontent.com/86801437/175404630-2e72da39-7ef2-4cef-8a48-a7fc005930a3.png)
With page parameter:
![image](https://user-images.githubusercontent.com/86801437/175404776-cfafc61d-bf5a-4ed0-abdf-c25108e910f9.png)


### Create new resume (PUT method): ###  
>[PUT] http://localhost:8080/resumes
>>Example:
![image](https://user-images.githubusercontent.com/86801437/175405104-8a5a87d7-344a-45dc-a702-52444d93d8cc.png)

### Delete resume from database by id (DELETE method): ###  
>[DELETE] http://localhost:8080/resumes/:id
>>Example:
![image](https://user-images.githubusercontent.com/86801437/175405268-99840620-5ca7-4314-b772-fd3e96776279.png)

### Update resume in database (PUT method): ###
>[PUT] http://localhost:8080/resumes/:id
>>Example:
![image](https://user-images.githubusercontent.com/86801437/175405471-6a63559e-3820-45af-ad24-187b9c14a550.png)

### Find resume by id (GET method): ###
>[GET] http://localhost:8080/resumes/:id
>>Example:
![image](https://user-images.githubusercontent.com/86801437/175405688-bb8459d3-c240-44ee-81f2-3407999bef54.png)

### Find resume by another fields (GET method): ###  
>[GET] http://localhost:8080/resumes/
>>Example (with @RequestParam ):
![image](https://user-images.githubusercontent.com/86801437/175405965-0bf3884c-57f1-438e-b7f5-0f9e16de6f48.png)