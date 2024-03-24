</a><img src="https://github.com/devicons/devicon/blob/master/icons/junit/junit-original.svg" width="40" height="40" /></a><img src="https://github.com/devicons/devicon/blob/master/icons/java/java-original.svg" width="40" height="40" />

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
#### Run Specific Test Method (Linux)
```
mvn -Dtest=testFileName#testMethodName test
```
For example, to run testIsBusy() located in CalendarTest.java, use the command below.

```
mvn -Dtest=CalendarTest#testIsBusy test
```
Windows users i'm sorry (:
