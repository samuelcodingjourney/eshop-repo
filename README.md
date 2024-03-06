## Module 1
### Reflection 1 answer:
During the work I did for this module I have already implemented clean code principles and secure coding practices during the making of my source code.                          
The first clean code principle I implemented was meaningful names. Since names are everywhere in coding like variables, functions, classes, arguments,
and many other, some examples of meaningful names I did was on the newly added features of edit and delete I created. In edit feature, the method names in ProductController java file such as editProductPage and in
method findByName located in ProductRepository which is used in order to search by name which poduct to be edited, this was fully intentional to be directly stating what that method does. 
Other method names that uses meaningful names principle was in delete features of method called deleteByName and deleteProduct.
The second clean code principle I implemented was function, mainly to write functions that are short, well named, and nicely organized. Also functions that has no side effects that can
lead to other hidden things happening that are unwanted. That is why I specifically seperate the whole process of the editing feature to firstly a method that will only do a job of finding the product name
and then another method that will do the job of actually invoking the method previously mentioned while also setting the values using setter and redirecting after editing. These functions spans on 8-12 lines of code
and the functions I referenced was called findByName and ProductRepository so it is clear which one is doing which.
The third principle is comments. Although comments are not always needed and can even make other developers reading the source code harder to understand what the code actually do, on my source code everything besides the testing part
requires no comments at all since each code I wrote generally explains itself of what it does. Meaning, there are no necesarry comments required such as when writing legal comments,
informative comments, or TODO comments. The next principle is objects and data structures principle. This is all about the ProductService interface that hides the implementations of the create, findAll, findByName, and deleteByName
methods in my source code. This method is often called encapsulation. In doing so, the internal details of the classes or objects are hidden from the outside world. Changes made to the internals of those classes and objects would be easier as it would not affect the external code to begin with.
Regarding secure coding practices, there are a lot that I could improve on in the future, such as providing error handling as this is also the last principle of secure coding that I had not implemented yet. Another one is 
authorization and authentication such as a log in, register, and log out feature to make the website more secure. However, I did implement input validation when methods findByName and deleteByName check if 
the productName variable is null before proceeding to prevent potential null pointer exceptions.

### Reflection 2 answers:
1. Personally, I think that the number of unit tests depends on multiple factors such as the test cases that could be positive or negative, edge cases if there are any, or even how complicated are the methods.
   There are a lot of scenarios to be considered, but it must adequately verify the functionality of the class well enough. Since code coverage is a metric that can be used to measure how much of my source code has been tested,
   achieving 100% code coverage is not enough to make the source code bug or error free. There could still be some possible scenarios or edge cases that have not been tested that could endanger the codebase correctness.
2. By creating the new functional test suite to verify the number of items in the product list, there would be code duplication as setup and instance variables could be copied from the previous functional test
   suite. Test isolation would also be violated since this kind of test relies on pre-existance of products that could depend on external factors. When making the test suite, there would be a variable declaration like int expectedProductListSize = 3. 
   Since I could use driver.findElements(By.tagName("tr")).size()-1 to actually get how many products in the product list in the website, this could violate maintainability since the value that was assumed could change at any moment where a product has been added
   and me or other programmers who have access to the test suite must modify the value in the variable to provide correcteness of the testing which in turn makes the code quality even worse. To handle these problems, there should be a seperate class to seperate the common procedures and instance variables
   in order to avoid code duplication which makes the source code for functional testing part cleaner and imrpoves on maintainability. Lastly, to make sure that hardcoding number of products to check can make code quality lower than it should be, on this particular case using a feature in JUnit called parameterized testing could allow me to run the 
   test suite with different input parameters and in turn also help me identify edge cases or corner cases that could cause errors or problems.  


## Module 2
### Reflection answers:
1. During the exercise, I fixed 2 code quality issues. The first one was about removing @Autowired as field injection and use constructor injection instead. SonarCloud was was suggesting that using constructor injection would allow dependencies to become explicit in order to promote better test experience and maintainability of the code. So I refactored my code to use constructor injection by removing the @Autowired and luckily I alreayd have a constructor that takes ProductRepository as a parameter so this was enough to solved the issue. The second code quality issue and also the last after being analyzed by SonarCloud was that in method of testMain() SonarCloud mentioned that there should be at least on assertion in this test case. So I added the line import static org.junit.jupiter.api.Assertions.assertDoesNotThrow; and assertDoesNotThrow(() -> EshopApplication.main(new String []{})); to solved this issue and that by doing this, the method would execute without throwing any exceptions. 

