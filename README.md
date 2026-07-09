# Three Kingdoms Data Structures

An interactive game-based application inspired by the Three Kingdoms era of Chinese history. This Java project demonstrates advanced data structures and algorithms through engaging gameplay mechanics including hierarchy management, maze solving, fortress defense simulations, and strategic team formation.

## 🎮 Overview

**Three Kingdoms DS** is an educational project that combines historical narrative with computer science concepts. Players interact with historical generals and engage in various challenges that showcase practical applications of data structures and algorithms like trees, graphs, DFS, A* pathfinding, and more.

## ✨ Features

### 1. **General Hierarchy System**
- Manage military hierarchy and chain of command
- Tree-based data structure for organization
- Visual GUI representation of command structure
- Add and traverse hierarchical relationships

### 2. **Fortress Defense Simulation**
- Enemy attack simulation on fortresses
- Strategic positioning and defense mechanics
- Multiple simulation variants for different scenarios
- Real-time fortress status tracking

### 3. **Maze Escape Challenge**
- A* pathfinding algorithm for intelligent maze navigation
- Optimal path calculation
- Interactive maze solver
- Escape from fortress maze scenarios

### 4. **Team Formation System**
- Recruit and organize military units
- Partition-based team strategy (Battleship clusters)
- DFS-based cluster detection
- Strategic troop arrangement

### 5. **Additional Mechanics**
- **Food Harvesting**: Resource management simulation
- **Boat Navigation**: Fleet management and positioning
- **Encryption System**: Secure communication between kingdoms
- **Character Selection**: Play as famous Three Kingdoms generals

## 📁 Project Structure

```
ThreeKingdomsDS/
├── README.md                                    # This file
├── ThreeKingdomsDS.iml                         # IntelliJ project configuration
├── .gitignore                                   # Git ignore rules
│
└── src/
    ├── Main Classes
    │   ├── GeneralHierarchy.java               # Tree-based hierarchy management
    │   ├── HierarchyGUI.java                   # GUI for hierarchy visualization
    │   ├── MazeEscapeAstar.java                # A* pathfinding algorithm
    │   ├── PathFinder.java                     # Path calculation utilities
    │   ├── BattleshipClustersDFS.java          # DFS-based cluster detection
    │   ├── EnemyAttackFortressSimulation.java  # Basic fortress defense
    │   ├── EnemyAttackFortressSimulationXtra.java # Extended simulation features
    │   ├── EnemyAttackController.java          # GUI controller for fortress mode
    │   ├── FoodHarvesting.java                 # Resource management
    │   ├── Boats.java                          # Fleet management
    │   ├── Encryption.java                     # Message encryption/decryption
    │   ├── FormTeam.java                       # Team formation logic
    │   ├── General.java                        # General character class
    │   ├── KINGDOM.java                        # Kingdom constants
    │   ├── QuestionThreeMain.java              # Main game entry point
    │   ├── Pair.java                           # Utility pair class
    │   ├── Strawman.java                       # Utility/placeholder class
    │   └── WuKingdomNode.java                  # Wu kingdom node representation
    │
    ├── GUI & FXML
    │   ├── EnemyAttack-view.fxml              # Fortress battle UI layout
    │   ├── EnemyAttackFortressSimulationGUI.java # GUI launcher
    │   ├── HierarchyGUI.java                   # Hierarchy visualization interface
    │
    ├── Legacy/Backup
    │   ├── BattleshipClustersOLD.java         # Legacy cluster detection
    │   └── JobDateDetector.java               # Utility class (possibly legacy)
    │
    ├── Images
    │   ├── Fortress Map.jpg                    # Fortress layout visualization
    │   │
    │   └── Characters/                         # Character portraits
    │       ├── daQiao.png                      # Da Qiao character
    │       ├── ganNing.png                     # Gan Ning character
    │       ├── huangGai.png                    # Huang Gai character
    │       ├── luMeng.png                      # Lu Meng character
    │       ├── luSu.png                        # Lu Su character
    │       ├── sunWu.png                       # Sun Wu character
    │       ├── taiShiCi.png                    # Tai Shi Ci character
    │       ├── xiaoQiao.png                    # Xiao Qiao character
    │       ├── xuSheng.png                     # Xu Sheng character
    │       ├── zhangZhao.png                   # Zhang Zhao character
    │       ├── zhouTai.png                     # Zhou Tai character
    │       ├── zhouYu.png                      # Zhou Yu character
    │       ├── zhuGeJin.png                    # Zhu Ge Jin character
    │       └── mainMenu.png                    # Main menu background
```

