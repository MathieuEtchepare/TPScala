import org.apache.kafka.common.serialization.{Deserializer, Serializer}
case class Velib(
                  postNumber: Int,
                  parkPlusBikeNumber: Int,
                  stationPostNumber: Int,
                  parkPlusActivation: String
                )

class VelibSerialize extends Serializer[Velib]{
  override def serialize(topic: String, data: Velib): Unit ={
    var a: Array[Byte]
    a[0] = data.postNumber
    a[1] = data.parkPlusBikeNumber
    a[2] = data.stationPostNumber
  } Array[Byte] = {data.postNumber, data.parkPlusBikeNumber, data.stationPostNumber, data.parkPlusActivation}
}

class VelibDeserialize extends Deserializer[Velib]{
  override def deserialize(topic: String, data: Array[Byte]): Velib = {data[0], data[1], data[2], data[3]}
}