2. Yes, because like for example the build.yml workflow that triggers on every push and pull request to ensure that every change are integrated into the main branch regularly. This is to ensure that tests run to detect issues can be done early in the development process. This is for Continuous Integration (CI), on the other hand Continuous Deployment (CD) has already been fulfilled by using PaaS which is Koyeb for free by taking use of Dockerfile. So in conclusion, yes I do think that  my current implementation has met the definition of Continuous Integration and Continuous Deployment and that even I used Sonar Cloud as a code quality analysis tool for CI.<br> 
Project Deployment URL: eshop-samuelcodingjourney.koyeb.app  

## Module 3
### Reflection
1. The principles that I applied is the 5 principles of SOLID on my project. Starting from the first principle that is SRP or is it called Single-Responsibility Principle. Now SRP states that a class should only have one reason to change. So that class has only one responsibility on the whole software project functionality. In this case, what I did in my project to apply this principle is to seperate the controller from ProductController with CarController because before CarController extends the ProductController so this violate the SRP principle due to the CarController still inherits the behavior that are specific to the products. So that in other words if there are changes, the CarController would have to relate to these changes whether or not it relates to cars or products. In other case, when using this class, this CarController class would be responsible on the operations or methods that are also from ProductController if this CarController class were to be referenced elsewhere in the project. The way I handled this violation to this principle was to create another java class called CarController in the controller folder that only contains the controller that relates to Car without extending or inheriting from other classes in the source code. 
The next principle that is OCP or Open-Closed Principle. Now for my project itself, there are no violation of this OCP principle because for example the class CarServiceImpl has a lot of methods that are implemented from the interface of CarService interface. By looking at the meaning of this principle which is software entities like in my example right now are classes between the two I mentioned before, they should be open for development but closed for modification. A good integral part of SOLID is coding to an interface. This is what I did for this source code. The interface CarService allows for the implementation of methods inside it in the CarServiceImpl class. In this case, such methods names are create, findAll, findById, update, and deleteCarById. Doing this would allow no need to modify the existing implemented logic of existing methods in the interface so that this allow if in the future there are other types of cars such as other types not just a general term called Cars by not just keep on modifying an existing class that has the methods and the logic everytime there are new types introduced but in this case, implementing the interface and adding the logic on that class that implement the interface would not violate the OCP principle. This is the same case for Product as they are divided to ProductService interface and also ProduceServiceImpl class that implements the ProductService interface. The fourth principle which is Interface Segregation Principle that says that clients should not depend on methods they do not use so that interfaces should has specific usage without implementing unnecessary methods. On CarService interface and ProductService interface, these do not violate the ISP principle assuming the clients does not need to only read the data. If they do, then splitting the interfaces to a much smaller one where each of them focusing on being able to be able to do certain specific features on the project. Lastly, the DIP principle since the meaning of this principle is that high-level modules should not rely on low-level modules directly, instead both should depend on abstraction, is true already for this source code project. For example, the two interfaces previously mentioned are the abstraction so that there are no high-level modules that relies on low-level modules or concretions but instead abstractions for enhancing flexibility of the project.
2. The advantages and the examples has been mentioned in question 1 answers. But overall, such advantages are increased maintainability, flexibility, scalability, reduced coupling, and enhanced testability.
3. The disadvantages itself would be the opposite effect of the advantages in question number 2. Such examples harder source code to understand by other developers, an infinite growth in certain classes that should not happened in the first place making complexity increase, and a more testability problems due to the codebase not adhering to SOLID principles. 
## Module 4
### Reflection answers:
1. By using this TDD flow, some unit tests were created but did not pass initially. Although, TDD is an iterative process so that this stuff is bound to happend in due process. From these failed tests and failures, the code implementation and the tests could be improver or fixed in an interative manner. The next time I make more tests, it is important to ensure that the test cases cover all possible scenarios. If the test had failed, it is very important to modify the implementation or even the test itself if some logic error was accidentally applied. There are a lot of unit tests that could be made but ones that covers all conditions such as edge cases would most likely help on increasing the code quality of my code base. 
2. The F.I.R.S.T principle meaning the first one being fast when the tests execute quickly. The tests that I made were very fast. Isolated/Independent, the tests that I made did not depend on each other. Repeatable, the tests did produce consistent result. Self-Validating, the tests I made returned boolean output because of using assertions. Timely, tests was written before the code itself. Adhering to this principle could make the next tests I created more detailed or comprehensive. In turn this could lead to a more effective and efficient TDD flow being applied to this project.   