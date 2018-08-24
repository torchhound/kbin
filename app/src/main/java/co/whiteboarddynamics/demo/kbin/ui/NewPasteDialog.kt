package co.whiteboarddynamics.demo.kbin.ui

import android.app.Activity
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.EditText
import android.widget.Toast
import co.whiteboarddynamics.demo.kbin.R
import co.whiteboarddynamics.demo.kbin.models.NewPaste
import co.whiteboarddynamics.demo.kbin.models.Sections
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewPasteDialog : DialogFragment() {

  companion object {
    val TAG = NewPasteDialog::class.qualifiedName

    fun show(activity: Activity) {
      NewPasteDialog().show(activity.fragmentManager, TAG)
    }
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val view = activity.layoutInflater.inflate(R.layout.new_paste_dialog, null)

    val editTextName = view.findViewById<View>(R.id.txt_paste_name) as EditText
    val editTextContent = view.findViewById<View>(R.id.txt_paste_content) as EditText
    val editTextDescription = view.findViewById<View>(R.id.txt_paste_description) as EditText

    return AlertDialog.Builder(activity)
        .setTitle("Create a New Paste")
        .setView(view)
        .setPositiveButton("Submit") {
          dialog, _ ->
          run {
            val sectionsList : MutableList<Sections> = mutableListOf()
            val newSection = Sections(editTextName.text.toString(), "text", editTextContent.text.toString())
            sectionsList.add(newSection)
            MainActivity.getApi().postNew(NewPaste(sectionsList, editTextDescription.text.toString(), false)).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(
                {
                  //NewPasteResponse link?
                },
                {
                  Toast.makeText(MainActivity.applicationContext(), "Error: $it", Toast.LENGTH_LONG).show()
                },
                {
                  dialog.dismiss()
                }
            )
          }
        }
        .setNegativeButton("Cancel") {
          dialog, _ ->
            dialog.dismiss()
        }
        .create()
        .apply {
          setCanceledOnTouchOutside(false)
        }
  }
}