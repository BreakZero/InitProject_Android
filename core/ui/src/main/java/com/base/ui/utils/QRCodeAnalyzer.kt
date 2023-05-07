package com.base.ui.utils

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ImageFormat
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.zxing.BarcodeFormat
import com.google.zxing.BinaryBitmap
import com.google.zxing.DecodeHintType
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatReader
import com.google.zxing.PlanarYUVLuminanceSource
import com.google.zxing.common.HybridBinarizer
import com.google.zxing.qrcode.QRCodeWriter
import java.nio.ByteBuffer

class QRCodeAnalyzer(
  private val onCodeScanned: (result: String) -> Unit
) : ImageAnalysis.Analyzer {
  private val supportedImageFormats = listOf(
    ImageFormat.YUV_420_888,
    ImageFormat.YUV_422_888,
    ImageFormat.YUV_444_888
  )
  private var finished = false

  override fun analyze(image: ImageProxy) {
    if (image.format in supportedImageFormats) {
      val bytes = image.planes.first().buffer.toByteArray()
      val source = PlanarYUVLuminanceSource(
        bytes,
        image.width,
        image.height,
        0,
        0,
        image.width,
        image.height,
        false
      )
      val binaryBmp = BinaryBitmap(HybridBinarizer(source))
      try {
        val result = MultiFormatReader().apply {
          setHints(
            mapOf(
              DecodeHintType.POSSIBLE_FORMATS to arrayListOf(
                BarcodeFormat.QR_CODE
              )
            )
          )
        }.decode(binaryBmp)
        if (result.text.isNotBlank() && !finished) {
          onCodeScanned(result.text)
          finished = true
        }
      } catch (e: Exception) {
        e.printStackTrace()
      } finally {
        image.close()
      }
    }
  }

  private fun ByteBuffer.toByteArray(): ByteArray {
    rewind()
    return ByteArray(remaining()).also {
      get(it)
    }
  }
}

inline fun String.encodeQRCodeToBitmap(
  width: Int = 500,
  height: Int = 500,
  block: (Bitmap?) -> Unit
) {
  val qrCodeWriter = QRCodeWriter()
  val hints = hashMapOf<EncodeHintType, String>().apply {
    put(EncodeHintType.CHARACTER_SET, "utf-8")
  }
  val encode = runCatching {
    qrCodeWriter.encode(this, BarcodeFormat.QR_CODE, width, height, hints)
  }.onFailure {
    it.printStackTrace()
  }.getOrNull()

  block(
    encode?.let {
      val colors = IntArray(width * height)
      (0 until width).forEach { wi ->
        (0 until height).forEach { hi ->
          val index = wi * width + hi
          if (it.get(wi, hi)) {
            colors[index] = if (index % 2 == 0) Color.BLUE else Color.GREEN
          } else {
            colors[index] = Color.WHITE
          }
        }
      }
      Bitmap.createBitmap(colors, width, height, Bitmap.Config.RGB_565)
    }
  )
}
