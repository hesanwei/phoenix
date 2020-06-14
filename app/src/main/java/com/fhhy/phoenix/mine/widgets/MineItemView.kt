package com.fhhy.phoenix.mine.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.fhhy.phoenix.R
import kotlinx.android.synthetic.main.view_mine_item.view.*

/**
 * MineItemView class
 *
 * @author admin created on 2020/6/14
 *
 */
class MineItemView : RelativeLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        LayoutInflater.from(context).inflate(R.layout.view_mine_item, this)
        with(context.obtainStyledAttributes(attributeSet, R.styleable.MineItemView)) {
            val showDivider = getBoolean(R.styleable.MineItemView_mivShowDivider, true)
            dividerLine.visibility = if (showDivider) View.VISIBLE else View.INVISIBLE

            val image = getResourceId(R.styleable.MineItemView_mivImage, -1)
            if (image == -1) {
                ivImage.visibility = View.GONE
            } else {
                ivImage.visibility = View.VISIBLE
                ivImage.setImageResource(image)
            }

            val name = getString(R.styleable.MineItemView_mivName)
            tvName.text = name

            recycle()
        }
    }

    fun setValue(value: String) {
        tvValue.text = value
    }
}