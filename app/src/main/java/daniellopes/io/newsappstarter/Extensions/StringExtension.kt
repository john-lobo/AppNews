package daniellopes.io.newsappstarter.Extensions

fun String.formataLimiteDeCarateres(limite: Int): String{
    if(this.length > limite) {
        val primeiroCaracter = 0
        return "${this.substring(primeiroCaracter, limite)}..."
    }
    return this
}