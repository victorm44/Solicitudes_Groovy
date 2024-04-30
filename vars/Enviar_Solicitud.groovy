// vars/EnviarSolicitud.groovy
def call(String url, String authorization) {
    def post = new URL(url).openConnection()
    encodedBytes = new String(Base64.getEncoder().encode(authorization.getBytes()))
    authorization = "Basic " + new String(encodedBytes);

    post.setRequestMethod("POST")
    post.setDoOutput(true)
    post.setRequestProperty("Authorization", authorization);
    post.setRequestProperty("Content-Type", "application/json")

    def postRC = post.getResponseCode()
    println("Código de respuesta: " + postRC)

    if (postRC == 200) {
        println(" =========== Respuesta del servidor: ============")
        println(post.getInputStream().getText())
    } else {
        currentBuild.result = 'FAILURE'
    }
}
