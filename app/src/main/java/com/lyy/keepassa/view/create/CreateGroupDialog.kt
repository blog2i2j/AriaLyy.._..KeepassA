package com.lyy.keepassa.view.create

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.arialyy.frame.base.BaseDialog
import com.keepassdroid.database.PwGroup
import com.keepassdroid.database.PwIconCustom
import com.keepassdroid.database.PwIconStandard
import com.lyy.keepassa.R
import com.lyy.keepassa.base.BaseApp
import com.lyy.keepassa.databinding.DialogAddGroupBinding
import com.lyy.keepassa.event.CreateOrUpdateGroupEvent
import com.lyy.keepassa.util.HitUtil
import com.lyy.keepassa.util.IconUtil
import com.lyy.keepassa.view.ChooseIconActivity
import com.lyy.keepassa.view.dialog.LoadingDialog
import org.greenrobot.eventbus.EventBus

/**
 * 创建或编辑群组dialog
 */
class CreateGroupDialog : BaseDialog<DialogAddGroupBinding>(), View.OnClickListener {

  private val requestCode = 0xA1
  private lateinit var loadDialog: LoadingDialog
  private var icon = PwIconStandard(48)
  private var customIcon: PwIconCustom? = null
  private var group: PwGroup? = null
  private lateinit var module: CreateEntryModule
  var parentGroup: PwGroup = BaseApp.KDB.pm.rootGroup

  companion object {
    fun generate(body: CreateGroupDialog.() -> CreateGroupDialog): CreateGroupDialog {
      return with(CreateGroupDialog()) { body() }
    }
  }

  fun build(): CreateGroupDialog {
    return this
  }

  override fun setLayoutId(): Int {
    return R.layout.dialog_add_group
  }

  override fun initData() {
    super.initData()
    module = ViewModelProvider(this).get(CreateEntryModule::class.java)
    binding.groupNameLayout.setEndIconOnClickListener {
      startActivityForResult(
          Intent(context, ChooseIconActivity::class.java), requestCode,
          ActivityOptions.makeSceneTransitionAnimation(activity)
              .toBundle()
      )
    }
    binding.enter.setOnClickListener(this)
    binding.cancel.setOnClickListener(this)
  }

  override fun onClick(v: View?) {
    when (v!!.id) {
      R.id.enter -> {
        val title = binding.groupName.text.toString()
            .trim()
        if (title.isEmpty()) {
          HitUtil.toaskShort(getString(R.string.error_group_name_null))
          return
        }
        if (title.length > 16) {
          HitUtil.toaskShort(requireContext().getString(R.string.title_too_long))
          return
        }
        loadDialog = LoadingDialog(context)
        loadDialog.show()
        createGroup()
      }
      R.id.cancel -> {
        dismiss()
      }
    }
  }

  /**
   * 创建群组
   */
  private fun createGroup() {
    module.createGroup(
        binding.groupName.text.toString().trim(),
        parentGroup,
        icon,
        customIcon
    ).observe(this, Observer { newGroup ->
      this.group = newGroup
      loadDialog.dismiss()
      if (newGroup == null) {
        HitUtil.toaskShort(getString(R.string.create_group_fail))
        return@Observer
      }
      HitUtil.toaskShort(getString(R.string.create_group_success))
      EventBus.getDefault()
          .post(CreateOrUpdateGroupEvent(newGroup))
      dismiss()
    })
  }

  override fun onActivityResult(
    requestCode: Int,
    resultCode: Int,
    data: Intent?
  ) {
    super.onActivityResult(requestCode, resultCode, data)
    if (resultCode == Activity.RESULT_OK && requestCode == this.requestCode && data != null) {
      val type =
        data.getIntExtra(ChooseIconActivity.KEY_ICON_TYPE, ChooseIconActivity.ICON_TYPE_STANDARD)
      if (type == ChooseIconActivity.ICON_TYPE_STANDARD) {
        icon = data.getSerializableExtra(ChooseIconActivity.KEY_DATA) as PwIconStandard
        binding.groupNameLayout.endIconDrawable =
          resources.getDrawable(IconUtil.getIconById(icon.iconId), requireContext().theme)
        customIcon = PwIconCustom.ZERO
      } else if (type == ChooseIconActivity.ICON_TYPE_CUSTOM) {
        customIcon = data.getSerializableExtra(ChooseIconActivity.KEY_DATA) as PwIconCustom
        binding.groupNameLayout.endIconDrawable =
          IconUtil.convertCustomIcon2Drawable(requireContext(), customIcon!!)
      }
    }
  }
}