fun main(arg: Array<String>){
       loop@ for (i in 1..10){
        for (j in 1..10){
            if(i == 5 && j == 5){
                println("Estamos por la mitad")
                break@loop
            } else{
                println("i=$i, j=$j")
            }
        }

    }
}