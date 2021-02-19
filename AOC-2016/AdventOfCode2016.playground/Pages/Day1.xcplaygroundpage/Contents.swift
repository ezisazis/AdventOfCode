/*
    The Document indicates that you should start at the given coordinates (where you just landed) and face North. Then, follow the provided sequence: either turn left (L) or right (R) 90 degrees, then walk forward the given number of blocks, ending at a new intersection.
 
    There's no time to follow such ridiculous instructions on foot, though, so you take a moment and work out the destination. Given that you can only walk on the street grid of the city, how far is the shortest path to the destination?
 
    For example:
 
    Following R2, L3 leaves you 2 blocks East and 3 blocks North, or 5 blocks away.
    R2, R2, R2 leaves you 2 blocks due South of your starting position, which is 2 blocks away.
    R5, L5, R5, R3 leaves you 12 blocks away.
*/
import Foundation

enum Direction {
    case north
    case south
    case east
    case west

    mutating func turnTo(direction: Turn) {
        switch (self, direction) {
        case (.north, .right):
            self = .east
        case (.north, .left):
            self = .west
        case (.south, .right):
            self = .west
        case (.south, .left):
            self = .east
        case (.east, .right):
            self = .south
        case (.east, .left):
            self = .north
        case (.west, .right):
            self = .north
        case (.west, .left):
            self = .south
        }
    }

}

enum Turn {
    case right
    case left
    
    init?(character: Character) {
        switch character {
        case "L":
            self = .left
        case "R":
            self = .right
        default:
            return nil
        }
    }
}

struct Coordinate {
    var x: Int
    var y: Int
}

struct Location {
    
    var direction: Direction
    var currentCoordinate: Coordinate
    var originalCooridnate: Coordinate
    var visitedCoordinates = [Coordinate]()
    
    init(direction: Direction, x: Int, y: Int) {
        self.direction = direction
        self.currentCoordinate = Coordinate(x: x,y: y)
        self.originalCooridnate = Coordinate(x: x,y: y)
    }
    
    mutating func move(withTurn: Turn, steps: Int) {
        direction.turnTo(direction: withTurn)
        makeSteps(count: steps)
    }
    
    mutating func makeSteps(count: Int) {
        for _ in 0..<count {
            switch direction {
                case .north:
                    currentCoordinate.y += 1
                case .south:
                    currentCoordinate.y -= 1
                case .east:
                    currentCoordinate.x += 1
                case .west:
                    currentCoordinate.x -= 1
        }
            visitedCoordinates.append(currentCoordinate)
        }
    }
    
    func distanceFromOriginalPoint() -> Int {
        return abs(currentCoordinate.x-originalCooridnate.x) + abs(currentCoordinate.y-originalCooridnate.y)
    }
    
    private func distanceBetweenOriginalPointAndGiven(coordinate: Coordinate) -> Int {
        return abs(coordinate.x-originalCooridnate.x) + abs(coordinate.y-originalCooridnate.y)
    }
    
    func distanceFromTheFirstRepeatedPlace() -> Int {
        if (visitedCoordinates.count > 1) {
            for i in 0..<(visitedCoordinates.count-1) {
                for j in i+1..<visitedCoordinates.count {
                    if (visitedCoordinates[i].x == visitedCoordinates[j].x
                        && visitedCoordinates[i].y == visitedCoordinates[j].y) {
                        return distanceBetweenOriginalPointAndGiven(coordinate: visitedCoordinates[i])
                    }
                }
            }
        }
        return 0
    }
}

var location = Location(direction: .north, x: 0, y: 0)
let input = Helper.readInput(input: "input_day_1")

for command in input.components(separatedBy: ",") {
    let trimmedString = command.trimmingCharacters(in: .whitespacesAndNewlines)
    let currentTurn = Turn(character: trimmedString[trimmedString.startIndex])!
    
    let indexMove = trimmedString.index(trimmedString.startIndex, offsetBy: 1)
    let currentSteps = Int(trimmedString.substring(from: indexMove))!
    
    location.move(withTurn: currentTurn, steps: currentSteps)
}

print("Distance from starting point: \(location.distanceFromOriginalPoint())")

/*
    Then, you notice the instructions continue on the back of the Recruiting Document. Easter Bunny HQ is actually at the first location you visit twice.
 
    For example, if your instructions are R8, R4, R4, R8, the first location you visit twice is 4 blocks away, due East.
 
    How many blocks away is the first location you visit twice?
*/
print("Distance between real HQ and starting point: \(location.distanceFromTheFirstRepeatedPlace())")