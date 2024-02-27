package machine

class CoffeeMachine() {
    //initial ingredients of the coffee machine
    var waterIst: Int = 400
    var milkIst: Int = 540
    var coffeeBeansIst: Int = 120
    var cupsIst: Int = 9
    var moneyIst: Int = 550

    //calculate the ingredients in the machine after the purchase
    fun buy() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")

        when(val coffeeType = readln()) {
            "1", "2", "3" -> {
                for (enum in CoffeeCalculation.values()) {
                    if (coffeeType == enum.coffee) {
                        when {
                            waterIst >= enum.water && milkIst >= enum.milk && coffeeBeansIst >= enum.coffeeBeans && cupsIst >= 1 -> {
                                println("I have enough resources, making you a coffee!")
                                waterIst -= enum.water
                                milkIst -= enum.milk
                                coffeeBeansIst -= enum.coffeeBeans
                                cupsIst--
                                moneyIst += enum.money
                            }
                            waterIst < enum.water -> println("Sorry, not enough water!")
                            milkIst < enum.milk -> println("Sorry, not enough milk!")
                            coffeeBeansIst < enum.coffeeBeans -> println("Sorry, not enough coffee beans!")
                            cupsIst == 0 -> println("Sorry, not enough cups!")
                        }
                    }
                }
            }
            "back" -> println("Write action (buy, fill, take, remaining, exit): ")
        }
    }

    //fill in the coffee machine
    fun fill() {
        println("Write how many ml of water you want to add:")
        val waterFill = readln().toInt()
        waterIst += waterFill
        println("Write how many ml of milk you want to add:")
        val milkFill = readln().toInt()
        milkIst += milkFill
        println("Write how many grams of coffee beans you want to add:")
        val coffeeBeansFill = readln().toInt()
        coffeeBeansIst += coffeeBeansFill
        println("Write how many disposable cups you want to add:")
        val cupsFill = readln().toInt()
        cupsIst += cupsFill
    }

    //take the money from coffee machine
    fun take() {
        println("I gave you $moneyIst")
        moneyIst = 0
    }

    //print the coffee machine's remaining
    fun remaining() {
        println("The coffee machine has:")
        println("$waterIst ml of water")
        println("$milkIst ml of milk")
        println("$coffeeBeansIst g of coffee beans")
        println("$cupsIst disposable cups")
        println("$$moneyIst of money")
    }
}

//set ingredient amount of each coffee type
enum class CoffeeCalculation {
    ESPRESSO("1", 250, 0, 16, 4),
    LATTE("2", 350, 75, 20, 7),
    CAPPUCCINO("3", 200, 100, 12, 6);

    val coffee: String
    val water: Int
    val milk: Int
    val coffeeBeans: Int
    val money: Int

    constructor(coffee: String, water: Int, milk: Int, coffeeBeans: Int, money: Int) {
        this.coffee = coffee
        this.water = water
        this.milk = milk
        this.coffeeBeans = coffeeBeans
        this.money = money
    }
}

fun main() {
    val coffeeMachine = CoffeeMachine()

    do {
        println("Write action (buy, fill, take, remaining, exit): ")

        val userAction = readln()

        when (userAction) {
            "buy" -> coffeeMachine.buy()
            "fill" -> coffeeMachine.fill()
            "take" -> coffeeMachine.take()
            "remaining" -> coffeeMachine.remaining()
        }
    } while (userAction != "exit")
}