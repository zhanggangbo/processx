package com.github.processx.dal.dataobjects;

import java.util.Date;

public class ProcessSchedulePlanDO {
  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column process_schedule_plan.id
   *
   * @mbggenerated
   */
  private Long id;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column process_schedule_plan.biz_no
   *
   * @mbggenerated
   */
  private String bizNo;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column process_schedule_plan.process_instance_id
   *
   * @mbggenerated
   */
  private Long processInstanceId;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column process_schedule_plan.node_id
   *
   * @mbggenerated
   */
  private Long nodeId;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column process_schedule_plan.exec_time
   *
   * @mbggenerated
   */
  private Date execTime;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column process_schedule_plan.exec_counts
   *
   * @mbggenerated
   */
  private Integer execCounts;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column process_schedule_plan.status
   *
   * @mbggenerated
   */
  private String status;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column process_schedule_plan.create_time
   *
   * @mbggenerated
   */
  private Date createTime;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column process_schedule_plan.modified_time
   *
   * @mbggenerated
   */
  private Date modifiedTime;

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table process_schedule_plan
   *
   * @mbggenerated
   */
  public ProcessSchedulePlanDO(Long id, String bizNo, Long processInstanceId, Long nodeId, Date execTime, Integer execCounts, String status, Date createTime, Date modifiedTime) {
    this.id = id;
    this.bizNo = bizNo;
    this.processInstanceId = processInstanceId;
    this.nodeId = nodeId;
    this.execTime = execTime;
    this.execCounts = execCounts;
    this.status = status;
    this.createTime = createTime;
    this.modifiedTime = modifiedTime;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table process_schedule_plan
   *
   * @mbggenerated
   */
  public ProcessSchedulePlanDO() {
    super();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column process_schedule_plan.id
   *
   * @return the value of process_schedule_plan.id
   * @mbggenerated
   */
  public Long getId() {
    return id;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column process_schedule_plan.id
   *
   * @param id the value for process_schedule_plan.id
   * @mbggenerated
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column process_schedule_plan.biz_no
   *
   * @return the value of process_schedule_plan.biz_no
   * @mbggenerated
   */
  public String getBizNo() {
    return bizNo;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column process_schedule_plan.biz_no
   *
   * @param bizNo the value for process_schedule_plan.biz_no
   * @mbggenerated
   */
  public void setBizNo(String bizNo) {
    this.bizNo = bizNo == null ? null : bizNo.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column process_schedule_plan.process_instance_id
   *
   * @return the value of process_schedule_plan.process_instance_id
   * @mbggenerated
   */
  public Long getProcessInstanceId() {
    return processInstanceId;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column process_schedule_plan.process_instance_id
   *
   * @param processInstanceId the value for process_schedule_plan.process_instance_id
   * @mbggenerated
   */
  public void setProcessInstanceId(Long processInstanceId) {
    this.processInstanceId = processInstanceId;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column process_schedule_plan.node_id
   *
   * @return the value of process_schedule_plan.node_id
   * @mbggenerated
   */
  public Long getNodeId() {
    return nodeId;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column process_schedule_plan.node_id
   *
   * @param nodeId the value for process_schedule_plan.node_id
   * @mbggenerated
   */
  public void setNodeId(Long nodeId) {
    this.nodeId = nodeId;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column process_schedule_plan.exec_time
   *
   * @return the value of process_schedule_plan.exec_time
   * @mbggenerated
   */
  public Date getExecTime() {
    return execTime;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column process_schedule_plan.exec_time
   *
   * @param execTime the value for process_schedule_plan.exec_time
   * @mbggenerated
   */
  public void setExecTime(Date execTime) {
    this.execTime = execTime;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column process_schedule_plan.exec_counts
   *
   * @return the value of process_schedule_plan.exec_counts
   * @mbggenerated
   */
  public Integer getExecCounts() {
    return execCounts;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column process_schedule_plan.exec_counts
   *
   * @param execCounts the value for process_schedule_plan.exec_counts
   * @mbggenerated
   */
  public void setExecCounts(Integer execCounts) {
    this.execCounts = execCounts;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column process_schedule_plan.status
   *
   * @return the value of process_schedule_plan.status
   * @mbggenerated
   */
  public String getStatus() {
    return status;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column process_schedule_plan.status
   *
   * @param status the value for process_schedule_plan.status
   * @mbggenerated
   */
  public void setStatus(String status) {
    this.status = status == null ? null : status.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column process_schedule_plan.create_time
   *
   * @return the value of process_schedule_plan.create_time
   * @mbggenerated
   */
  public Date getCreateTime() {
    return createTime;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column process_schedule_plan.create_time
   *
   * @param createTime the value for process_schedule_plan.create_time
   * @mbggenerated
   */
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column process_schedule_plan.modified_time
   *
   * @return the value of process_schedule_plan.modified_time
   * @mbggenerated
   */
  public Date getModifiedTime() {
    return modifiedTime;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column process_schedule_plan.modified_time
   *
   * @param modifiedTime the value for process_schedule_plan.modified_time
   * @mbggenerated
   */
  public void setModifiedTime(Date modifiedTime) {
    this.modifiedTime = modifiedTime;
  }
}