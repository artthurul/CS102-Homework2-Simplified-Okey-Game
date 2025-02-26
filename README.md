# CS102 Simplified Okey Game Project.

## Team Members
- **[Ertuğrul Eren Durak](https://github.com/artthurul)** (`artthurul`)
- **[Ahmet Furkan Morkoç](https://github.com/furkanmm)** (`furkanmm`)
- **[Tuğrul Mert Orhan](https://github.com/PhoenixMert)** (`PhoenixMert`)
- **[Musa Eren Üçdal](https://github.com/Tuskamu)** (`Tuskamu`)

## Project Overview
This repository contains the source code for the **Simplified Okey Game** as part of the CS102 – Algorithms and Programming II assignment. The project implements a simplified, console-based version of the traditional Okey game, adapted with modified rules for ease of development and play. In this game, a human player competes against three computer opponents. The implementation includes features such as sorted tile management, computer strategies for drawing/discarding, and an optional debug mode to view computer players’ hands.

## Features
- **Console-Based Gameplay:**  
  - A user-friendly menu-driven interface for playing the game in the console.
- **Simplified Game Rules:**  
  - 112 tiles total (4 copies each of 4 colors and 7 numbers).  
  - No jokers or false joker tiles.  
  - Valid chains are formed by different colored tiles of the same number.
- **Player Mechanics:**  
  - Human player starts with 15 tiles; computer players start with 14 tiles.  
  - Efficient, sorted insertion of new tiles to maintain order.
- **Computer Strategy:**  
  - Algorithms to decide whether to draw from the discard pile or tile stack.  
  - Smart discarding based on duplicates and chain potential.
- **Debug Options:**  
  - An open-hand mode to reveal computer players’ tiles for testing purposes.
- **Collaboration & Documentation:**  
  - A well-organized repository structure with contribution guidelines and individual reflection files.
 
    
## Collaboration & Workflow
1. **Repository Setup:**  
   - One team member (Ertugrul, in that case) creates the GitHub repository and adds the others as collaborators.
2. **Branching Strategy:**  
   - Use descriptive feature branches (e.g., `feature/draw-discard-logic`, `feature/tile-insertion`).
3. **Commit & Pull Request Process:**  
   - Commit frequently with clear, descriptive messages.
   - Use pull requests for merging changes; at least one team member must review each PR.
4. **Issue Tracking:**  
   - Utilize GitHub Issues to manage tasks, bugs, and feature requests.
5. **Final Submission:**  
   - Ensure the repository includes an updated `Members.txt`, complete source code, and individual reflections.
   - One designated member will upload the consolidated solution to Moodle.

## Demonstration
The final working application, along with the commit history and supporting documentation, will be presented to the TA/Tutor during the lab session.

## License
This project is for educational purposes in **Bilkent University** and follows the guidelines set by the course instructors.
