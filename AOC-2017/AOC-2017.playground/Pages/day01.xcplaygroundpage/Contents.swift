import UIKit
import XCTest

func solveFirstPart(input: String) -> Int {
    let digits = input.flatMap{Int(String($0))}
    var sum = 0
    
    if let first = digits.first, let last = digits.last, first == last {
        sum = first
    }
    
    digits.reduce(0) {
        if $0 == $1 { sum += $0 }
        return $1
    }
    
    return sum
}

func solveSecondPart(input: String) -> Int {
    let digits = input.flatMap{Int(String($0))}
    
    let length = digits.count
    let pushedArray = digits[length/2...length-1] + digits[0...length/2-1]
    
    return zip(digits, pushedArray).reduce(0) {
        if $1.0 == $1.1 {
            return $0 + $1.0
        } else {
            return $0
        }
    }
}

let testCases = [
    ("1122",3),
    ("1111",4),
    ("1234",0),
    ("91212129",9)
]

print("----DAY01/01 TEST CASES----")

for (input, result) in testCases {
    print("----TESTING \(input) against \(result)-----")
    XCTAssertEqual(solveFirstPart(input: input), result)
    print("----SUCCESS-----")
}

print("----DAY01 PART1 RESULT----")
let input = String.readInput(input: "day01-input")
print(solveFirstPart(input: input))

let testCases2 = [
    ("1212",6),
    ("1221",0),
    ("123425",4),
    ("123123",12),
    ("12131415",4)
]

print("----DAY01/02 TEST CASES----")

for (input, result) in testCases2 {
    print("----TESTING \(input) against \(result)-----")
    XCTAssertEqual(solveSecondPart(input: input), result)
    print("----SUCCESS-----")
}
print("----DAY01 PART2 RESULT----")
print(solveSecondPart(input: input))
