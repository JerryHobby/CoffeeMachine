class Block(val color: String) {

    object BlockProperties {
        var length: Int = 0
        var width: Int = 0

        public fun blocksInBox(boxLength: Int, boxWidth: Int) :Int {
            return (boxWidth / width) * (boxLength / length)
        }
    }
}

fun mymain() {
    val box1 = Block("red")
    val box2 = Block("green")

    Block.BlockProperties.width = 4
    Block.BlockProperties.length = 6

    println("${box1.color} ${Block.BlockProperties.blocksInBox(10,10)}")
    println("${box2.color} ${Block.BlockProperties.blocksInBox(100,100)}")
}