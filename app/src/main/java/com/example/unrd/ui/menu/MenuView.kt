package com.example.unrd.ui.menu

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.unrd.BuildConfig
import com.example.unrd.R
import kotlinx.android.synthetic.main.menu.view.*
import org.koin.core.KoinComponent

class MenuView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle), KoinComponent {

    var menuClickListener: MenuInterface? = null

    init {
        View.inflate(context, R.layout.menu, this)
        version.text = BuildConfig.VERSION_NAME
        setClickListeners()
    }

    private fun setClickListeners() {
        menuTitleBar.setOnClickListener {
            menuClickListener?.unrdClicked()
        }

        videoImage.setOnClickListener {
            menuClickListener?.videoListClicked()
        }

        videoText.setOnClickListener {
            menuClickListener?.videoListClicked()
        }
    }

}