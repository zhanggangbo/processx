package com.github.processx.dal.daointerface;

import com.github.processx.dal.dataobjects.ProcessInstanceDO;
import java.util.List;

public interface ProcessInstanceDOMapper extends ProcessInstanceDOMapperBase {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table process_instance
   *
   * @mbggenerated
   */
  int deleteByPrimaryKey(Long id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table process_instance
   *
   * @mbggenerated
   */
  int insert(ProcessInstanceDO record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table process_instance
   *
   * @mbggenerated
   */
  ProcessInstanceDO selectByPrimaryKey(Long id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table process_instance
   *
   * @mbggenerated
   */
  List<ProcessInstanceDO> selectAll();

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table process_instance
   *
   * @mbggenerated
   */
  int updateByPrimaryKey(ProcessInstanceDO record);
}