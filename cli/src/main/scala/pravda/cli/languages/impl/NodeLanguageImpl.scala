package pravda.cli.languages.impl

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpEntity, HttpMethods, HttpRequest}
import akka.stream.ActorMaterializer
import akka.util.{ByteString => AkkaByteString}
import com.google.protobuf.ByteString
import pravda.cli.languages.NodeLanguage
import pravda.common.bytes
import pravda.common.domain.{Address, NativeCoin}
import pravda.node.data.blockchain.Transaction.UnsignedTransaction
import pravda.node.data.blockchain.TransactionData
import pravda.node.data.cryptography
import pravda.node.data.cryptography.PrivateKey
import pravda.node.launcher

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.Random

final class NodeLanguageImpl(implicit system: ActorSystem,
                             materializer: ActorMaterializer,
                             executionContext: ExecutionContextExecutor)
    extends NodeLanguage[Future] {

  def launch(configPath: String): Future[Unit] = Future {
    sys.props.put("config.file", configPath)
    launcher.main(Array.empty)
    Thread.currentThread().join()
  }

  def singAndBroadcastTransaction(uriPrefix: String,
                                  address: ByteString,
                                  privateKey: ByteString,
                                  data: ByteString): Future[Either[String, String]] = {

    val tx = UnsignedTransaction(
      from = Address @@ address,
      program = TransactionData @@ data,
      wattLimit = 0L, // TODO
      wattPrice = NativeCoin.zero, // TODO
      nonce = Random.nextInt()
    )

    val stx = cryptography.signTransaction(PrivateKey @@ privateKey, tx)
    val fromHex = bytes.byteString2hex(address)
    val signatureHex = bytes.byteString2hex(stx.signature)

    val request = HttpRequest(
      method = HttpMethods.POST,
      uri = s"$uriPrefix?from=$fromHex&signature=$signatureHex&nonce=${tx.nonce}&fee=0",
      entity = HttpEntity(data.toByteArray)
    )

    Http()
      .singleRequest(request)
      .flatMap { response =>
        response.entity.dataBytes
          .runFold(AkkaByteString(""))(_ ++ _)
          .map(x => Right(x.utf8String))
      }
  }
}
