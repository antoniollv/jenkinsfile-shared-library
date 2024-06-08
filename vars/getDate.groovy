
import static java.util.Calendar.*

void call() {

    def date = new Date()
    def formattedDate = date.format("dd/MM/yyy HH:mm:ss")
    println("Date : ${formattedDate}")
    return formattedDate
}