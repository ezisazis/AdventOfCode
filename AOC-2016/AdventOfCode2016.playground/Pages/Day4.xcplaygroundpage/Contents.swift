import Foundation

struct SecretCode {
    var encryptedNames: [String]
    var sectorId: Int
    var checkSum: String
    
    init(inputLine: String) {
        var inputs = inputLine.components(separatedBy: "-")
        
        let lastPart = inputs.last
        let checksumStart: Range<String.Index> = lastPart!.range(of: "[")!
        let checksumEnd: Range<String.Index> = lastPart!.range(of: "]")!
        
        inputs.remove(at: inputs.count - 1)
        self.encryptedNames = inputs
        self.sectorId = Int((lastPart?.substring(to: checksumStart.lowerBound))!)!
        self.checkSum = (lastPart?.substring(with: checksumStart.upperBound..<checksumEnd.lowerBound))!
    }
    
    /*
        Returns the sectors if it's a valid room
        either way it just returns 0
    */
    func validateSectorId() -> Int {
        return checkIfValidEncryptions() ? self.sectorId : 0
    }
    
    /* 
        Returns TRUE if the encryptions are matching to the rules
        either way it's gonna be FALSE
     
        TODO: this should be cleaned up...
    */
    private func checkIfValidEncryptions() -> Bool {
        let sorted = Array(collectOccurences()).sorted(by: {($0.1, $1.0) > ($1.1, $0.0)}).prefix(checkSum.characters.count)
        for char in checkSum.characters {
            var contains = false
            for element in sorted {
                if (element.key == char) {
                    contains = true
                }
            }
            if (contains == false) { return false }
        }
        return true
    }
    
    private func collectOccurences() -> [Character:Int] {
        var countToLetters = [Character:Int]()
        for name in encryptedNames {
            for character in name.characters {
                if let pair = countToLetters[character] {
                    countToLetters[character] = pair + 1
                } else {
                    countToLetters[character] = 1
                }
            }
        }
        return countToLetters
    }
    
    func decryptName() -> Int? {
        var objective = ""
        for name in encryptedNames {
            for char in name.characters {
                objective.append(shiftCharacter(character: char))
            }
            objective.append(" ")
        }
        if (objective == "northpole object storage ") {
            return sectorId
        }
        return nil
    }
    
    private func shiftCharacter(character: Character) -> Character {
        let characterString = String(character)
        let value = (UnicodeScalar(characterString)?.value)!
        let left = self.sectorId % 26
        let added = (Int(value) + left)
        let shiftedString = String(format: "%c", added > 122 ? added - (left == 0 ? 0 : 26) : added)
        return shiftedString.characters.first!
    }
}

let input = Helper.readInput(input: "input_day_4")
var finalCode = 0
for line in input.components(separatedBy: "\n") {
    if (line.characters.count > 0) {
        var secretCode =  SecretCode(inputLine: line)
        finalCode = finalCode + secretCode.validateSectorId()
        
        /*
         The room names are encrypted by a state-of-the-art shift cipher, which is nearly unbreakable without the right software. However, the information kiosk designers at Easter Bunny HQ were not expecting to deal with a master cryptographer like yourself.
         
         To decrypt a room name, rotate each letter forward through the alphabet a number of times equal to the room's sector ID. A becomes B, B becomes C, Z becomes A, and so on. Dashes become spaces.
         
         What is the sector ID of the room where North Pole objects are stored?
         */
        if let objective = secretCode.decryptName() {
            print("final shifted code is \(objective)")
        }
    }
}

/*
 
 Each room consists of an encrypted name (lowercase letters separated by dashes) followed by a dash, a sector ID, and a checksum in square brackets.
 
 A room is real (not a decoy) if the checksum is the five most common letters in the encrypted name, in order, with ties broken by alphabetization. For example:
 
    aaaaa-bbb-z-y-x-123[abxyz] is a real room because the most common letters are a (5), b (3), and then a tie between x, y, and z, which are listed alphabetically.
    a-b-c-d-e-f-g-h-987[abcde] is a real room because although the letters are all tied (1 of each), the first five are listed alphabetically.
    not-a-real-room-404[oarel] is a real room.
    totally-real-room-200[decoy] is not.
 
 Of the real rooms from the list above, the sum of their sector IDs is 1514.
 
 What is the sum of the sector IDs of the real rooms?
 
*/
