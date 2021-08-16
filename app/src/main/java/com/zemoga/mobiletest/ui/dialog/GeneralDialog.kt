package com.zemoga.mobiletest.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.zemoga.mobiletest.R

class GeneralDialog{

    fun show(context: Context, message: String, lottieFile: String, timeout: Long){

        val promptsView = LayoutInflater.from(context).inflate(R.layout.general_dialog, null)
        val alertDialogBuilder = AlertDialog.Builder(context, R.style.CustomAlertDialog)

        alertDialogBuilder.setView(promptsView)
        val tvMessage = promptsView.findViewById<View>(R.id.tvMessage) as TextView
        val ivIcon = promptsView.findViewById<View>(R.id.imgAnimation) as LottieAnimationView

        tvMessage.text = message
        ivIcon.setAnimation(lottieFile)

        // set dialog message
        alertDialogBuilder.setCancelable(false)

        // create alert dialog
        val alertDialog = alertDialogBuilder.create()

        // show it
        alertDialog.show()

        Handler(Looper.getMainLooper()).postDelayed({
            alertDialog.dismiss()
        }, timeout)
    }
}