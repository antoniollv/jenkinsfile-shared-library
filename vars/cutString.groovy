
def call(String string, String cutChar, boolean right = false) {
    def posCutChar = string.indexOf(cutChar)
    if (right) {
        string  = string.substring(posCutChar + 1)
    } else {
        string  = string.getAt(0..(posCutChar -1))
    }
    return string
}