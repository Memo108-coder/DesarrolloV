fun main(arg: Array<String>){
    val numeros = intArrayOf(1,2,3,4,5,6)
    for (numero: Int in numeros) print(numero)
    println("****Numeros pares****")
    val numerosPares = intArrayOf(0,2,4,6,8,10)
    for((indice, valor) in numerosPares.withIndex()){
        println("Elemento $indice: $valor")

    }
 for(i in 1..10)println(i)
}