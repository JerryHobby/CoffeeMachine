package machine

// recipes allow for different recipes
data class Recipe(
    var name: String,
    var water: Int,
    var milk: Int,
    var beans: Int,
    var price: Int
)

// multiple machines manage different inventories of ingredients
// in this exercise we will only use one instance
class Machine {
    var water: Int = 0
    var milk: Int = 0
    var beans: Int = 0
    var cups: Int = 0
    var money: Int = 0

    // how many of a given recipe can we make
    private fun canMake(recipe: Recipe): Int {
        var maxWater = 0
        var maxMilk = 0
        var maxBeans = 0
        val maxCups: Int = cups
        var total: Int

        if (recipe.water > 0)
            maxWater = water / recipe.water

        if (recipe.milk > 0)
            maxMilk = milk / recipe.milk

        if (recipe.beans > 0)
            maxBeans = beans / recipe.beans

        // total = the least of all these
        // check it and reduce it for each ingredient
        total = maxCups

        // if we need this, do we have enough?
        if (recipe.water > 0 && total > maxWater)
            total = maxWater

        // if we need this, do we have enough?
        if (recipe.milk > 0 && total > maxMilk)
            total = maxMilk

        // if we need this, do we have enough?
        if (recipe.beans > 0 && total > maxBeans)
            total = maxBeans

        return total
    }

    fun status() {
        println("The coffee machine has:")
        println("$water ml of water")
        println("$milk ml of milk")
        println("$beans g of coffee beans")
        println("$cups disposable cups")
        println("\$$money of money")
    }

    fun buy(recipe: Recipe, quantity: Int) {
        // error check
        if (canMake(recipe) < quantity) {
            if (recipe.water * quantity > water)
                println("Sorry, not enough water!")
            if (recipe.milk * quantity > milk)
                println("Sorry, not enough milk!")
            if (recipe.beans * quantity > beans)
                println("Sorry, not enough coffee beans!")
            if (quantity > cups)
                println("Sorry, not enough disposable cups!")

        } else {
            water -= recipe.water * quantity
            milk -= recipe.milk * quantity
            beans -= recipe.beans * quantity
            cups -= quantity
            money += recipe.price * quantity
        }
    }
}


fun main() {
    // create a recipe for coffee
    val espressoRecipe = Recipe(
        name = "espresso",
        water = 250,
        milk = 0,
        beans = 16,
        price = 4
    )
    val latteRecipe = Recipe(
        name = "latte",
        water = 350,
        milk = 75,
        beans = 20,
        price = 7
    )
    val cappuccinoRecipe = Recipe(
        name = "cappuccino",
        water = 200,
        milk = 100,
        beans = 12,
        price = 6
    )

    // initialize the coffee machine
    val coffeeMachine = Machine()

    with(coffeeMachine)
    {
        water = 400
        milk = 540
        beans = 120
        cups = 9
        money = 550
    }

    var command = ""

    // top menu
    // buy, fill, take

    while (command != "exit") {
        print("Write action (buy, fill, take, remaining, exit): ")
        command = readln()

        if (command == "buy") {
            print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ")

            when (readln()) {
                "1" -> coffeeMachine.buy(espressoRecipe, 1)
                "2" -> coffeeMachine.buy(latteRecipe, 1)
                "3" -> coffeeMachine.buy(cappuccinoRecipe, 1)
            }
        }

        if (command == "fill") {
            with(coffeeMachine)
            {
                print("Write how many ml of water do you want to add: ")
                water += readln().toInt()
                print("Write how many ml of milk do you want to add: ")
                milk += readln().toInt()
                print("Write how many grams of coffee beans do you want to add: ")
                beans += readln().toInt()
                print("Write how many disposable cups of coffee do you want to add: ")
                cups += readln().toInt()
            }
        }

        if (command == "take") {
            println("I gave you \$$coffeeMachine.money")
            coffeeMachine.money = 0
        }

        if (command == "remaining") {
            coffeeMachine.status()
        }
    }
}
