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

2. Yes, because like for example the build.yml workflow that triggers on every push and pull request to ensure that every change are integrated into the main branch regularly. This is to ensure that tests run to detect issues can be done early in the development process. This is for Continuous Integration (CI), on the other hand Continuous Deployment (CD) has already been fulfilled by using PaaS which is Koyeb for free by taking use of Dockerfile. So in conclusion, yes I do think that  my current implementation has met the definition of Continuous Integration and Continuous Deployment and that even I used Sonar Cloud as a code quality analysis tool for CI. 
