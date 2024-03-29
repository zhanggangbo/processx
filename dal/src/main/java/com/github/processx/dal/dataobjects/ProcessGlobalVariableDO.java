package com.github.processx.dal.dataobjects;

import java.util.Date;

public class ProcessGlobalVariableDO {
  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column process_global_variable.id
   *
   * @mbggenerated
   */
  private Long id;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column process_global_variable.process_instance_id
   *
   * @mbggenerated
   */
  private Long processInstanceId;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column process_global_variable.biz_no
   *
   * @mbggenerated
   */
  private String bizNo;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column process_global_variable.name
   *
   * @mbggenerated
   */
  private String name;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column process_global_variable.value
   *
   * @mbggenerated
   */
  private String value;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column process_global_variable.create_time
   *
   * @mbggenerated
   */
  private Date createTime;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column process_global_variable.modified_time
   *
   * @mbggenerated
   */
  private Date modifiedTime;

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table process_global_variable
   *
   * @mbggenerated
   */
  public ProcessGlobalVariableDO(Long id, Long processInstanceId, String bizNo, String name, String value, Date createTime, Date modifiedTime) {
    this.id = id;
    this.processInstanceId = processInstanceId;
    this.bizNo = bizNo;
    this.name = name;
    this.value = value;
    this.createTime = createTime;
    this.modifiedTime = modifiedTime;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table process_global_variable
   *
   * @mbggenerated
   */
  public ProcessGlobalVariableDO() {
    super();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column process_global_variable.id
   *
   * @return the value of process_global_variable.id
   * @mbggenerated
   */
  public Long getId() {
    return id;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column process_global_variable.id
   *
   * @param id the value for process_global_variable.id
   * @mbggenerated
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column process_global_variable.process_instance_id
   *
   * @return the value of process_global_variable.process_instance_id
   * @mbggenerated
   */
  public Long getProcessInstanceId() {
    return processInstanceId;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column process_global_variable.process_instance_id
   *
   * @param processInstanceId the value for process_global_variable.process_instance_id
   * @mbggenerated
   */
  public void setProcessInstanceId(Long processInstanceId) {
    this.processInstanceId = processInstanceId;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column process_global_variable.biz_no
   *
   * @return the value of process_global_variable.biz_no
   * @mbggenerated
   */
  public String getBizNo() {
    return bizNo;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column process_global_variable.biz_no
   *
   * @param bizNo the value for process_global_variable.biz_no
   * @mbggenerated
   */
  public void setBizNo(String bizNo) {
    this.bizNo = bizNo == null ? null : bizNo.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column process_global_variable.name
   *
   * @return the value of process_global_variable.name
   * @mbggenerated
   */
  public String getName() {
    return name;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column process_global_variable.name
   *
   * @param name the value for process_global_variable.name
   * @mbggenerated
   */
  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column process_global_variable.value
   *
   * @return the value of process_global_variable.value
   * @mbggenerated
   */
  public String getValue() {
    return value;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column process_global_variable.value
   *
   * @param value the value for process_global_variable.value
   * @mbggenerated
   */
  public void setValue(String value) {
    this.value = value == null ? null : value.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column process_global_variable.create_time
   *
   * @return the value of process_global_variable.create_time
   * @mbggenerated
   */
  public Date getCreateTime() {
    return createTime;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column process_global_variable.create_time
   *
   * @param createTime the value for process_global_variable.create_time
   * @mbggenerated
   */
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column process_global_variable.modified_time
   *
   * @return the value of process_global_variable.modified_time
   * @mbggenerated
   */
  public Date getModifiedTime() {
    return modifiedTime;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column process_global_variable.modified_time
   *
   * @param modifiedTime the value for process_global_variable.modified_time
   * @mbggenerated
   */
  public void setModifiedTime(Date modifiedTime) {
    this.modifiedTime = modifiedTime;
  }
}