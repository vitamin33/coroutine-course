import kotlinx.coroutines.*
import kotlin.random.Random

fun main(args: Array<String>): Unit = runBlocking {
    val coroutinesDeferred: List<Deferred<String>> = List(100) {
        async(start = CoroutineStart.DEFAULT) {
            doWork("Hello from Coroutines: $it")
        }
    }
    val coroutinesJobs: List<Job> = List(100) {
        launch(start = CoroutineStart.DEFAULT) {
            doWork("Hello from Coroutines: $it")
        }
    }
    coroutinesDeferred.forEach {
        //it.cancel("Cancel by hand")
        //println(it.await())
    }
    coroutinesJobs.forEach {
        it.cancel("Cancel by hand")
        //println(it.await())
    }
}

suspend fun doWork(name: String): String {
    delay(Random.nextInt(5000).toLong())
    return "Done. $name"
}