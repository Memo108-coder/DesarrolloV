fun main (arg: Array<String>){
    val arrayPares: IntArray = intArrayOf(0,2,4,6,8)
    println(arrayPares[1])
    val arrayImpares: IntArray = IntArray(size = 10)
    println(arrayImpares[5])
    val arrayImparesv2: IntArray = IntArray(size = 5){1}
    println(arrayImparesv2[3])
    println(arrayImparesv2[2])
    val arrayImparesv3: IntArray = IntArray(size = 5){it * 3}
    println("Numeros potencias de 3:")
    println(arrayImparesv3[0])
    println(arrayImparesv3[1])
    println(arrayImparesv3[2])
}