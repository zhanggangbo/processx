package com.github.processx.dal.dataobjects;

import java.util.Date;

public class ProcessDO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process.version
     *
     * @mbggenerated
     */
    private Integer version;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process.record_process_instance
     *
     * @mbggenerated
     */
    private Boolean recordProcessInstance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process.record_node_instance
     *
     * @mbggenerated
     */
    private Boolean recordNodeInstance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process.record_global_param
     *
     * @mbggenerated
     */
    private Boolean recordGlobalParam;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process.record_process_input
     *
     * @mbggenerated
     */
    private Boolean recordProcessInput;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process.record_trigger_input
     *
     * @mbggenerated
     */
    private Boolean recordTriggerInput;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process.record_trigger_result
     *
     * @mbggenerated
     */
    private Boolean recordTriggerResult;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process.modified_time
     *
     * @mbggenerated
     */
    private Date modifiedTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table process
     *
     * @mbggenerated
     */
    public ProcessDO(Long id, String name, Integer version, Boolean recordProcessInstance, Boolean recordNodeInstance, Boolean recordGlobalParam, Boolean recordProcessInput, Boolean recordTriggerInput, Boolean recordTriggerResult, Date createTime, Date modifiedTime) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.recordProcessInstance = recordProcessInstance;
        this.recordNodeInstance = recordNodeInstance;
        this.recordGlobalParam = recordGlobalParam;
        this.recordProcessInput = recordProcessInput;
        this.recordTriggerInput = recordTriggerInput;
        this.recordTriggerResult = recordTriggerResult;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table process
     *
     * @mbggenerated
     */
    public ProcessDO() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process.id
     *
     * @return the value of process.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process.id
     *
     * @param id the value for process.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process.name
     *
     * @return the value of process.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process.name
     *
     * @param name the value for process.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process.version
     *
     * @return the value of process.version
     *
     * @mbggenerated
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process.version
     *
     * @param version the value for process.version
     *
     * @mbggenerated
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process.record_process_instance
     *
     * @return the value of process.record_process_instance
     *
     * @mbggenerated
     */
    public Boolean getRecordProcessInstance() {
        return recordProcessInstance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process.record_process_instance
     *
     * @param recordProcessInstance the value for process.record_process_instance
     *
     * @mbggenerated
     */
    public void setRecordProcessInstance(Boolean recordProcessInstance) {
        this.recordProcessInstance = recordProcessInstance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process.record_node_instance
     *
     * @return the value of process.record_node_instance
     *
     * @mbggenerated
     */
    public Boolean getRecordNodeInstance() {
        return recordNodeInstance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process.record_node_instance
     *
     * @param recordNodeInstance the value for process.record_node_instance
     *
     * @mbggenerated
     */
    public void setRecordNodeInstance(Boolean recordNodeInstance) {
        this.recordNodeInstance = recordNodeInstance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process.record_global_param
     *
     * @return the value of process.record_global_param
     *
     * @mbggenerated
     */
    public Boolean getRecordGlobalParam() {
        return recordGlobalParam;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process.record_global_param
     *
     * @param recordGlobalParam the value for process.record_global_param
     *
     * @mbggenerated
     */
    public void setRecordGlobalParam(Boolean recordGlobalParam) {
        this.recordGlobalParam = recordGlobalParam;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process.record_process_input
     *
     * @return the value of process.record_process_input
     *
     * @mbggenerated
     */
    public Boolean getRecordProcessInput() {
        return recordProcessInput;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process.record_process_input
     *
     * @param recordProcessInput the value for process.record_process_input
     *
     * @mbggenerated
     */
    public void setRecordProcessInput(Boolean recordProcessInput) {
        this.recordProcessInput = recordProcessInput;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process.record_trigger_input
     *
     * @return the value of process.record_trigger_input
     *
     * @mbggenerated
     */
    public Boolean getRecordTriggerInput() {
        return recordTriggerInput;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process.record_trigger_input
     *
     * @param recordTriggerInput the value for process.record_trigger_input
     *
     * @mbggenerated
     */
    public void setRecordTriggerInput(Boolean recordTriggerInput) {
        this.recordTriggerInput = recordTriggerInput;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process.record_trigger_result
     *
     * @return the value of process.record_trigger_result
     *
     * @mbggenerated
     */
    public Boolean getRecordTriggerResult() {
        return recordTriggerResult;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process.record_trigger_result
     *
     * @param recordTriggerResult the value for process.record_trigger_result
     *
     * @mbggenerated
     */
    public void setRecordTriggerResult(Boolean recordTriggerResult) {
        this.recordTriggerResult = recordTriggerResult;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process.create_time
     *
     * @return the value of process.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process.create_time
     *
     * @param createTime the value for process.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process.modified_time
     *
     * @return the value of process.modified_time
     *
     * @mbggenerated
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process.modified_time
     *
     * @param modifiedTime the value for process.modified_time
     *
     * @mbggenerated
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}