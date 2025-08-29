fun main(arg: Array<String>){
    val llueve: Boolean = true
    val nublado: Boolean = true
    val hacefrio: Boolean = true

    if(llueve || hacefrio){
        println("NO planeo salir a pesar")
    }

    if(nublado && hacefrio){
        println("Seguramente va a llover")
    }

    if(!llueve){
        println("No necesito el paraguas")
    }
}