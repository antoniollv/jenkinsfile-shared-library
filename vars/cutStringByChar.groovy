/**
*  Método que devuelve un Substring a partir del String pasado como primer parámetro,
* tomando como carácter de corte la primera coincidencia del carácter pasado como segundo parámetro y sin incluir a este
*
*  @Param string String a cortar.
*  @Param cutChar String carácter de corte, no se incluirá en el Substring devuelto
*  @Param right boolean, determina si el Substring devuelto será la parte derecha o la izquierda del string a partir del carácter de corte
*
*/
def call(String string, String cutChar, boolean right = false) {
    def posCutChar = string.indexOf(cutChar)
    if (right) {
        string  = string.substring(posCutChar + 1)
    } else {
        string  = string.getAt(0..(posCutChar -1))
    }
    return string
}