import org.junit.Test
import java.util.Calendar

//Clases
class Computadora(val procesador: String, var ram: Int, var almacenamiento: Int, var sistemaOperativo: String
) {
    var encendida: Boolean = false
    val programasInstalados: MutableList<String> = mutableListOf(
        "Notion 2026", "Facebook 2024", "Word 2023", "Android Studio 2026"
    )

    fun encender() {
        if (!encendida) {
            encendida = true
            println("La computadora se ha encendido")
        } else {
            println("La computadora ya está encendida")
        }
    }

    fun apagar() {
        if (encendida) {
            encendida = false
            println("La computadora se ha apagado")
        } else {
            println("La computadora ya está apagada")
        }
    }

    fun cambiarRam(nuevaRam: Int) {
        ram = nuevaRam
        println("RAM actualizada a $nuevaRam GB")
    }

    fun cambiarAlmacenamiento(nuevoAlmacenamiento: Int) {
        almacenamiento = nuevoAlmacenamiento
        println("Almacenamiento actualizado a $nuevoAlmacenamiento GB")
    }

    fun cambiarSistemaOperativo(nuevoSO: String) {
        sistemaOperativo = nuevoSO
        println("Sistema operativo cambiado a $nuevoSO")
    }

    fun programasDelAnioActual(): List<String> {
        val anioActual = Calendar.getInstance().get(Calendar.YEAR).toString()
        val regexAnio = "\\b(\\d{4})\\b".toRegex()
        return programasInstalados.filter { programa ->
            val match = regexAnio.find(programa)
            match?.groupValues?.get(1) == anioActual
        }
    }
}

class Calculadora(val marca: String, val vida: Int, var precio: Double) {
    fun sumar(a: Double, b: Double): Double = a + b
    fun restar(a: Double, b: Double): Double = a - b
    fun multiplicar(a: Double, b: Double): Double = a * b
    fun dividir(a: Double, b: Double): Double {
        if (b == 0.0) throw IllegalArgumentException("Elementos ingresados no validos")
        return a / b
    }
}

data class Estudiante(val nombre: String, val carnet: String, val asignatura: String)

class EjecutorEjercicios {
    @Test
    fun ejecutarMenu() {
        main()
    }
}

//Francisco Javier Martinez Donado 00073424
// Correr con covarege
fun main() {
    val miPc = Computadora( procesador = "Intel Core i5", ram = 8, almacenamiento = 512, sistemaOperativo = "Windows 11")
    val miCalc = Calculadora(marca = "Casio", vida = 5, precio = 29.99)
    val ciclo01 = listOf(
        Estudiante(nombre = "Carlos Rivera", carnet = "1", asignatura = "Programacion de Dispositivos Moviles"),
        Estudiante(nombre = "Carlos Cruz", carnet = "2", asignatura = "Programacion de Dispositivos Moviles"),
        Estudiante(nombre = "Francisco Martinez", carnet = "3", asignatura = "Programacion de Dispositivos Moviles"),
        Estudiante(nombre = "Alberto Martinez", carnet = "4", asignatura = "Análisis numerico"),
        Estudiante(nombre = "Enoc Melara", carnet = "5", asignatura = "Analisis numerico"),
        Estudiante(nombre = "Diego Sanchez", carnet = "6", asignatura = "Analisis numerico"),
        Estudiante(nombre = "Valeria Gomez", carnet = "7", asignatura = "Analisis numerico")
    )

    while (true) {
        println("=== Menu ===")
        println("1. Ejercicio 1: Computadora")
        println("2. Ejercicio 2: Calculadora")
        println("3. Ejercicio 3: Estudiantes")
        println("0. Salir")
        print("Seleccione una opcion: ")

        when (readLine()?.toIntOrNull()) {
            1 -> menuCompu(miPc)
            2 -> menuCalc(miCalc)
            3 -> menuEstudiantes(lista = ciclo01)
            0 -> {
                println("Fin del programa")
                return
            }
            else -> println("Opción no válida, intente de nuevo")
        }
    }
}



fun menuCompu(pc: Computadora) {
    while (true) {
        println("Ejercicio 1: Computadora")
        println("1. Encender")
        println("2. Apagar")
        println("3. Cambiar RAM")
        println("4. Cambiar Almacenamiento")
        println("5. Cambiar Sistema Operativo")
        println("6. Ver programas del año actual")
        println("0. Volver al menú principal")
        print("Opción: ")

        when (readLine()?.toIntOrNull()) {
            1 -> pc.encender()
            2 -> pc.apagar()
            3 -> {
                print("Ingrese nueva cantidad de RAM : ")
                val nuevaRam = readLine()?.toIntOrNull()
                if (nuevaRam != null && nuevaRam > 0) pc.cambiarRam(nuevaRam)
                else println("Valor inválido")
            }
            4 -> {
                print("Ingrese nuevo almacenamiento (GB): ")
                val nuevoAlmacenamiento = readLine()?.toIntOrNull()
                if (nuevoAlmacenamiento != null && nuevoAlmacenamiento > 0) pc.cambiarAlmacenamiento(nuevoAlmacenamiento)
                else println("Valor invalido")
            }
            5 -> {
                print("Ingrese nuevo sistema operativo: ")
                val nuevoSO = readLine()
                if (!nuevoSO.isNullOrBlank()) pc.cambiarSistemaOperativo(nuevoSO)
                else println("Nombre invalido")
            }
            6 -> {
                val programas = pc.programasDelAnioActual()
                if (programas.isNotEmpty()) {
                    println("Programas del año actual:")
                    programas.forEach { println(" - $it") }
                } else {
                    println("No hay programas del año actual")
                }
            }
            0 -> return
            else -> println("Opcion no valida")
        }
    }
}

fun menuCalc(calc: Calculadora) {
    while (true) {
        println("Ejercicio 2: Calcualdora (marca: ${calc.marca}, vida: ${calc.vida} años, precio: $${calc.precio})")
        println("1. Sumar")
        println("2. Restar")
        println("3. Multiplicar")
        println("4. Dividir")
        println("0. Volver al menu")
        print("Opcion: ")

        val opcion = readLine()?.toIntOrNull()
        if (opcion == 0) return
        if (opcion !in 1..4) {
            println("Opcion no valida")
            continue
        }

        print("Ingrese el primer número: ")
        val a = readLine()?.toDoubleOrNull()
        print("Ingrese el segundo número: ")
        val b = readLine()?.toDoubleOrNull()

        if (a == null || b == null) {
            println("Numeros invalidos")
            continue
        }

        val resultado = try {
            when (opcion) {
                1 -> calc.sumar(a, b)
                2 -> calc.restar(a, b)
                3 -> calc.multiplicar(a, b)
                4 -> calc.dividir(a, b)
                else -> null
            }
        } catch (e: IllegalArgumentException) {
            println(e.message)
            null
        }

        if (resultado != null) {
            println("Resultado: $resultado")
        }
    }
}

fun menuEstudiantes(lista: List<Estudiante>) {
    println("Ejercicio 3 - Estudiantes de moviles: ")
    val filtrados = lista.filter { it.asignatura == "Programacion de Dispositivos Moviles" }
    if (filtrados.isEmpty()) {
        println("No hay estudiantes en esa materia.")
    } else {
        filtrados.forEachIndexed { i, est ->
            println("${i + 1}. ${est.nombre} (Carnet: ${est.carnet})")
        }
    }
    return
}
