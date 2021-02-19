import Foundation

extension String {
    public static func readInput(input: String) -> String {
        let path = Bundle.main.path(forResource: input, ofType: "txt")
        let contentData = FileManager.default.contents(atPath: path!)
        let content = String(data: contentData!, encoding: String.Encoding.utf8)
        return content!
    }
}
