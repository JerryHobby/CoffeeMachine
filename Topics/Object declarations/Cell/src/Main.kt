class Cell {
    var width = BaseProperties.width
    var height = BaseProperties.height
    
    object BaseProperties {
        // Avoid magic number warning
        const val DEFAULTWIDTH = 10
        const val DEFAULTHEIGHT = 10

        var width = DEFAULTWIDTH
        var height = DEFAULTHEIGHT
    }
}
