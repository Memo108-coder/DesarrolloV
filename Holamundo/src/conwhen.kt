fun main(arg: Array<String>){

    val password = "ee34r6fedd"
    when (password.length){
        0-> println("Contraseña basia porfa agrega algo en esa zona")
        in 1..4 ->println("contraseña muy debil agrega mas caracteres")
        in 5..6 -> println("contraseña debil agregar mas signos")
        else -> println("ESOOO ya era hora oe al fin se te ocurre una contraseña segura malcriado ")

    }
    esPar(numero = 23243)
}

fun esPar(numero: Int) = when(numero % 2){
    0-> println("El numero es par")
    else -> println("El numero es impar")
}