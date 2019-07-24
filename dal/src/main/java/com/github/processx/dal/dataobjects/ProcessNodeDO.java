package com.github.processx.dal.dataobjects;

import java.util.Date;

public class ProcessNodeDO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process_node.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process_node.process_id
     *
     * @mbggenerated
     */
    private Long processId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process_node.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process_node.node_type
     *
     * @mbggenerated
     */
    private Integer nodeType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process_node.pre_node_id
     *
     * @mbggenerated
     */
    private String preNodeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process_node.execute_compoment
     *
     * @mbggenerated
     */
    private String executeCompoment;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process_node.max_exe_time
     *
     * @mbggenerated
     */
    private Integer maxExeTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process_node.is_sync
     *
     * @mbggenerated
     */
    private Boolean isSync;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process_node.is_protected
     *
     * @mbggenerated
     */
    private Boolean isProtected;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process_node.stage
     *
     * @mbggenerated
     */
    private String stage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process_node.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process_node.modified_time
     *
     * @mbggenerated
     */
    private Date modifiedTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table process_node
     *
     * @mbggenerated
     */
    public ProcessNodeDO(Long id, Long processId, String name, Integer nodeType, String preNodeId, String executeCompoment, Integer maxExeTime, Boolean isSync, Boolean isProtected, String stage, Date createTime, Date modifiedTime) {
        this.id = id;
        this.processId = processId;
        this.name = name;
        this.nodeType = nodeType;
        this.preNodeId = preNodeId;
        this.executeCompoment = executeCompoment;
        this.maxExeTime = maxExeTime;
        this.isSync = isSync;
        this.isProtected = isProtected;
        this.stage = stage;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table process_node
     *
     * @mbggenerated
     */
    public ProcessNodeDO() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process_node.id
     *
     * @return the value of process_node.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process_node.id
     *
     * @param id the value for process_node.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process_node.process_id
     *
     * @return the value of process_node.process_id
     *
     * @mbggenerated
     */
    public Long getProcessId() {
        return processId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process_node.process_id
     *
     * @param processId the value for process_node.process_id
     *
     * @mbggenerated
     */
    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process_node.name
     *
     * @return the value of process_node.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process_node.name
     *
     * @param name the value for process_node.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process_node.node_type
     *
     * @return the value of process_node.node_type
     *
     * @mbggenerated
     */
    public Integer getNodeType() {
        return nodeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process_node.node_type
     *
     * @param nodeType the value for process_node.node_type
     *
     * @mbggenerated
     */
    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process_node.pre_node_id
     *
     * @return the value of process_node.pre_node_id
     *
     * @mbggenerated
     */
    public String getPreNodeId() {
        return preNodeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process_node.pre_node_id
     *
     * @param preNodeId the value for process_node.pre_node_id
     *
     * @mbggenerated
     */
    public void setPreNodeId(String preNodeId) {
        this.preNodeId = preNodeId == null ? null : preNodeId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process_node.execute_compoment
     *
     * @return the value of process_node.execute_compoment
     *
     * @mbggenerated
     */
    public String getExecuteCompoment() {
        return executeCompoment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process_node.execute_compoment
     *
     * @param executeCompoment the value for process_node.execute_compoment
     *
     * @mbggenerated
     */
    public void setExecuteCompoment(String executeCompoment) {
        this.executeCompoment = executeCompoment == null ? null : executeCompoment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process_node.max_exe_time
     *
     * @return the value of process_node.max_exe_time
     *
     * @mbggenerated
     */
    public Integer getMaxExeTime() {
        return maxExeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process_node.max_exe_time
     *
     * @param maxExeTime the value for process_node.max_exe_time
     *
     * @mbggenerated
     */
    public void setMaxExeTime(Integer maxExeTime) {
        this.maxExeTime = maxExeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process_node.is_sync
     *
     * @return the value of process_node.is_sync
     *
     * @mbggenerated
     */
    public Boolean getIsSync() {
        return isSync;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process_node.is_sync
     *
     * @param isSync the value for process_node.is_sync
     *
     * @mbggenerated
     */
    public void setIsSync(Boolean isSync) {
        this.isSync = isSync;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process_node.is_protected
     *
     * @return the value of process_node.is_protected
     *
     * @mbggenerated
     */
    public Boolean getIsProtected() {
        return isProtected;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process_node.is_protected
     *
     * @param isProtected the value for process_node.is_protected
     *
     * @mbggenerated
     */
    public void setIsProtected(Boolean isProtected) {
        this.isProtected = isProtected;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process_node.stage
     *
     * @return the value of process_node.stage
     *
     * @mbggenerated
     */
    public String getStage() {
        return stage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process_node.stage
     *
     * @param stage the value for process_node.stage
     *
     * @mbggenerated
     */
    public void setStage(String stage) {
        this.stage = stage == null ? null : stage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process_node.create_time
     *
     * @return the value of process_node.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process_node.create_time
     *
     * @param createTime the value for process_node.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process_node.modified_time
     *
     * @return the value of process_node.modified_time
     *
     * @mbggenerated
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process_node.modified_time
     *
     * @param modifiedTime the value for process_node.modified_time
     *
     * @mbggenerated
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}