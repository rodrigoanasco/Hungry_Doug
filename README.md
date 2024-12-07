# CMPT276F24_group14
# Hungry Doug

Hello, we are Group 14, and the game we are presenting is **Hungry Doug**. In this 2D arcade-style game, you control Doug, a hungry dog navigating through various maze-like maps. The objective is to collect all regular treats while avoiding enemies and punishments. With different environments and challenges, the player must guide Doug to the exit without losing all his food. Enjoy the adventure! Video demo link - https://drive.google.com/file/d/1YukljggPfGbU7JRUE2bMmqhItFPBFToJ/view?usp=sharing

## **Requirements**

Before you start, make sure you have the following installed on your system:
- [Java 17+](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Apache Maven 3.8+](https://maven.apache.org/install.html)
- A terminal or IDE (e.g., IntelliJ IDEA, Eclipse, or VSCode)

---

## **Building the Project**

1. Clone the repository to your local machine:
   ```bash
   git clone git@github.sfu.ca:hsa237/CMPT276F24_group14.git
  
2. Build the project using Maven:
   ```bash
   mvn clean install
  This will make maven download dependencies, compile the code, and package it into a JAR file
   
## **Running the Game**
1. Navigate to the demo directory
   ```bash
   cd target
   
2. Run the game:
   ```bash
   mvn clean compile exec:java
   
Make sure to be in the demo directory, if not the console will indicate problems


## **Testing the Game**
The game includes automated tests written with JUnit and Mockito. To run the tests:
1. Execute the following Maven Command:
   ```bash
   mvn test
   
2. Check the test reports in the terminal to ensure all the test pass


## **Running the Javadocs**
The game's functions are also documented in javadocs. To run the javadoc:

0. Make sure you are in the demo directory:
   ```bash
   cd demo
   
1. Execute the following Maven Command:
   ```bash
   mvn javadoc:javadoc
   
2. Enter to the following directory:
   ```bash
   cd target/site/apidocs

3. According to your operating system, enter the following command:
   ```bash
   open index.html  # for macOS
   start index.html # for Windows
   xdg-open index.html # for Linux


## **Running the JAR FILE**
0. Make sure you are in the demo directory:
   ```bash
   cd demo
   
1. Execute the following Maven Command:
   ```bash
   mvn package
   
2. Enter to the following directory:
   ```bash
   cd target

3. By default, the file that we generated is caled 'game-1.0-SNAPSHOT.jar':
   ```bash
   java -jar game-1.0-SNAPSHOT.jar

The game should execute without any problem