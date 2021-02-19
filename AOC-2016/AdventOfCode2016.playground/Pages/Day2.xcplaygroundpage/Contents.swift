import Foundation

enum Turn {
    case right
    case left
    case up
    case down
    
    init?(character: Character) {
        switch character {
        case "L":
            self = .left
        case "R":
            self = .right
        case "U":
            self = .up
        case "D":
            self = .down
        default:
            return nil
        }
    }
}

struct Position {
    var xPos: Int
    var yPos: Int
    let minIndex = 0
    let maxIndex: Int
}

struct KeyPad {
    var interface: [[Any]]
    var currentPosition: Position
    var solution = ""
    
    init(interface: [[Any]], currentPosition: Position) {
        self.interface = interface
        self.currentPosition = currentPosition
    }
    
    mutating func calculateNextNumberFrom(instructions: String) {
        for character in instructions.characters {
            moveTo(direction:Turn.init(character: character)!)
        }
        solution.append(String(describing: interface[currentPosition.yPos][currentPosition.xPos]))
    }
    
    mutating func moveTo(direction: Turn) {
        switch direction {
        case .down:
            if (currentPosition.yPos < currentPosition.maxIndex
                && String(describing: interface[currentPosition.yPos+1][currentPosition.xPos ]) != " ") {
                currentPosition.yPos = currentPosition.yPos + 1
            }
        case .up:
            if (currentPosition.yPos > currentPosition.minIndex
                && String(describing: interface[currentPosition.yPos-1][currentPosition.xPos]) != " ") {
                currentPosition.yPos = currentPosition.yPos - 1
            }
        case .left:
            if (currentPosition.xPos > currentPosition.minIndex
                && String(describing: interface[currentPosition.yPos][currentPosition.xPos - 1]) != " ") {
                currentPosition.xPos = currentPosition.xPos - 1
            }
        case .right:
            if (currentPosition.xPos < currentPosition.maxIndex
                && String(describing: interface[currentPosition.yPos][currentPosition.xPos + 1]) != " ") {
                currentPosition.xPos = currentPosition.xPos + 1
            }
        }
    }
}

let input = Helper.readInput(input: "input_day_2")
var keyPad = KeyPad(interface: [[1, 2, 3], [4, 5, 6], [7, 8, 9]], currentPosition: Position(xPos: 1,yPos: 1, maxIndex: 2))
var keyPad2 = KeyPad(interface: [[" ", " ", 1, " ", " "], [" ", 2, 3, 4, " "], [5, 6, 7, 8, 9], [" ", "A", "B", "C", " "], [" ", " ", "D", " ", " "]], currentPosition: Position(xPos: 0,yPos: 3, maxIndex: 4))

for line in input.components(separatedBy: "\n") {
    if (line.characters.count > 0) {
        keyPad.calculateNextNumberFrom(instructions: line)
        keyPad2.calculateNextNumberFrom(instructions: line)
    }
}

/*
 "In order to improve security," the document you find says, "bathroom codes will no longer be written down. Instead, please memorize and follow the procedure below to access the bathrooms."
 
 The document goes on to explain that each button to be pressed can be found by starting on the previous button and moving to adjacent buttons on the keypad: U moves up, D moves down, L moves left, and R moves right. Each line of instructions corresponds to one button, starting at the previous button (or, for the first line, the "5" button); press whatever button you're on at the end of each line. If a move doesn't lead to a button, ignore it.
 
 You can't hold it much longer, so you decide to figure out the code as you walk to the bathroom. You picture a keypad like this:
 
 1 2 3
 4 5 6
 7 8 9
 */
print("First Solution is \(keyPad.solution)")

/*
      1
    2 3 4
  5 6 7 8 9
    A B C
      D
 */
print("Second Solution is \(keyPad2.solution)")

