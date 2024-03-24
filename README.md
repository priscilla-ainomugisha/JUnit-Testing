## Unit Testing for the Meeting Planner Java Application

####Project Installation
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
For example, to run testIsBusy() located in CalendarTest.java. I will use the command below.
```mvn -Dtest=CalendarTest#testIsBusy test```
