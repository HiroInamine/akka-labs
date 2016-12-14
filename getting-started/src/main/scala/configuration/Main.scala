package configuration

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory

object Main extends App {

  def simpleConfiguration() = {
    val logger = LoggerFactory.getLogger(this.getClass)
    val system = ActorSystem()
    // logger.debug(s"${system.settings}")
    // this is a shortcut for system.settings().config().root().render()
  }

  def programmaticallyConfiguration() = {
    val customConf = ConfigFactory.parseString(
      """
        akka.actor.deployment {
          /my-service {
            router = round-robin-pool
            nr-of-instances = 3
          }
        }
      """)
    // ConfigFactory.load sandwiches customConfig between default reference
    // config and default overrides, and then resolves it.
    val system = ActorSystem("MySystem", ConfigFactory.load(customConf))
  }

  programmaticallyConfiguration()

}
