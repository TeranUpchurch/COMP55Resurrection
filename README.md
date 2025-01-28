# Readme
## In-Game Visual Debug Mode
### Overview
Currently, when developers or users add or modify features in the game, such as a player or enemy unit, the effects of those changes are not always immediately clear during gameplay. To address this, I plan to implement an in-game visual debugging mode that provides real-time feedback on key unit attributes and interactions.

This feature will include:

- Health bars for units.
- Range indicators for towers and enemies.
- Hitbox visualizations for units.
    
This debugging mode will be controlled by a JSON configuration file. The file will include a boolean to enable or disable the visuals, making it easy for developers to toggle the mode without modifying code directly.
 // Toggle debug mode
    method toggleDebugMode():
        debugMode = NOT debugMode  // Toggle the debug mode
### Pseudocode

// visualDebug Class

class visualDebug {

    // Class Attributes
    
    boolean debugMode = false  // False = off, True = on
    
    Map<Unit, VisualElement> unitDisplay
    
    Map<Robot, VisualElement> robotDisplay
    
    Map<Projectile, VisualElement> projectileDisplay 
    
    // Constructor
    
    method initialize():
    
        unitDisplay = new Map<Unit, VisualElement>()
        
        robotDisplay = new Map<Robot, VisualElement>()
        
        projectileDisplay = new Map<Projectile, VisualElement>()
        
    // Toggle debug mode

    method toggleDebugMode():
    
        debugMode = NOT debugMode
        
    // Render information for each unit
    
    method renderUnitInfo(unit: Unit):
    
        if debugMode:
        
            // Render health, range, hitbox
            
            unitDisplay.put(unit, createVisualElement(unit))
            
    // Render information for each robot
    
    method renderRobotInfo(robot: Robot):
    
        if debugMode:
        
            // Render health, speed, hitbox
            
            robotDisplay.put(robot, createVisualElement(robot))
            
    // Render information for each projectile
    
    method renderProjectileInfo(projectile: Projectile):

        if debugMode:
        
            // Render speed, damage
            
            projectileDisplay.put(projectile, createVisualElement(projectile))
            
    // Remove all debug visuals from the screen
    
    method clearDebugInfo():
    
        unitDisplay.clear()
        
        robotDisplay.clear()
        
        projectileDisplay.clear()
        
    // Helper method to create a visual element for a unit, robot, or projectile
    
    method createVisualElement(element: Object) -> VisualElement:
    
        // Generate a visual element based on the object type
        
        return new VisualElement(element)
        
}
