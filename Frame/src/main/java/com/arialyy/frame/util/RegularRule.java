/*
 * Copyright (C) 2020 AriaLyy(https://github.com/AriaLyy/KeepassA)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 */


package com.arialyy.frame.util;

/**
 * Created by AriaLyy on 2015/1/10.
 * 正则表达式规则
 */
public class RegularRule {

  /**
   * 一级域名
   */
  public static final String DOMAIN_TOP =
      "(\\w*\\.?){1}\\.(com.cn|net.cn|gov.cn|org\\.nz|org.cn|com|net|org|gov|cc|biz|info|cn|co)$";

  /**
   * 二级域名
   */
  public static final String DOMAIN_SECOND =
      "(\\w*\\.?){2}\\.(com.cn|net.cn|gov.cn|org\\.nz|org.cn|com|net|org|gov|cc|biz|info|cn|co)$";

  /**
   * 三级域名
   */
  public static final String DOMAIN_THIRD =
      "(\\w*\\.?){3}\\.(com.cn|net.cn|gov.cn|org\\.nz|org.cn|com|net|org|gov|cc|biz|info|cn|co)$";

  /**
   * APK包
   */
  public static String APK = "^(.*)\\.(apk)$";
  /**
   * 视频
   */
  public static String VIDEO =
      "^(.*)\\.(mpeg-4|h.264|h.265|rmvb|xvid|vp6|h.263|mpeg-1|mpeg-2|avi|" +
          "mov|mkv|flv|3gp|3g2|asf|wmv|mp4|m4v|tp|ts|mtp|m2t)$";
  /**
   * 音频
   */
  public static String MUSIC = "^(.*)\\.(aac|vorbis|flac|mp3|mp2|wma)$";

  /**
   * 文本
   */
  public static String TEXT = "^(.*)\\.(txt|xml|html)$";
  /**
   * 压缩包
   */
  public static String ZIP = "^(.*)\\.(zip|rar|7z)$";
  /**
   * DOC
   */
  public static String DOC = "^(.*)\\.(doc|docx)";
  /**
   * PPT
   */
  public static String PPT = "^(.*)\\.(ppt|pptx)";
  /**
   * xls
   */
  public static String XLS = "^(.*)\\.(xls|xlsx)";
  /**
   * vcf
   */
  public static String VCF = "^(.*)\\.(vcf)";
  /**
   * pdf
   */
  public static String PDF = "^(.*)\\.(pdf)";
  /**
   * SQL
   */
  public static String SQL = "^(.*)\\.(sql|db)";

  /**
   * 图片
   */
  public static String IMG = "^(.*)\\.(jpg|bmp|png|gif|jpeg|psd)";

  /**
   * 中文
   */
  public static String CHINESE = "[\\u4e00-\\u9fa5]";

  /**
   * 首尾空白字符
   */
  public static String START_OR_END_NONE = "^\\s*|\\s*";

  /**
   * 空白行
   */
  public static String NOTE_ITEM = "\\n\\s*\\r";

  /**
   * 邮箱
   */
  public static String EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

  /**
   * url
   */
  public static String URL = "[a-zA-z]+://[^\\s]*";

  /**
   * QQ
   */
  public static String QQ = "[1-9][0-9]{4,}";

  /**
   * IP
   */
  public static String IP = "\\d+\\.\\d+\\.\\d+\\.\\d+";
}