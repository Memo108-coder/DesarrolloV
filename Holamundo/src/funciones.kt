fun main(arg: Array<String>){
printHeader("Declaracionfunciones")
var resultado = printFooter(titulo="Funcion con resutado")
println(resultado)

}

fun printHeader(titulo: String){
    println("********************")
    println("******"+ titulo + "******")
    println("********************")
}


fun printFooter(titulo: String): String{
    var footer ="***********\n"
    footer += titulo
    footer +="**************"
    return footer
}