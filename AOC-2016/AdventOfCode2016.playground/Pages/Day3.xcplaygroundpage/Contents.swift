import Foundation

struct Triangle {
    var aSide, bSide, cSide :Int?
    
    func isValidTriangle() -> Bool {
        if let a = aSide, let b = bSide, let c = cSide {
            if (a + b <= c) { return false }
            if (a + c <= b) { return false }
            if (b + c <= a) { return false }
            return true
        }
        return false
    }
}

let input = Helper.readInput(input: "input_day_3")

var triangleCount = 0
var triangleMatrix = [[Int?]]()
for (index,line) in input.components(separatedBy: "\n").enumerated() {
    var sides = line.components(separatedBy: " ")
    if (sides.count == 3) {
        let triangle = Triangle(aSide: Int(sides[0]), bSide: Int(sides[1]), cSide: Int  (sides[2]))
        triangleCount = triangleCount + (triangle.isValidTriangle() ? 1 : 0)
        
        triangleMatrix.append([triangle.aSide, triangle.bSide, triangle.cSide])
    }
}

var triangleCountVertical = 0
for i in stride(from: 0, to: triangleMatrix.count, by: 3) {
    for j in 0..<3 {
        let triangle = Triangle(aSide: Int(triangleMatrix[i][j]!), bSide: Int(triangleMatrix[i+1][
            j]!), cSide: Int(triangleMatrix[i+2][j]!))
        triangleCountVertical = triangleCountVertical + (triangle.isValidTriangle() ? 1 : 0)
    }
}


/*
 The design document gives the side lengths of each triangle it describes, but... 5 10 25? Some of these aren't triangles. You can't help but mark the impossible ones.
 
 In a valid triangle, the sum of any two sides must be larger than the remaining side. For example, the "triangle" given above is impossible, because 5 + 10 is not larger than 25.
 
 In your puzzle input, how many of the listed triangles are possible?
*/
print("Number of valid triangles is: \(triangleCount)")

/*
 Now that you've helpfully marked up their design documents, it occurs to you that triangles are specified in groups of three vertically. Each set of three numbers in a column specifies a triangle. Rows are unrelated.
 
 For example, given the following specification, numbers with the same hundreds digit would be part of the same triangle:
 
    101 301 501
    102 302 502
    103 303 503
    201 401 601
    202 402 602
    203 403 603
 
 In your puzzle input, and instead reading by columns, how many of the listed triangles are possible?
 */
print("Number of valid vertical triangles is: \(triangleCountVertical)")
