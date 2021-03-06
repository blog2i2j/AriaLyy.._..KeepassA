/*
 * Copyright (C) 2020 AriaLyy(https://github.com/AriaLyy/KeepassA)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 */


package com.lyy.keepassa.event

import com.keepassdroid.database.PwEntryV4
import com.keepassdroid.database.PwGroupV4

/**
 * 恢复数据的事件
 */
data class MoveEvent(
  val type: Int = MOVE_TYPE_GROUP, // 1：群组，2：条目
  val entryV4: PwEntryV4? = null,
  val pwGroupV4: PwGroupV4? = null
){
  companion object{
    const val MOVE_TYPE_GROUP = 1 // 群组
    const val MOVE_TYPE_ENTRY = 2 // 条目
  }
}

