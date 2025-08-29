fun main(arg: Array<String>) {
    var operando1: Int = 5
    var operando2: Int = 10

    //float tamaños pequeños
    var operando3: Float
    var operando4: Float

    operando3 = 4.3f
    operando4 = 20.5f

    //double tamaños mas grandes
    var operando5: Double
    var operando6: Double
    operando5 = 5.5
    operando6 = 13.5

    var resultadoSuma: Double = sumaDouble(operando5, operando6)
    println("la suma de Double = " + resultadoSuma)

    var resultadofloat: Float = sumaFloat(operando3, operando4)
    println("Suma de float =" + resultadofloat)


    println("Suma de int=" + sumaInt(operando1, operando2))
}

fun sumaDouble(operando5: Double, operando6: Double): Double {
    return operando5 + operando6
}

fun sumaFloat(op3: Float, op4: Float): Float {
    return op3 + op4
}

fun sumaInt(op1: Int, op2: Int): Int{
    return op1 + op2
}


