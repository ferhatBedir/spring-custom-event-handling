# spring-custom-event-handling

OBJECTIVE:
  When User updated, User will updated automatically in Deparment.
  
FOR RUN:
  You must do config in application.proferties file.
  
WHEN APP RUN
  1. Useing basic authentication in App. 
     If you dont changed, User name and password in SecurityConfigClass
     - UserName : User 
     - Password : 123123 
  2. List<Depaertment> add to Database.(User must be null in DepartmentList.)
  3. List<User> add to Database.(DepartmentId must not be null or failure.)
     Users add to automatically in Department.
  4. For Example,
      User will be update.
      When User updated, User will updated automatically in Department and save to Database.
  
 
  FOR SWAGGER
  
    if app is runing, you enter http://localhost:8080/swagger-ui.html.
    You view swagger doc.
