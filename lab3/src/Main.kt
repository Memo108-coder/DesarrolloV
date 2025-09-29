

class MainActivity : ComponentActivity() {

    companion object{
        const val moneda = "EUR"
    }

    var saldo : Float = 300.54f
    var sueldo = 764.82f
    var entero: Int = 62

    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fecha = "01/05/1990"
        var nombre: String = "jota"
        var vip: Boolean =  true
        var incial: Char = 'J'

        nombre = "adrian"

        var saludo = "Hola " + nombre

        /* CONDICIONALES */
        if (vip == true ) saludo += ", te queremos mucho"
        else saludo += ", queres ser vip? paga la cuota"
        mostrar_saldo()

        var dia = fecha.subSequence(0, 2).toString().toInt()
        if (dia == 1) ingresar_sueldo()


        var mes = fecha.subSequence(3, 5).toString().toInt()

        when(mes){
            1 -> ("\n En enero hay ofertas de inversiones con el 7% de interes")
            2, 3 -> ("\n En invierno no hay ofertas de inversiones")
            4, 5, 6 -> ("\n En primavera hay ofertas de inversiones con el 1.5% de interes")
            7, 8, 9 -> ("\n En verano hay ofertas de inversiones con el 2.5% de interes")
            10, 11, 12 -> ("\n En otoño hay ofertas de inversiones con el 3.5% de interes")
            else -> print("\n La fecha es erronea")
        }
        println(saludo)


        var pin = 1234
        var intentos: Int = 0
        var claveIngresada: Int = 1234
        do {
            println("Ingrese el pin:")
            println("Clave ingresada:  ${claveIngresada++}")
            intentos++
        }while ( intentos < 3 && claveIngresada != pin)

        if(intentos == 3) println("Tarjeta bloqueada")

        mostrar_saldo()
        ingresar_Dinero(50.5f)
        retirar_Dinero(40f)

        retirar_Dinero(50f)
        retirar_Dinero(2000f)


        var a: Boolean = true
        var b: Boolean = true
        var c: Boolean = false
        var d: Boolean = false

        a && b
        a || b
        a && c
        a && c

        !d




        saldo += sueldo
        saldo++

        a == b
        a !=b
        a > b
        a < b
        a >= b
        a <= b

    }


    fun mostrar_saldo(){
        println("Tienes $saldo $moneda")
    }

    fun ingresar_sueldo(){
        saldo += sueldo
        println("Se ha ingresado tu sueldo de $sueldo $moneda")
        mostrar_saldo()
    }

    fun ingresar_Dinero(cantidad: Float){
        saldo += cantidad
        println("Se ha ingresado $cantidad $moneda")
        mostrar_saldo()
    }

    fun retirar_Dinero(cantidad: Float){
        if ( verificarCantidad(cantidad) ) {
            saldo -= cantidad
            println("Se ha retirado tu sueldo de: $cantidad $moneda")
            mostrar_saldo()
        }
        else println("Cantidad superior al saldo, imposible retirar")
    }

    fun verificarCantidad(cantidad_a_retirar : Float): Boolean {
        if (cantidad_a_retirar > saldo) return false
        else return true
    }
}

private fun ComponentActivity.onCreate(savedInstanceState: Bundle?) {}


annotation class Bundle

annotation class ComponentActivity
