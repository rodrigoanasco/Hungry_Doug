# Hungry Doug 🐶🎮
A 2D arcade-style game built in **Java** for SFU’s CMPT 276. You control Doug, a hungry dog navigating maze-like maps, collecting treats, avoiding enemies, and reaching the exit.

## Features
- Multi-threaded **game engine** for smooth rendering and reduced race conditions  
- **AI-driven enemy pathfinding** with bounding-box collision detection  
- Custom UI screens: Main Menu, Game Over, Victory, with background music & sound effects  
- **JUnit & Mockito** test suite with ~90% coverage (collision logic, item spawning, input handling)  
- Modular OOP architecture (Handler class, Singleton player, grid-based asset management)

🎥 Video Demo: https://drive.google.com/file/d/1YukljggPfGbU7JRUE2bMmqhItFPBFToJ/view?usp=sharing

---

## Tech Stack
- **Languages**: Java (JDK 17+)  
- **Build Tools**: Apache Maven  
- **Testing**: JUnit, Mockito  
- **Graphics**: Java Canvas & Graphics Library  

---

## Build & Run
```bash
# Clone repository
git clone git@github.sfu.ca:hsa237/CMPT276F24_group14.git
cd CMPT276F24_group14

# Build & run
mvn clean compile exec:java
```

To run the JAR:
```bash
mvn package
java -jar target/game-1.0-SNAPSHOT.jar
```

---

## Documentation
Generate and view Javadocs:
```bash
mvn javadoc:javadoc
open target/site/apidocs/index.html   # macOS
start target/site/apidocs/index.html  # Windows
xdg-open target/site/apidocs/index.html # Linux
```

---

This project was developed collaboratively, but my key contributions included:  
- Implementing **AI-driven enemy pathfinding**  
- Designing **custom UI screens** with sound integration  
- Writing **JUnit & Mockito tests** achieving ~90% coverage  
- Applying **OOP patterns** for maintainability  

