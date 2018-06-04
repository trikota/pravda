package pravda.node.data

import cats.Show
import com.google.protobuf.ByteString
import pravda.common.domain._
import supertagged.TaggedType

object blockchain {

  sealed trait Transaction {
    val from: Address
    val program: TransactionData
    val wattLimit: Long
    val wattPrice: NativeCoin
    val nonce: Int

    def forSignature: (Address, TransactionData, Long, NativeCoin, Int) =
      (from, program, wattLimit, wattPrice, nonce)
  }

  object Transaction {

    final case class UnsignedTransaction(from: Address,
                                         program: TransactionData,
                                         wattLimit: Long,
                                         wattPrice: NativeCoin,
                                         nonce: Int)
        extends Transaction

    final case class SignedTransaction(from: Address,
                                       program: TransactionData,
                                       signature: ByteString,
                                       wattLimit: Long,
                                       wattPrice: NativeCoin,
                                       nonce: Int)
        extends Transaction

    import pravda.common.bytes

    object SignedTransaction {
      implicit val showInstance: Show[SignedTransaction] = { t =>
        val from = bytes.byteString2hex(t.from)
        val program = bytes.byteString2hex(t.program)
        val signature = bytes.byteString2hex(t.signature)
        s"transaction.signed[from=$from,program=$program,signature=$signature,nonce=${t.nonce},wattLmit=${t.wattLimit},wattPrice=${t.wattPrice}]"
      }
    }

    final case class AuthorizedTransaction(from: Address,
                                           program: TransactionData,
                                           signature: ByteString,
                                           wattLimit: Long,
                                           wattPrice: NativeCoin,
                                           nonce: Int)
        extends Transaction
  }

  object TransactionData extends TaggedType[ByteString]
  type TransactionData = TransactionData.Type
}
