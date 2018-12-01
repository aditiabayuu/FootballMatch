package id.co.kotlin.footballmatch.Api

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.net.URL

class Repository {

    fun doRequest(url: String): Deferred<String> = GlobalScope.async {
        URL(url).readText()
    }
}