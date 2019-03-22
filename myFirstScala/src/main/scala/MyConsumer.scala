import java.io.{ByteArrayInputStream, ByteArrayOutputStream, ObjectInputStream, ObjectOutputStream}
import java.util.Properties

import scala.collection.JavaConverters._
import java.time.Duration

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.serialization.{Deserializer, Serializer}
case class Velib(
                  postNumber: Int,
                  parkPlusBikeNumber: Int,
                  stationPostNumber: Int,
                  parkPlusActivation: String
                )

class VelibSerialize extends Serializer[Velib]{
  override def serialize(topic: String, data: Velib): Array[Byte] = {
    try{
      val byteOut = ByteArrayOutputStream()
      val objOut = new ObjectOutputStream(byteOut)
      objOut.writeObject(data)
      byteOut.toByteArray
    }
    catch{
      case ex : Exception => throw new Exception(ex.getMessage())
    }
  }
}

class VelibDeserialize extends Deserializer[Velib]{
  override def deserialize(topic: String, data: Array[Byte]) = {
    val byteIn = new ByteArrayInputStream(data)
    val objIn = new ObjectInputStream(byteIn)
    val obj = objIn.readObject().asInstanceOf[Velib]
    byteIn.close()
    objIn.close()
    obj
  }
}




object MyConsumer extends App {
  val props = new Properties()
  props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
  props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
  props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true")
  props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
  props.put(ConsumerConfig.GROUP_ID_CONFIG, "1")
  //props.put(ConsumerConfig.GROUP_ID_CONFIG, )
  val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)
  consumer.subscribe(List("KilianLeBg").asJava)
  val records: ConsumerRecords[String, String] = consumer.poll(Duration.ofMillis(1000))
  records.asScala.foreach{
    record => println(s"offset = ${record.offset()}, key = ${record.key()}, value = ${record.value()}")
  }
  //consumer.commitSync()
  consumer.close()
  }
