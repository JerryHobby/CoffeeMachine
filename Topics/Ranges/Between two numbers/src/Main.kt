fun main() {
    // write your code here
    var a :Int = readln().toInt()
    var b :Int = readln().toInt()
    var c :Int = readln().toInt()

    if(a in b .. c)
        println("true")
    else
        println("false")
}