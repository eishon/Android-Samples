package com.lazypotato.mvvm_hilt_flow_room.ui.deleteallcompleted

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels

class DeleteAllCompletedDialogFragment : DialogFragment() {

    private val viewModel: DeleteAllCompletedViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle("Confrim deletion")
            .setMessage("Do you really want to delete all completed tasks?")
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Yes") { _, _ ->
                viewModel.onConfirmClick()

            }
            .create()
}