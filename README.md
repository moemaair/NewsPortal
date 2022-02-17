# NewsPortal
this is a REST API that is used for querying and retrieving Scoped news and information within an organisation . Employees can access general news and departmental news.

## Author
<li> Mohamed Ibrahim</li>

# Setup and installation 
<li>Fork this repo</li>
<li>Clone this repo</li>
<li>Navigate to the terminal</li>
<li>type in the command git clone and paste the url of clone and then press enter</li>

## to setup the Database
<li>run psql<create.sql</li>

## for local execution</li>
<li>Navigate to the App class and replace the password and databas name with your credentials, do the same to the test files</li>

## API documentation
### user
<ul>
<li>Creating user http://0.0.0.0:4567//users/new
{
"name":"Rage"
"position":"Hr manager",
"staff_role":"Editor"
}</li>
<li>Viewing User:http://0.0.0.0:4567/users </li>
<li>Viewing specific User Viewing Specific User ::http://0.0.0.0:4567//user/:id
     Replace :id with id of user
<li>Viewing Specific User Departments 
Replace :id with id  of user</li>  

### Departments
<ul>
<li>
## Creating Departments : http://0.0.0.0:4567/departments/new
{
"name":"Editing",
"description":"Editing of books"
}</li>
<li>Viewing Departments : http://0.0.0.0:4567/departments</li>
<li>Viewing Specific Department :http://0.0.0.0:4567/department/:id
Replace :id with id of department</li>
<li>Viewing Specific User in Departments :http://0.0.0.0:4567/department/:id/users
Replace :id with id of department</li>
<li>Adding users to department in Departments :http://0.0.0.0:4567/add/user/:id/department/:id
Replace :id with id of department</li>
</ul>

### News
<ul>
<li>Creating General News :/news/new/general
{
"title":"Meeting",
"description":"Discussion about expanding",
"user_id":1
}</li>
<li>Creating Department News /news/new/department
{
"title":"Meeting",
"description":"Discussion about expanding",
"department_id":1,
"user_id":1
}
</li>
<li>Viewing general news :/news/general </li>
<li>Viewing department news :/news/department/:id </li>
</ul>
 
 ## Technology
 <li>Java</li>
 <li>Postgress</li>
 <li>Heroku</li>
 <li>Postman</li>
 
 ## BDD
  <li>Click on the link :  </li>
  <li>copy the url  and place it in the postman url bar,Navigate to the post man and select post verb, </li>
  <li>on the body  tab select raw and  type json </li>
  <li>pass this data
{
"name":"Mohamed Ibrahim"
"position":"Director Manager",
"staff_role":"Editor"
}
  </li>
<li>click on send , you get an output similar to what you have typed with the  given Id</li>
<li></li>
<li>you can query by passing the Id generated ,replace the is with the number provided, /li>

## Contributions
for any queries , contributions to the project feel free to reach out  @karani.maina2010@gmail.com
 
## License
### License

MIT License

Copyright (c) 2022 Mohamed Ibrahim

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.


  
  
  






