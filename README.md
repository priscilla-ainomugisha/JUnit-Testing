## Meeting Planner Unit Testing.
The Meeting Planner Java Application is a software solution designed to facilitate the scheduling and management of meetings. This project employs the JUnit testing framework for ensuring the reliability and correctness of its functionalities.

#### Project Installation
```
git clone https://github.com/MartinKalema/JUnit-Testing
```

#### Install Maven (Linux)
```
sudo apt install maven 
```
#### Compile Using Maven (Linux)
```
mvn clean compile
```

#### Run All Tests (Linux)
```
mvn test
```
#### Run particular test function/method
```
mvn -Dtest=test_filename#method_name test
```
For example, to run testIsBusy() located in CalendarTest.java, use the command below.

```
mvn -Dtest=CalendarTest#testIsBusy test
```
