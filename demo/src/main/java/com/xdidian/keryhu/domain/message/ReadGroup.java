package com.xdidian.keryhu.domain.message;

/**
 * 新地点发送的message，此message 由谁来读取。
 * @author hushuming
 *
 */
public enum ReadGroup {
  XDIDIAN,               // 新地点的工作人员，包含客服和管理员 所以读取时候，需要验证此人的role
  INDIVIDUAL;             // 个人会员，如果message，是由个人来读取，那么必需要加上此人的userId
 
}
