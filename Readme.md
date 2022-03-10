
Author: Bhaskar Thalwar

## The Task
The assignment is to implement a warehouse software. This software should hold articles, and the articles should contain an identification number, a name and available stock. 
It should be possible to load articles into the software from a file, see the attached inventory.json.
The warehouse software should also have products, products are made of different articles. Products should have a name, price and a list of articles of which they are made from with a quantity. 
The products should also be loaded from a file, see the attached products.json. 
 
The warehouse should have at least the following functionality;
* Get all products and quantity of each that is an available with the current inventory
* Remove(Sell) a product and update the inventory accordingly

Procedure to run the code in local :

**1) Checkout the code using the below command :**
    
    git clone https://github.com/Bhaskarthalwar/Warehouse.git 
    git checkout master
    cd warehouse 

**2) Install the application in local M2 repo :**
    
      mvn clean install 

**3) Run the sprng boot application locally on 8080 port**
     
     mvn spring-boot:run
     The application will run on port 8080 by default 
     
**4) Test the below URL'S through postman or CURL**
    
      GET     : http://localhost:8080/warehouse/inventory 
      GET     : http://localhost:8080/warehouse/product
      GET     : http://localhost:8080/warehouse/inventory/{artId}
      GET     : http://localhost:8080/warehouse/product/{name}
      PATCH   : http://localhost:8080/warehouse/product/{name}/{artId}
      
      
             **The above API is used for selling the products so one the above API is executed , one can test the changes in memory via 
             triggering the first two API's and see the updated inventory details.**
      
 **5) This application supports the actuator end points as well**
    
     GET : http://localhost:8080/actuator/health
     
 _LIMITATIONS_ :
 
  1) The selling happens inmemory and one can see the updated values till the spring context exists and once the context gets destroyed. 
  The inventory data gets reloaded from the given json files.
  
  2) Not all the edge case scenarios are handled as in mostly the given requirements are met.


