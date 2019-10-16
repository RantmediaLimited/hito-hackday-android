package com.rantmedia.hackday

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


abstract class SwipeToDeleteCallback(context: Context) : ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.UP or ItemTouchHelper.DOWN,
    ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
) {
    private val deleteIcon = ContextCompat.getDrawable(context, android.R.drawable.ic_menu_delete)
    private val archiveIcon = ContextCompat.getDrawable(context, android.R.drawable.ic_menu_share)
    private val intrinsicWidth = deleteIcon!!.intrinsicWidth
    private val intrinsicHeight = deleteIcon!!.intrinsicHeight
    private val background = ColorDrawable()
    private val backgroundColor = Color.parseColor("#FF6759")
    private val clearPaint = Paint().apply { xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }
    private val redPaint = Paint(0).apply {
        color = backgroundColor
    }

    private val deleteDrawable = ContextCompat.getDrawable(context, R.drawable.delete_indicator)
    private val archiveDrawable = ContextCompat.getDrawable(context, R.drawable.archive_indicator)
    private val drawableIntrinsicWidth = deleteDrawable!!.intrinsicWidth
    private val drawableIntrinsicHeight = 300
    private val path = Path()

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        /**
         * To disable "swipe" for specific item return 0 here.
         * For example:
         * if (viewHolder?.itemViewType == YourAdapter.SOME_TYPE) return 0
         * if (viewHolder?.adapterPosition == 0) return 0
         */
        if (viewHolder.adapterPosition == 10) return 0
        return super.getMovementFlags(recyclerView, viewHolder)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onChildDraw(
        c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
        dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
    ) {
        val itemView = viewHolder.itemView
        val itemHeight = itemView.bottom - itemView.top
        val isCanceled = dX == 0f && !isCurrentlyActive

        if (isCanceled) {
            clearCanvas(
                c,
                itemView.right + dX,
                itemView.top.toFloat(),
                itemView.right.toFloat(),
                itemView.bottom.toFloat()
            )
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            return
        }

        when {
            // Swiping to the right
            dX > 0 -> {
                // Draw the red delete background
                //        background.color = backgroundColor
                //        background.setBounds(
                //            itemView.left,
                //            itemView.top,
                //            itemView.left + dX.toInt(),
                //            itemView.bottom
                //        )
                //        background.draw(c)

                //        c.drawOval(
                //            itemView.left.toFloat(),
                //            itemView.top.toFloat(),
                //            (itemView.left + dX.toInt()).toFloat(),
                //            itemView.bottom.toFloat(),
                //            redPaint)

                //        c.drawArc(
                //            itemView.left.toFloat(),
                //            itemView.top.toFloat(),
                //            (itemView.left + dX.toInt()).toFloat(),
                //            itemView.bottom.toFloat(),
                //            -90f,
                //            180f,
                //            false,
                //            redPaint)

                // TODO shaban: try the path method below
//                path.set(..)

                val drawableIconTop = itemView.top + (itemHeight - drawableIntrinsicHeight) / 2
                val drawableIconMargin = (itemHeight - drawableIntrinsicHeight) / 2
                val drawableIconRight = itemView.left - 10 + dX.toInt()
                val drawableIconLeft = itemView.left + drawableIconMargin
                val drawableIconBottom = drawableIconTop + drawableIntrinsicHeight

                deleteDrawable?.setBounds(
                    drawableIconLeft,
                    drawableIconTop,
                    drawableIconRight,
                    drawableIconBottom
                )
                deleteDrawable?.draw(c)

                // Calculate position of delete icon
                val deleteIconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
                val deleteIconMargin = (itemHeight - intrinsicHeight) / 2
                val deleteIconRight = itemView.left + deleteIconMargin + intrinsicWidth
                val deleteIconLeft = itemView.left + deleteIconMargin
                val deleteIconBottom = deleteIconTop + intrinsicHeight

                // Draw the delete icon
                deleteIcon?.setBounds(
                    deleteIconLeft,
                    deleteIconTop,
                    deleteIconRight,
                    deleteIconBottom
                )
            }
            // Swiping to the left
            dX < 0 -> {
                val drawableIconTop = itemView.top + (itemHeight - drawableIntrinsicHeight) / 2
                val drawableIconMargin = (itemHeight - drawableIntrinsicHeight) / 2
                val drawableIconRight = itemView.right + 10
                val drawableIconLeft = itemView.right - drawableIconMargin + dX.toInt()
                val drawableIconBottom = drawableIconTop + drawableIntrinsicHeight

                archiveDrawable?.isAutoMirrored = true
                archiveDrawable?.setBounds(
                    drawableIconLeft,
                    drawableIconTop,
                    drawableIconRight,
                    drawableIconBottom
                )
                archiveDrawable?.draw(c)

                val archiveIconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
                val archiveIconMargin = (itemHeight - intrinsicHeight) / 2
                val archiveIconLeft = itemView.right - archiveIconMargin - intrinsicWidth
                val archiveIconRight = itemView.right - archiveIconMargin
                val archiveIconBottom = archiveIconTop + intrinsicHeight

                archiveIcon?.setBounds(archiveIconLeft, archiveIconTop, archiveIconRight, archiveIconBottom)
            }
            else -> {
//                path.reset()
                deleteDrawable?.setBounds(0, 0, 0, 0)
                deleteIcon?.setBounds(0, 0, 0, 0)
                archiveIcon?.setBounds(0, 0, 0, 0)
            }
        }
//        c.drawPath(path, redPaint)
        deleteIcon?.draw(c)
        archiveIcon?.draw(c)

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun clearCanvas(c: Canvas?, left: Float, top: Float, right: Float, bottom: Float) {
        c?.drawRect(left, top, right, bottom, clearPaint)
    }
}