package io.foxcapades.spigot.block.compression.event.handler

import io.foxcapades.spigot.block.compression.compressible.CompressionLevel.None
import io.foxcapades.spigot.block.compression.event.BCInvClickEvent
import io.foxcapades.spigot.block.compression.item.ifCompressionLevelNot
import io.foxcapades.spigot.block.compression.item.ifNotEmpty

internal object StandardShiftLeftClickHandler : ClickHandler {
  override fun handle(event: BCInvClickEvent) =
    with(event) {
      inventorySlot.ifNotEmpty {
        return handleItemMove(event)
      }

      cursor.ifNotEmpty {
        StandardLeftClickHandler.handleStandardItemPlace(event)
      }
    }

  private fun handleItemMove(event: BCInvClickEvent) =
    event.ifUserClickedBottomInv {
      event.inventorySlot.ifCompressionLevelNot(None) {
        event.ifTopInvIsNotCompressedItemSafe { cancel() }
      }
    }
}