## 🚀 Getting Started

### Prerequisites

- Java 8 or higher
- JavaFX (for GUI features)
- IntelliJ IDEA or any Java IDE (optional)

### Installation

1. Clone the repository:
```bash
git clone https://github.com/bwubbu/ThreeKingdomsDS.git
cd ThreeKingdomsDS
```

2. Compile the Java source files:
```bash
javac src/*.java
```

### Running the Application

**Launch the main game:**
```bash
java -cp src QuestionThreeMain
```

**Specific Game Modes:**

**Hierarchy Management:**
```bash
java -cp src HierarchyGUI
```

**Maze Escape:**
```bash
java -cp src MazeEscapeAstar
```

**Fortress Defense:**
```bash
java -cp src EnemyAttackFortressSimulationGUI
```

**Team Formation:**
```bash
java -cp src FormTeam
```

## 📚 Key Algorithms & Data Structures

### Tree Structure - General Hierarchy
Uses a tree data structure to represent military command hierarchy. Supports:
- Node insertion and traversal
- Ancestor/descendant queries
- Level-order traversal for visualization

### A* Pathfinding - Maze Escape
Implements the A* algorithm for optimal pathfinding:
- Heuristic-based search
- Manhattan distance calculation
- Efficient maze navigation

### Depth-First Search (DFS) - Battleship Clusters
Detects connected components using DFS:
- Cluster identification
- Strategic troop grouping
- Territory analysis

### Resource Management - Food Harvesting
Simulates resource allocation and consumption:
- Harvest scheduling
- Storage management
- Supply chain optimization

## 🎭 Characters

The game features famous generals from the Three Kingdoms period:

- **Da Qiao** - Strategy specialist
- **Gan Ning** - Naval commander
- **Huang Gai** - Defense expert
- **Lu Meng** - Tactical genius
- **Lu Su** - Diplomatic leader
- **Sun Wu** - Military strategist
- **Tai Shi Ci** - Combat master
- **Xiao Qiao** - Support specialist
- **Xu Sheng** - Infantry commander
- **Zhang Zhao** - Political advisor
- **Zhou Tai** - Guard captain
- **Zhou Yu** - Grand strategist
- **Zhu Ge Jin** - Logistics officer

## 🔐 Encryption System

Includes a message encryption/decryption system for secure kingdom communications:
- Character-based encryption
- Kingdom-specific cipher algorithms
- Real-time encoding/decoding

## 📈 Technologies Used

- **Language**: Java 100%
- **GUI Framework**: JavaFX
- **Markup**: FXML for UI layouts
- **Design Patterns**: MVC (Model-View-Controller)
- **Algorithms**: A*, DFS, Tree traversal
- **Data Structures**: Trees, Graphs, Queues, Stacks

## 🎓 Learning Objectives

This project demonstrates:
- Object-oriented programming principles
- Advanced data structure implementation
- Algorithm design and optimization
- GUI development with JavaFX
- Game mechanics and simulation design
- File I/O and resource management

## 🎨 Assets

The project includes high-quality character artwork and fortress maps to enhance the gaming experience and historical immersion.

## 🛠️ Development Notes

- Project uses IntelliJ IDEA configuration (`.idea` directory)
- Legacy implementations preserved for reference
- Modular design allows easy feature extensions
- All core algorithms are self-contained in dedicated classes

## 🤝 Contributing

Feel free to fork this project and submit pull requests for improvements, new features, or optimization!

## 📝 License

Open source - available for educational and personal use.

## 📧 Contact

For questions or suggestions about the Three Kingdoms DS project, feel free to open an issue in the repository.

---

**Status**: Educational Project  
**Language**: Java (100%)  
**Last Updated**: June 2023
