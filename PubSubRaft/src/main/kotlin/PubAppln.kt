package org.example

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlinx.cli.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.lang.Exception

class PubAppln : UpcallHandler {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    private val mwObj = PubMW
    var name: String? = null
    var numTopics: Int = 0
    var topicList: List<String> = listOf()
    var iters: Int = 0
    var frequency: Int = 0

    init {
        mwObj.setUpcallHandler(this)
    }

    fun initialize(name: String, numTopics: Int, topicList: List<String>, iters: Int, frequency: Int): PubAppln {
        this.name = name
        this.numTopics = numTopics
        this.topicList = topicList
        this.iters = iters
        this.frequency = frequency
        return this
    }

    fun configurePublisher(settings: Map<String, String>): PubAppln {
        val host = settings.getOrElse("host") { "localhost" }
        val port = settings.getOrElse("port") { "5672" }.toInt()
        mwObj.connectToServer(host, port)
        return this
    }

    fun publish(topic: String, messageContent: String): PubAppln {
        val serializedMessage: String = serializeMessageToJson(topic, messageContent)
        mwObj.publishMessage(serializedMessage)
        return this
    }

    private fun serializeMessageToJson(topic: String, messageContent: String): String {
        val json = Json { prettyPrint = true }
        val message = buildJsonObject {
            put("topic", topic) // Update this as needed, possibly as a method parameter
            put("content", messageContent)
            put("datetime", java.time.LocalDate.now().toString()) // Using java.time.LocalDate for the current date
            put("publisher", name ?: "Unknown")
        }
        return json.encodeToString(message)
    }

    fun changeTopic(newTopic: String): PubAppln {
        setUpTopic(newTopic)
        return this
    }

    fun shutdownPublisher(): PubAppln {
        mwObj.closeConnection()
        return this
    }

    override fun onMessagePublished(topic: String, message: String) {
        logger.info("Message published to topic $topic: $message")
    }

    override fun onErrorOccurred(error: Throwable) {
        logger.error("Error occurred in PubMW", error)
    }

    fun dump(): PubAppln {
        try {
            logger.info("PubAppln::dump")
            logger.info("-Name: $name")
            logger.info("-Number of Topics: $numTopics")
            logger.info("-TopicList: ${topicList.joinToString(", ")}")
            logger.info("-Iterations: $iters")
            logger.info("-Frequency: $frequency")
        } catch (e: Exception) {
            logger.error("Error during dump", e)
        }
        return this
    }

    private fun serializeMessage(message: Any): String {
        return message.toString()
    }

    private fun setUpTopic(topic: String) {
        mwObj.setupMessageExchange(topic)
    }
}

data class PubCLIArgs(
    val name: String,
    val addr: String,
    val port: Int,
    val discovery: String,
    val numTopics: Int,
    val config: String,
    val frequency: Int,
    val iters: Int,
    val loglevel: Int
)
fun pubParseCLIArgs(args: Array<String>): PubCLIArgs {
    val parser = ArgParser("PubAppln")
    val name by parser.option(ArgType.String, shortName = "n", description = "Some name assigned to us. Keep it unique per publisher").default("pub")
    val addr by parser.option(ArgType.String, shortName = "a", description = "IP addr of this publisher to advertise").default("localhost")
    val port by parser.option(ArgType.Int, shortName = "p", description = "Port number on which our underlying publisher RabbitMQ service runs").default(5672)
    val discovery by parser.option(ArgType.String, shortName = "d", description = "IP Addr:Port combo for the discovery service").default("localhost:5672")
    val numTopics by parser.option(ArgType.Int, shortName = "T", description = "Number of topics to publish").default(7)
    val config by parser.option(ArgType.String, shortName = "c", description = "configuration file").default("config.ini")
    val frequency by parser.option(ArgType.Int, shortName = "f", description = "Rate at which topics disseminated").default(1)
    val iters by parser.option(ArgType.Int, shortName = "i", description = "number of publication iterations").default(1000)
    val loglevel by parser.option(ArgType.Int, shortName = "l", description = "logging level").default(20)

    parser.parse(args)

    return PubCLIArgs(name, addr, port, discovery, numTopics, config, frequency, iters, loglevel)
}

fun main(args: Array<String>) {
    val parsedArgs = pubParseCLIArgs(args)
    val logger = LoggerFactory.getLogger("PubAppln")

    try {
        logger.info("Main - acquire a child logger and then log messages in the child")
        logger.debug("Main: parse command line arguments")
        logger.debug("Main: effective log level is ... (not defined yet)")

        logger.debug("Main: obtain the publisher appln object")
        val pubApp = PubAppln()

        val topicList = listOf("python", "ruby", "typescript")
        pubApp.initialize(parsedArgs.name, parsedArgs.numTopics, topicList, parsedArgs.iters, parsedArgs.frequency)

        logger.debug("Main: configure the publisher appln object")
        val settings = mapOf("host" to parsedArgs.addr, "port" to parsedArgs.port.toString())
        pubApp.configurePublisher(settings)
        logger.debug("Main: invoke the publisher application's main process")
        pubApp.publish("python", "fast api")
        pubApp.dump()

    } catch (e: Exception) {
        logger.error("Exception caught in main - ${e.message}", e)
    }
}
